package othr.sw.koesler.entity;

import othr.sw.koesler.entity.util.GeneratedIdEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Worker extends GeneratedIdEntity {

    public static long id_count = 1;

    String name;
    boolean available;
    @ManyToOne
    private Address address;
    Date birthday;

    public Worker() {
    }

    public Worker(String name) {
        super.id = this.id_count;
        id_count++;
        this.name = name;
        this.available = true;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isAvailable() {
        return available;
    }

    public Address getAdress() {
        return address;
    }

    public void setAdress(Address address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ID: " + this.getId() + ",Name: " + this.name;
    }
}
