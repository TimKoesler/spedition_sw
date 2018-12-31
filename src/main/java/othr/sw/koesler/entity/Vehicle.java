package othr.sw.koesler.entity;

import othr.sw.koesler.entity.util.GeneratedIdEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.awt.*;
import java.util.Scanner;

@Entity
public class Vehicle extends GeneratedIdEntity {

    private String model;
    private Color color;
    private int capacity, reach, fuelCapacity;

    @ManyToOne
    private Worker driver;


    public Vehicle() {
    }

    public Vehicle (String model, Color color, int capacity) {
        this.model = model;
        this.color = color;
        this.capacity = capacity;
    }

    public Color getColor() {
        return color;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getFuelCapacity() {
        return fuelCapacity;
    }

    public int getReach() {
        return reach;
    }

    public String getModel() {
        return model;
    }

    public Worker getDriver() {
        if(driver != null) {
            return driver;      //TOSTRING?
        } else {
            return null;
        }
    }

    public void setDriver(Worker driver) {
        if(this.driver != null) {
            this.driver = driver;
        } else {
            System.out.println("Driver already assigned: " + this.driver.toString() + " - Want to swap? (Y/N)");
            Scanner sc = new Scanner(System.in);
            String answer = sc.nextLine();
            switch(answer) {
                case "Y": this.driver = driver;
                        return;
                case "N": System.out.println("Request canceled!");
                            return;
                default:    System.out.println("Invalid input, request rejected");
                            return;
            }
        }
    }

    @Override
    public String toString() {
        return "Vehicle: " + this.getId() + ", " +  model + ", " + this.color.toString();
    }
}
