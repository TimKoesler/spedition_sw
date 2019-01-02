package othr.sw.koesler.entity;

import othr.sw.koesler.entity.util.StringIdEntity;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Random;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Address extends StringIdEntity {

    private final static int LIMIT = 100;

    @XmlTransient
    private Location loc;

    private String street, city, country;
    private int PLZ;


    public Address() {
//        Random r = new Random();
//        this.loc = new Location();
//        this.loc.setX_Coord(r.nextInt(LIMIT));
//        this.loc.setY_Coord(r.nextInt(LIMIT));
    }

    public Address(String street, String city, String country, int PLZ) {
        this.street = street;
        this.city = city;
        this.country = country;
        this.PLZ = PLZ;

        Random r = new Random(100);
        this.loc = new Location();
        this.loc.setX_Coord(r.nextInt(LIMIT));
        this.loc.setY_Coord(r.nextInt(LIMIT));
        super.id = this.toString();
    }

    public Address(int x_Coord, int y_Coord) {
        this.loc = new Location();
        this.loc.setX_Coord(x_Coord);
        this.loc.setY_Coord(y_Coord);
    }

    //Getter
    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public int getPLZ() {
        return PLZ;
    }

    public Location getLoc() { return this.loc; }

    public String getLocString() {
        return loc.getX_Coord() + ", " + loc.getY_Coord();
    }

    //Setter
    public void setStreet(String street) {
        this.street = street;

    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPLZ(int PLZ) {
        this.PLZ = PLZ;
    }

    public void setLoc(Location loc)  {
        this.loc = loc;
    }

    @Override
    public String toString() {
        return street + ",\n" + city + ",\n" + PLZ + " " + country;
    }
}
