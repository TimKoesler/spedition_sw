package othr.sw.koesler.ui.model;


import othr.sw.koesler.entity.Order;
import othr.sw.koesler.entity.util.OrderType;
import othr.sw.koesler.service.BookingService;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;

@Named
@SessionScoped
public class TestModel implements Serializable {

    @Inject
    BookingService bookingService = new BookingService(); //Davon könnte man jetzt auf der xhtml mal anzeigen lassen

    private String text = "Blalblalal";
    private int counter = 0;
    //test
  //  private Order order = new Order();

 public void aendere() {
        System.out.println("WURDE GEKLICKT!!!!!!!!!!!!!!!!");
        this.counter++;
        if(bookingService.checkAvailability(OrderType.Delivery, new Date(), 555) == true) {
            System.out.println("AVAILABLE");
        } else {
            System.out.println("SORRY NOT SORRY");
        }
    }


    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public String getText() {
        System.out.println("Getter");
        return this.text;
    }

    public void setText(String text) {
        System.out.println("setter");
        this.text = text;
    }
}
