package othr.sw.koesler.entity;

import othr.sw.koesler.entity.util.GeneratedIdEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class LineItem extends GeneratedIdEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    private Order order;

    private int amount;
    private String description;

    public LineItem() {
    }

    public LineItem(int amount, String description) {
        this.description = description;
        this.amount = amount;
    }

    //Getter Setter

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
