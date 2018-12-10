package othr.sw.koesler.service;

import othr.sw.koesler.entity.*;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Date;

@ApplicationScoped
public class InitService {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void init() {
        Customer customer = em.find(Customer.class, "1");
        if(customer == null) {
            customer = new Customer("Koesler", "Tim");
            em.persist(customer);
            Customer customer2 = new Customer("Machau", "Thomas");
            em.persist(customer2);
            Customer customer3 = new Customer("Jureschek", "Paul");
            em.persist(customer3);
            Customer customer4 = new Customer("Wagner", "Michael");
            em.persist(customer4);
        }

        Order order = em.find(Order.class, "1");
        if(order == null) {
            order = new Order(em.find(Customer.class, "1"), new Address(), new Date(2018, 12, 21));
            Order order2 = new Order(em.find(Customer.class, "2"), new Address(), new Date(2018, 12, 22));
            Order order3 = new Order(em.find(Customer.class, "3"), new Address(), new Date(2018, 12, 23));
            Order order4 = new Order(em.find(Customer.class, "4"), new Address(), new Date(2018, 12, 24));
            em.persist(order);
            em.persist(order2);
            em.persist(order3);
            em.persist(order4);
        }
    }
}
