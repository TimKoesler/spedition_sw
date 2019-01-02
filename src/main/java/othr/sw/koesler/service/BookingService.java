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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

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
    @Transactional
    @WebMethod(exclude = true)
    public Order createOrder(Customer customer, OrderType type, Address source, Address destination, List<LineItem> items, Calendar date) throws NullPointerException {
        //TODO
        if(customer == null || type == null || source == null || destination == null || items == null || date == null) {
            throw new NullPointerException("One of the Order Arguments is null");
        }
        Order newOrder = new Order(customer, date);
        newOrder = checkAddress(newOrder, source, destination);
        newOrder.setType(type);
        orderRepo.persist(newOrder);
        return newOrder;

    }

    //WebService Interface
    @Transactional @Protokollieren
    public Order createOrder(@WebParam(name="Username") String user, @WebParam(name="Password") String password, @WebParam(name="OrderType") OrderType type, @WebParam(name="PickUpAddress") Address source, @WebParam(name="DeliveryAddress")Address destination, @WebParam(name="LineItems")List<LineItem> items, @WebParam(name="AmountKG") int amount, @WebParam(name = "Date") Calendar date) throws LoginException {
        //Login
        try {
            Customer c = userService.login(user, password);
            //Check Order
            if(checkAvailability(type, amount, date)) {
                Order newOrder = new Order(c, date);
                //Convert to Enum - nur wenn ichs manuell aufrufe brauch ich das oder?
                for(OrderType ot : OrderType.values()) {
                    if(ot.getLabel().equals(type)) {
                        type = ot;
                    }
                }
                newOrder.setType(type);
                newOrder.setLineitems(items);
                checkAddress(newOrder, source, destination);
                return newOrder;
            } else {
                //TODO non availaible Handling
                return null;
            }
        } catch (LoginException l) {
            //TODO let user know that he fucked up
            return null;
        }
    }

    @Transactional
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
    public boolean checkAvailability(OrderType type, int amount, Calendar date) {
        //TODO More complex implementation?
        System.out.println("CHECKING AVAILABILITY: " + type + " Amount " + amount);
        for(Vehicle v : vehicleRepo.getAll()) {
            if(v.isAvailability() && v.getCapacity() > amount) {
                return true;
            }
        }
        return false;
    }

    private Order checkAddress(Order newOrder, Address source, Address destination) {
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

    //TODO custId nicht assigned
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
