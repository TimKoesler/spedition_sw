package othr.sw.koesler.entity;

import javax.persistence.OneToOne;

public class Address {

    @OneToOne
    Location loc;

    String street, city, country;
    int PLZ;

    public Address() {

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

    public String getLoc() {
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
