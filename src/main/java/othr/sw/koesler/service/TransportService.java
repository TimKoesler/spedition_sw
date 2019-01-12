package othr.sw.koesler.service;

import othr.sw.koesler.entity.Customer;
import othr.sw.koesler.entity.Order;
import othr.sw.koesler.entity.Vehicle;
import othr.sw.koesler.entity.repo.CustomerRepo;
import othr.sw.koesler.entity.repo.OrderRepo;
import othr.sw.koesler.entity.repo.VehicleRepo;
import othr.sw.koesler.entity.util.OrderStatus;
import othr.sw.koesler.entity.util.OrderType;
import partner.zeitarbeit.PickUp;
import partner.zeitarbeit.PickupService;

import javax.ejb.*;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static javax.transaction.Transactional.TxType.REQUIRED;

@Singleton
@Startup
public class TransportService {

    public static final long zeitarbeit = 1;
    public static final long mine = 52;
    public static final long kraftwerk = 3;

    @PersistenceContext(unitName = "speditionPU")
    EntityManager em;

    @Inject
    private OrderRepo orderRepo;
    @Inject
    private CustomerRepo customerRepo;
    @Inject
    private VehicleRepo vehicleRepo;

    @Schedule (
            hour="*",
            minute = "*/5",
            persistent = false
    )
    private void checkForOrders() {
        Calendar today = Calendar.getInstance();
        today.setTime(new Date());
        for(Order o : orderRepo.getAll()) {
            //irrelevant
            if(o.getOrderStatus() == OrderStatus.Cancelled || o.getOrderStatus() == OrderStatus.Done  || o.getOrderStatus() == OrderStatus.inProgess)
                continue;
            if(o.getDueDate().get(Calendar.YEAR) == today.get(Calendar.YEAR) || o.getOrderStatus() == OrderStatus.Planned) {
                if(o.getDueDate().get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR) || o.getOrderStatus() == OrderStatus.Planned)  {
                    //If today
                    if(o.getOrderStatus() != OrderStatus.Planned) {
                        o.setOrderStatus(OrderStatus.Planned);
                        em.persist(o);
                    }

                    if(o.getDueDate().get(Calendar.HOUR_OF_DAY) == today.get(Calendar.HOUR_OF_DAY) || o.getOrderStatus() == OrderStatus.Planned){
                        if(o.getOrderStatus() == OrderStatus.Planned) {
                            pickUp(o);
                        }
                    }
                }
            }
        }
    }

    @Transactional(REQUIRED)
    private void pickUp(Order o) {
      //  System.out.println("Going to pickup LineItems at..." + o.getSource());
        o.setOrderStatus(OrderStatus.inProgess);
        em.merge(o);

        long id = o.getCustomer().getId();

        //TODO on response deliver
       if(id == zeitarbeit) {
           //Zeitarbeit interface pickup

       } else if(id == mine) {
           //mine interface pickup
       } else if(id == kraftwerk) {
           //kraftwerk interface pickup - gibts gar nicht?
       } else {
           //DEFAULT CASE
       }

        try {
            //Wait till its there
            TimeUnit.MINUTES.sleep(Double.doubleToLongBits(o.calcDuration()));
            this.deliver(o);
        } catch(InterruptedException e) {
            //Set it back to planned for redelivery
            System.out.println(e.getCause());
            o.setOrderStatus(OrderStatus.Planned);
            em.merge(o);
        }
    }

    //TODO Logger für failed deliveries?

    @Transactional(REQUIRED)
    private void deliver(Order o) {
       // System.out.println("Reached Delivery Address..." + o.getDestination());
        //Assign vehicles
        //TODO use Total Lineitem weight to calc vehicle necesary
        int amount = o.getLineitems().size();
        if(o.getType() == OrderType.Human_Transport) {
            for(Vehicle v : vehicleRepo.getAll()) {
                //Look for available vehicles
                if(v.getModel().contains("Person") && v.isAvailability()) {
                    o.addVehicle(v);
                    amount -= v.getCapacity();
                    if(amount <= 0) {
                        break;
                    }
                }
            }
            if(amount > 0) {
                o.setOrderStatus(OrderStatus.Planned);
                em.merge(o);
                System.out.println("Not enough available vehicles, schedule later");
                return;
            }
        } else {
            for (Vehicle v : vehicleRepo.getAll()) {

            }
        }

        long id;
        Customer cust;

        if(o.getCustomer().getId() == mine) {
            cust = em.find(Customer.class, kraftwerk);
        } else {
            cust = null;
            for (Customer c : customerRepo.getAll()) {
                if (c.getAddress().equals(o.getDestination())) {
                    //Customer with same address cant exist.... or first one gets taken
                    cust = c;
                    break;
                }
            }
        }

        if(cust != null) {
            id = cust.getId();
        } else {
            id = 0;
        }

        //DO STUFF
        if(id == zeitarbeit) {
            //Zeitarbeit interface deliver
        } else if(id == mine) {
            //mine interface deliver
        } else if(id == kraftwerk) {
            //kraftwerk interface deliver
        } else {
            //DEFAULT CASE
        }


        o.setOrderStatus(OrderStatus.Done);
        em.merge(o);
    }
}
