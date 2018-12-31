package othr.sw.koesler.service;

import othr.sw.koesler.entity.*;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.awt.*;
import java.util.Date;

@ApplicationScoped
public class InitService {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void init() {
//        Customer customer = em.find(Customer.class, "1");
//        if(customer == null) {
//            customer = new Customer("Tim", "Koesler", new Address());
//            customer.setUser("Tim");
//            customer.setPassword("Koesler");
//            em.persist(customer);
//            Customer customer2 = new Customer("Thomas", "Machau", new Address());
//            customer2.setUser("Thomas");
//            customer2.setPassword("Machau");
//            em.persist(customer2);
//            Customer customer3 = new Customer("Paul", "Jureschek", new Address());
//            customer3.setUser("Paul");
//            customer3.setPassword("Jureschek");
//            em.persist(customer3);
//            Customer customer4 = new Customer("Michael", "Wagner", new Address());
//            customer4.setUser("Michael");
//            customer4.setPassword("Wagner");
//            em.persist(customer4);
//        }
//
//        Order order = em.find(Order.class, "1");
//        if(order == null) {
//            order = new Order(em.find(Customer.class, "1"), new Address(), new Date(2018, 12, 21));
//            Order order2 = new Order(em.find(Customer.class, "2"), new Address(), new Date(2018, 12, 22));
//            Order order3 = new Order(em.find(Customer.class, "3"), new Address(), new Date(2018, 12, 23));
//            Order order4 = new Order(em.find(Customer.class, "4"), new Address(), new Date(2018, 12, 24));
//            em.persist(order);
//            em.persist(order2);
//            em.persist(order3);
//            em.persist(order4);
//        }
        //String model, Color color, int capacity, int reach, int fuelCapacity
        Vehicle vehicle1 = new Vehicle("LKW Groß", Color.BLACK, 24000);
        Vehicle vehicle2 = new Vehicle("LKW Groß", Color.BLACK, 24000);
        Vehicle vehicle3 = new Vehicle("LKW Mittel", Color.BLACK, 12000);
        Vehicle vehicle4 = new Vehicle("LKW Mittel", Color.BLACK, 12000);
        Vehicle vehicle5 = new Vehicle("LKW Mittel", Color.BLACK, 12000);
        Vehicle vehicle6 = new Vehicle("LKW Mittel", Color.BLACK, 12000);
        Vehicle vehicle7 = new Vehicle("LKW Klein", Color.BLACK, 6000);
        Vehicle vehicle8 = new Vehicle("LKW Klein", Color.BLACK, 6000);
        em.persist(vehicle1);
        em.persist(vehicle2);
        em.persist(vehicle3);
        em.persist(vehicle4);
        em.persist(vehicle5);
        em.persist(vehicle6);
        em.persist(vehicle7);
        em.persist(vehicle8);
    }
}
