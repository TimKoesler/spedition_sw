package othr.sw.koesler.entity;

import othr.sw.koesler.entity.util.GeneratedIdEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Customer extends GeneratedIdEntity {

    public static long id_count = 1;

    String firstname, lastname;

    @ManyToOne
    Address address;

    public Customer() {
    }

    public Customer (String firstname, String lastname) {
        //Sequence ID
        super.id = this.id_count;
        this.id_count++;

        this.firstname = firstname;
        this.lastname = lastname;
    }

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

    @Override
    public String toString() {
        return "ID: " + super.getId() + ", NAME: " + this.lastname + ", " + firstname;
    }
}
