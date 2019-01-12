package othr.sw.koesler.entity;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class humanShipable extends Shipable {
    
    private String firstname, lastname;
    private Long persNr;

    public humanShipable() {

    }

    public humanShipable(String firstname, String lastname, Long persNr) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.persNr = persNr;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Long getPersNr() {
        return persNr;
    }

    public void setPersNr(Long persNr) {
        this.persNr = persNr;
    }
}
