package othr.sw.koesler.ui.model;

import othr.sw.koesler.entity.*;
import othr.sw.koesler.entity.util.OrderStatus;
import othr.sw.koesler.service.BookingService;
import othr.sw.koesler.entity.util.OrderType;
import othr.sw.koesler.service.UserService;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.auth.login.LoginException;
import java.io.Serializable;
import java.security.InvalidParameterException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Named
@SessionScoped
public class SpeditionModel implements Serializable {


    private OrderType orderType;
    private List<Shipable> lineItems = new ArrayList<>();
    private int amount = 0;
    private boolean availability = false;
    private Date date;
    private Calendar tempTime = Calendar.getInstance();
    private Address destination;
    //ERRORS
    private String errorMsg;
    private boolean error = false;


    //Login part
    private boolean loggedIn = false;
    private String user, password;
    private Customer tempCustomer;
    //Register + Temp Var for Data Save
    private String firstname, lastname, street, city, country;
    private int PLZ = 0;

    @Inject
    private BookingService bookingService;
    @Inject
    private UserService userService;

    @Inject
    private Conversation conversation;

    //Action methoden
    public String validateNewOrder() {
        if(this.amount == 0 || this.date.equals(null)) {
            this.error = true;
            this.errorMsg = "Please fill out all the fields!";
            return null;
        } else {
            this.tempTime.setTime(this.date);
            this.availability = this.bookingService.checkAvailability(this.orderType, this.amount, this.tempTime);
            this.error = false;
            return "availabilitySummary";
        }
    }

    public String orderTypeRouting() {
        if(this.orderType == orderType.Item_Transport) {
            return "orderLineItems";
        } else {
            return "orderHumans";
        }
    }

    public String validateLineitems() {
            if(lineItems.isEmpty()) {
                this.errorMsg = "Please add at least a single line item for a valid order!";
                this.error = true;
                return null;
            } else {
                this.error = false;
                return "addressInformations";
            }
    }

    public void addItemShipable (int amount, String description) {
        this.lineItems.add(new itemShipable(amount, description));
    }
    public void addHumanShipable (String firstname, String lastname, int persNr) {
        this.lineItems.add(new humanShipable(firstname, lastname, Integer.toUnsignedLong(persNr)));
    }

    public void removeLineItem(Shipable item) {
        this.lineItems.remove(item);
    }


    public String saveDestination (String streetDest, String cityDest, String countryDest, int PLZDest) {
        if(streetDest == null || cityDest == null || countryDest == null || PLZDest == 0 || this.street == null || this.city == null || this.country == null || this.PLZ == 0) {
            this.errorMsg = "Please fill out all the Address Information";
            this.error = true;
            return null;
        } else {
            this.error = false;
            this.destination = new Address(streetDest, cityDest, countryDest, PLZDest);
            if(this.orderType == OrderType.Item_Transport)
                return "orderSummary";
            else
                return "humanOrderSummary";
        }
    }

    public String createOrder() {
        //TODO Error Handling
        try {
            bookingService.createOrder(this.tempCustomer, this.orderType, new Address(this.street, this.city, this.country, this.PLZ), this.destination, this.lineItems, this.tempTime);
            resetVars();
        } catch (NullPointerException e) {
            System.out.println("Argument Null @: " + e.getCause());
            this.errorMsg = "Please fill out all the fields!";
            this.error = true;
        }
        return "thankYouScreen";
    }

    private void resetVars() {
        this.orderType = null;
        this.destination = null;
        this.street = null;
        this.country = null;
        this.PLZ = 0;
        this.city = null;
        this.lineItems = null;
        this.tempTime = null;
    }

    public String login() {

        if(this.conversation.isTransient()) {
            this.conversation.begin();
        }
        try {
            Customer customer = userService.login(this.user, this.password);
            this.tempCustomer = customer;
            this.updateUserInfo("GET");
            this.loggedIn = true;
            this.error = false;
            return "home";

        } catch (LoginException l) {
            System.out.println(l.getCause());
            this.error = true;
            this.errorMsg = "Username or Password invalid!";
            return null;
        }
    }

    public String logout() {
        this.loggedIn  = false;
        resetVars();
        if(!this.conversation.isTransient()) {
            this.conversation.end();
        }
        return "home";
    }

    public String register(String PLZ) {
        if(this.conversation.isTransient()) {
            this.conversation.begin();
        }
        this.PLZ = Integer.parseInt(PLZ);
        System.out.println("Data: " + this.firstname + " " + this.lastname + " " +  new Address(this.street, this.city, this.country, this.PLZ) + " " +  this.user + " " +  this.password);
        if(this.firstname != null || this.lastname != null || this.PLZ != 0 || this.user != null || this.password != null) {
            this.tempCustomer = this.userService.register(this.firstname, this.lastname, new Address(this.street, this.city, this.country, this.PLZ), this.user, this.password);
            if(this.tempCustomer == null) {
                this.error = true;
                this.errorMsg = "Username already in use!";
                return null;
            } else {
                this.updateUserInfo("GET");
                this.loggedIn = true;
                this.error = false;
                return "home";
            }
        } else {
            this.error = true;
            this.errorMsg = "Something went wrong - please check your data!";
            return null;
        }
    }

    public String userUpdate() {

        this.updateUserInfo("SET");
        this.tempCustomer = userService.updateUser(tempCustomer);
        this.updateUserInfo("GET");
        return "updatedUser";
    }

    private void updateUserInfo(String type) throws InvalidParameterException {
        if(type.equals("SET")) {
            this.tempCustomer.setUser(this.user);
            this.tempCustomer.setFirstname(this.firstname);
            this.tempCustomer.setLastname(this.lastname);
            this.tempCustomer.getAddress().setCity(this.city);
            this.tempCustomer.getAddress().setCountry(this.country);
            this.tempCustomer.getAddress().setPLZ(this.PLZ);
            this.tempCustomer.getAddress().setStreet(this.street);;
        } else if(type.equals("GET")) {
            this.user = this.tempCustomer.getUser();
            this.firstname = this.tempCustomer.getFirstname();
            this.lastname = this.tempCustomer.getLastname();
            this.street = this.tempCustomer.getAddress().getStreet();
            this.city = this.tempCustomer.getAddress().getCity();
            this.country = this.tempCustomer.getAddress().getCountry();
            this.PLZ = this.tempCustomer.getAddress().getPLZ();
        } else {
            throw new InvalidParameterException("Parameter: " + type + " cannot be processed");
        }
    }

    public void deleteUser() {
        userService.deleteUser(this.tempCustomer);
    }

    public String validateRequestTransport() {
        if(loggedIn) {
            return "requestTransport";
        } else {
            return "login";
        }
    }

    public Collection<Order> getAllOrders() {
        return bookingService.getAllOrders(this.tempCustomer);
    }

    public String isAvailable() {
        if(availability)
            return "YES";
        else
            return "NO";
    }

    public void cancelOrder(int id) {
        this.bookingService.cancelOrder(id);
    }

    public boolean isCancellable(OrderStatus status) {
        if(status == OrderStatus.Cancelled || status == OrderStatus.Done) {
            return false;
        } else {
            return true;
        }
    }

    //Getter and Setter


    public List<Shipable> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<Shipable> lineItems) {
        this.lineItems = lineItems;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getPLZ() {
       return this.PLZ;
    }

    public void setPLZ(int PLZ) {
        this.PLZ = PLZ;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
