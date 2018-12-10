package othr.sw.koesler.entity.ui.model;


import othr.sw.koesler.entity.Order;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class TestModel implements Serializable {

    @Inject
    BookingService book = new BookingService(); //Davon könnte man jetzt auf der xhtml mal anzeigen lassen

    private String text = "Blalblalal";
    private int counter = 0;
    //test
    private Order order = new Order();

    public void aendere() {
        System.out.println("WURDE GEKLICKT!!!!!!!!!!!!!!!!");
        this.counter++;
        book.doSomething(); //Um einen der Services vom Frontend aufzurufen
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
