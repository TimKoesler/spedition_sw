package othr.sw.koesler.service;

import othr.sw.koesler.entity.*;
import othr.sw.koesler.entity.repo.OrderRepo;
import othr.sw.koesler.entity.repo.VehicleRepo;
import othr.sw.koesler.entity.util.OrderStatus;
import othr.sw.koesler.entity.util.OrderType;
import othr.sw.koesler.entity.util.Protokollieren;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.security.auth.login.LoginException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.security.InvalidParameterException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static javax.transaction.Transactional.TxType.REQUIRED;

@WebService
@SessionScoped
public class BookingService implements Serializable {

    @PersistenceContext(unitName = "speditionPU")
    private EntityManager entityManager;

    private final static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");

    @Inject
    private OrderRepo orderRepo;
    @Inject
    private VehicleRepo vehicleRepo;

    @Inject
    private UserService userService;


    //Frontend Method
    @Transactional(REQUIRED)
    @WebMethod(exclude = true)
    public Order createOrder(Customer customer, OrderType type, Address source, Address destination, List<Shipable> items, Calendar date) throws NullPointerException {
        //TODO
        if(customer == null || type == null || source == null || destination == null || items == null || date == null) {
            throw new NullPointerException("One of the Order Arguments is null");
        }
        Order newOrder = new Order(customer, date);
        newOrder = checkAddress(newOrder, source, destination);
        newOrder.setType(type);
        newOrder.setLineitems(items);
        orderRepo.persist(newOrder);
        return newOrder;

    }

    //WebService Interface
    @Transactional(REQUIRED) @Protokollieren
    public Long createItemOrder(@WebParam(name="Username") String user, @WebParam(name="Password") String password, @WebParam(name="PickUpAddress") Address source, @WebParam(name="DeliveryAddress")Address destination, @WebParam(name="Items")List<itemShipable> items, @WebParam(name = "Date") Date date) throws LoginException, InvalidParameterException {
        OrderType type = OrderType.Item_Transport;

        int amount = 0;
        for(itemShipable i : items) {
            amount *= i.getAmount();
        }

        return this.orderLogic(type, user, password, source, destination, new ArrayList<Shipable>(items), amount, date).getId();
    }

    //WebService Interface
    @Transactional(REQUIRED) @Protokollieren
    public Long createHumanOrder(@WebParam(name="Username") String user, @WebParam(name="Password") String password, @WebParam(name="PickUpAddress") Address source, @WebParam(name="DeliveryAddress")Address destination, @WebParam(name="Persons")List<humanShipable> items, @WebParam(name = "Date") Date date) throws LoginException, InvalidParameterException {
//        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");     for testing
//        Calendar date2 = Calendar.getInstance();
//        try {
//            date2.setTime(format.parse(date));
//        } catch (ParseException e) {
//            System.out.println("Date error");
//        }
        OrderType type = OrderType.Human_Transport;

        int amount = items.size();

        return this.orderLogic(type, user, password, source, destination, new ArrayList<Shipable>(items), amount, date).getId();

    }

    @Transactional(REQUIRED)
    @WebMethod(exclude = true)
    private Order orderLogic(OrderType type, String user, String password, Address source, Address destination, List<Shipable> items,  int amount, Date date) throws LoginException, InvalidParameterException {
        //Login
        try {
            //Plain, maybe extend logic in future
            if(date == null) {
                date = new Date();
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            Customer c = userService.login(user, password);
            //brauch ich?
            c = entityManager.merge(c);
            if(source == null) {
                source = c.getAddress();
            }

            if(destination == null) {
                //Special for mine
                if(c.getId() == TransportService.mine) {
                    Customer tempCustomer = entityManager.find(Customer.class, TransportService.kraftwerk);
                    destination = tempCustomer.getAddress();
                } else {
                    throw new InvalidParameterException("Destination adress is null");
                }
            }


            //Check Order
            if(checkAvailability(type, amount, calendar)) {
                Order newOrder = new Order(c, calendar);
                newOrder.setType(type);
                newOrder.setLineitems(items);
                newOrder = checkAddress(newOrder, source, destination);
                orderRepo.persist(newOrder);
                return newOrder;
            } else {
                //TODO non availaible Handling
                throw new InvalidParameterException("Please check your parameters, seems like something is null!");
            }
        } catch (LoginException l) {
            //TODO let user know that he fucked up
            throw new LoginException("Credentials not valid");
        }
    }

    @Transactional(REQUIRED)
    @WebMethod(exclude = true)
    public boolean cancelOrder(int id) throws NullPointerException{
        Order order = orderRepo.getById(Integer.toUnsignedLong(id));
        if(order != null) {
            order.setOrderStatus(OrderStatus.Cancelled);
            orderRepo.persist(order);
            return true;
        } else {
            throw new NullPointerException("No such Order found!");
        }
    }
    @WebMethod(exclude = true)
    @Transactional(REQUIRED)
    public boolean checkAvailability(OrderType type, int amount, Calendar date) {
        //TODO More complex implementation?
        //System.out.println("CHECKING AVAILABILITY: " + type + " Amount " + amount);
        for(Vehicle v : vehicleRepo.getAll()) {
            if(v.isAvailability() && v.getCapacity() > amount) {
                return true;
            }
        }
        return false;
    }

    @Transactional(REQUIRED)
    private Order checkAddress(Order newOrder, Address source, Address destination) {
        //Make sure they have IDs
        source = new Address(source.getStreet(), source.getCity(), source.getCountry(), source.getPLZ());
        destination = new Address(destination.getStreet(), destination.getCity(), destination.getCountry(), destination.getPLZ());
        Address s =  entityManager.find(Address.class, source.getId());
        Address d =  entityManager.find(Address.class, destination.getId());

        //Wenns schon gibt
        if(s == null) {
            newOrder.setSource(source);
        } else {
            newOrder.setSource(s);
        }
        if(d == null) {
            newOrder.setDestination(destination);
        } else {
            newOrder.setDestination(d);
        }
        return newOrder;
    }

    @Transactional(REQUIRED)
    @WebMethod(exclude = true)
    public List<Order> getAllOrders(Customer c) throws NullPointerException{
        if(c == null) {
            throw new NullPointerException("No Customer Referenced");
        } else {
            Long custId = c.getId();
            TypedQuery<Order> query = entityManager.createQuery(
                    "Select o from Order as o where o.customer.id = :custId",
                    Order.class
            );
            query.setParameter("custId", custId);
            return query.getResultList();
        }
    }
}
