package othr.sw.koesler.entity;

import othr.sw.koesler.entity.util.GeneratedIdEntity;

import javax.persistence.ManyToMany;
import java.awt.*;
import java.util.Scanner;

public class Vehicle extends GeneratedIdEntity {
    public static long id_count = 0;

    String model;
    Color color;
    int capacity, reach, fuelCapacity;

    @ManyToMany
    Worker driver;

    public Vehicle (String model, Color color, int capacity, int reach, int fuelCapacity) {
        super.id = this.id_count;
        this.id_count++;
        this.model = model;
        this.color = color;
        this.capacity = capacity;
        this.reach = reach;
        this.fuelCapacity = fuelCapacity;
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