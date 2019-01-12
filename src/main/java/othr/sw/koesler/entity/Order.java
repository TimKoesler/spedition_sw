package othr.sw.koesler.entity;

import othr.sw.koesler.entity.util.GeneratedIdEntity;
import othr.sw.koesler.entity.util.*;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "Sped_Order")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Order extends GeneratedIdEntity {

    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    private Collection<Shipable> lineitems = new ArrayList<>();
    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Collection<Vehicle> vehicles = new ArrayList<>();


    @ManyToOne(cascade = CascadeType.PERSIST)
    private Address source;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Address destination;

    private Calendar dueDate;
    private OrderType type;
    private OrderStatus orderStatus;

    public Order() {

    }

    public Order(Customer customer, Calendar dueDate) {

        this.customer = customer;
        this.dueDate = dueDate;
        this.orderStatus = OrderStatus.New;

//        assignVehicle();
//        calcDuration();
    }


    public void addLineItem(Shipable item) {
        this.lineitems.add(item);
    }

    public void addLineItems(List<Shipable> items) {
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

    public double calcDuration() {
        if(this.source != null && this.destination != null) {
            int x = Math.abs(this.source.getLoc().getX_Coord() - this.destination.getLoc().getY_Coord());
            int y = Math.abs(this.source.getLoc().getY_Coord() - this.destination.getLoc().getY_Coord());
            return Math.hypot(x, y);
        } else {
            return 0;
        }
    }

    public void assignVehicle() {
        //TODO use Total Lineitem weight to calc vehicle necesary
        int sum = 0;

        for( Object x: lineitems.toArray()) {
            //TODO
        }
    }

    //Getter Setter
    public Long getId() {
        return super.getId();
    }

    public Calendar getDueDate() {
        return dueDate;
    }

    public void setDueDate(Calendar dueDate) {
        this.dueDate = dueDate;
    }

    public Collection<Shipable> getLineitems() {
        return lineitems;
    }

    public void setLineitems(Collection<Shipable> lineitems) {
        this.lineitems = lineitems;
    }

    public Address getSource() {
        return source;
    }

    public void setSource(Address source) {
        this.source = source;
    }

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
