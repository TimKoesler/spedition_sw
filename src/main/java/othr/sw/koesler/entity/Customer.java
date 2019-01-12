package othr.sw.koesler.entity;

import othr.sw.koesler.entity.util.GeneratedIdEntity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Customer extends GeneratedIdEntity {

    private String firstname, lastname, user;

    @XmlTransient
    private String password;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Address address;

    //TODO Brauch ich hier überhaupt ne beidseitige beziehung???
    @OneToMany(mappedBy = "customer")
    private Collection<Order> orders = new ArrayList<>();

    public Customer() {
    }

    public Customer (String firstname, String lastname, Address address) {
        //Sequence ID
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    //Getter Setter
    public String getFirstname() {
        return this.firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public Address getAddress() {
        return address;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ID: " + super.getId() + ", NAME: " + this.lastname + ", " + firstname;
    }
}
