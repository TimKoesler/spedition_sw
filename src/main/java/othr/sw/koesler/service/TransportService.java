package othr.sw.koesler.service;

import othr.sw.koesler.entity.Order;
import othr.sw.koesler.entity.repo.OrderRepo;
import othr.sw.koesler.entity.util.OrderStatus;

import javax.ejb.*;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Calendar;
import java.util.Date;


@ApplicationScoped
public class TransportService {

    @PersistenceContext(unitName = "speditionPU")
    EntityManager em;

    @Inject
    private OrderRepo orderRepo;

    @Schedule (
            hour="*",
            minute = "*",
            persistent = false
    )
    private void checkForOrders() {
        System.out.println("Checking for due orders.....");
        Calendar today = Calendar.getInstance();
        today.setTime(new Date());
        for(Order o : orderRepo.getAll()) {
            if(o.getDueDate().get(Calendar.YEAR) == today.get(Calendar.YEAR)) {
                if(o.getDueDate().get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR))  {
                    //If today
                    o.setOrderStatus(OrderStatus.Planned);
                    em.persist(o);
                    if(o.getDueDate().get(Calendar.HOUR_OF_DAY) == today.get(Calendar.HOUR_OF_DAY)){
                        if(o.getOrderStatus() == OrderStatus.New) {
                            pickUp(o);
                        }
                    }
                }
            }
        }
    }

    private void pickUp(Order o) {
        System.out.println("Going to pickup LineItems...");
        o.setOrderStatus(OrderStatus.inProgess);
        em.merge(o);
        //TODO on response deliver
        deliver(o);
    }

    private void deliver(Order o) {
        System.out.println("On Delivery...");
        //DO STUFF
        o.setOrderStatus(OrderStatus.Done);
        em.merge(o);
    }
}
