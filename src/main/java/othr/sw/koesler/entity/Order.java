package othr.sw.koesler.entity;

import othr.sw.koesler.entity.util.GeneratedIdEntity;
import othr.sw.koesler.entity.util.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.Date;

@Entity
public class Order extends GeneratedIdEntity {

    private static long id_count = 1;

    @OneToMany(mappedBy="order")
    Collection<LineItem> lineitems;
    @ManyToOne
    Customer customer;
    @OneToMany
    Collection<Vehicle> vehicles;
    @ManyToOne
    Address destination;

    Date dueDate;
    double duration;    //Ist das so sinnhaftig?
    OrderType type;


    public Order() {

    }

    public Order(Customer customer, Address address, Date dueDate) {
        //Sequence ID
        super.id = this.id_count;
        this.id_count++;
        this.destination = address; //TODO Wirklich so?
        this.customer = customer;
        this.dueDate = dueDate;

        assignVehicle();
        calcDuration();
    }


    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
        // vehicle.setStatus(<ENUM>) TODO
    }

    public OrderType getType() {
        return type;
    }

    public void setType(OrderType type) {
        this.type = type;
    }

    private double calcDuration() {
        return 0;
        //TODO
    }

    private void assignVehicle() {
        //TODO use Total Lineitem weight to calc vehicle necesary
        int sum = 0;

        for( Object x: lineitems.toArray()) {
            //TODO
        }
    }

    @Override
    public String toString() {
        return "Order: " + this.getId() + ", Date: " + this.dueDate.toString();
    }
}
