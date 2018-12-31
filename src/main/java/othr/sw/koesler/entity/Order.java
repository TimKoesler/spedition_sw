package othr.sw.koesler.entity;

import othr.sw.koesler.entity.util.GeneratedIdEntity;
import othr.sw.koesler.entity.util.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

@Entity
public class Order extends GeneratedIdEntity {

    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    private Collection<LineItem> lineitems = new ArrayList<>();
    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer;
    @OneToMany(fetch = FetchType.LAZY)
    private Collection<Vehicle> vehicles = new ArrayList<>();

    //TODO Will ich beide in der DB? Eigentlich schon falls source vom customer abweicht. Benennen, sodass kein Problem mehr mit gleichen Spalten gibt?

    @ManyToOne
    private Address source;

    @ManyToOne
    private Address destination;

    private Calendar dueDate;
    private OrderType type;
    private OrderStatus orderStatus;

    public Order() {

    }

    public Order(Customer customer, Address source, Address destination, Calendar dueDate) {

        //TODO PLZ Error?
        this.source = source;
        this.destination = destination; //TODO Wirklich so?
        this.customer = customer;
        this.dueDate = dueDate;
        this.orderStatus = OrderStatus.New;

//        assignVehicle();
//        calcDuration();
    }


    public void addLineItem(LineItem item) {
        this.lineitems.add(item);
    }

    public void addLineItems(List<LineItem> items) {
        this.lineitems.addAll(items);
    }

    public void addVehicle(Vehicle vehicle) {
        this.vehicles.add(vehicle);
        // vehicle.setStatus(<ENUM>) TODO
    }

    public void addVehicles(List<Vehicle> vehicles) {
        this.vehicles.addAll(vehicles);
        // vehicle.setStatus(<ENUM>) TODO
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

    //Getter Setter


    public Address getDestination() {
        return destination;
    }

    public void setDestination(Address destination) {
        this.destination = destination;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public OrderType getType() {
        return type;
    }

    public void setType(OrderType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Order: " + this.getId() + ", Date: " + this.dueDate.toString();
    }
}
