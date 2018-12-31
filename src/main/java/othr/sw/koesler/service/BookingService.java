package othr.sw.koesler.service;

import othr.sw.koesler.entity.Address;
import othr.sw.koesler.entity.Customer;
import othr.sw.koesler.entity.LineItem;
import othr.sw.koesler.entity.Order;
import othr.sw.koesler.entity.repo.OrderRepo;
import othr.sw.koesler.entity.util.OrderStatus;
import othr.sw.koesler.entity.util.OrderType;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

@WebService
@SessionScoped
public class BookingService implements Serializable {

    @PersistenceContext(unitName = "speditionPU")
    private EntityManager entityManager;

//    @Inject
//    private TransportService transportService;
    @Inject
    private OrderRepo orderRepo;


    //Frontend Method
    @Transactional
    @WebMethod(exclude = true)
    public Order createOrder(Customer customer, OrderType type, Address source, Address destination, List<LineItem> items, Calendar date) {
        //TODO
        Order newOrder = new Order(customer, source, destination, date);
        orderRepo.persist(newOrder);
        return newOrder;

    }

    //WebService Interface
    @Transactional
    public Order createOrder(@WebParam(name="Username") String user, @WebParam(name="Password") String password, @WebParam(name="OrderType") OrderType type, @WebParam(name="PickUpAddress") Address source, @WebParam(name="DeliveryAddress")Address destination, @WebParam(name="LineItems")List<LineItem> items, int amount, Calendar date) {
        if(checkAvailability(type, amount, date)) {
            Order newOrder = new Order();
            newOrder.setOrderStatus(OrderStatus.New);
            return newOrder;
        } else {
            //TODO non availaible Handling
            return null;
        }
    }

    @Transactional
    @WebMethod(exclude = true)
    public boolean cancelOrder(int id) {
        Order order = orderRepo.getById("" + id);
        if(order != null) {
            order.setOrderStatus(OrderStatus.Cancelled);
            return true;
        } else {
            return false;
        }
    }
    @WebMethod(exclude = true)
    public boolean checkAvailability(OrderType type, int amount, Calendar date) {
        //TODO Check Availability of Vehicles and Drivers
        System.out.println("CHECKING AVAILABILITY: " + type + " Amount " + amount);


        return true;
    }


    //TODO Tablle Order wird nicht instanziiert
    public List<Order> getAllOrders(Customer c) throws NullPointerException{
        if(c == null) {
            throw new NullPointerException("No Customer Referenced");
        } else {
            Long custId = c.getId();
            TypedQuery<Order> query = entityManager.createQuery(
                    "Select o from Order as o where o.customer.id = :custId",
                    //AS o Where o.customer.id = :custId
                    Order.class
            );
            return query.getResultList();
        }
    }
}
