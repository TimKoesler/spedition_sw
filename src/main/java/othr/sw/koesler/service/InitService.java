package othr.sw.koesler.service;

import othr.sw.koesler.entity.*;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.awt.*;

@ApplicationScoped
public class InitService {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void init() {

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

        Worker worker1 = new Worker("Hans Hansen");
        worker1.setAdress(new Address("Hans Straße", "Berlin", "Deutschland", 12345));
        Worker worker2 = new Worker("Horst Heinrich");
        worker2.setAdress(new Address("Horst Straße", "Hamburg", "Deutschland", 95467));
        Worker worker3 = new Worker("Max Mustermann");
        worker3.setAdress(new Address("Max Straße", "Hamburg", "Deutschland", 79456));
        Worker worker4 = new Worker("Doktor Strange");
        worker4.setAdress(new Address("Strange Straße", "Köln", "Deutschland", 18789));
        Worker worker5 = new Worker("Wer bin ich?");
        worker5.setAdress(new Address("??? Straße", "Berching", "Deutschland", 54444));
        Worker worker6 = new Worker("Name Namenlos");
        worker6.setAdress(new Address("Namenlos Straße", "Regensburg", "Deutschland", 36547));
        Worker worker7 = new Worker("Dirk Dietrich");
        worker7.setAdress(new Address("Dirk Straße", "Nürnberg", "Deutschland", 33333));
        Worker worker8 = new Worker("Dieter Doof");
        worker8.setAdress(new Address("Dieter Straße", "Berlin", "Deutschland", 12456));
        Worker worker9 = new Worker("Markus Meckern");
        worker9.setAdress(new Address("Markus Straße", "Berlin", "Deutschland", 78964));
        em.persist(worker1);
        em.persist(worker2);
        em.persist(worker3);
        em.persist(worker4);
        em.persist(worker5);
        em.persist(worker6);
        em.persist(worker7);
        em.persist(worker8);
        em.persist(worker9);
    }
}
