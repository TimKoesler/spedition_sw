package othr.sw.koesler.entity;

import othr.sw.koesler.entity.util.StringIdEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class LineItem extends StringIdEntity {

    private static long id_count = 1;

    @ManyToOne
    Order order;

    int lineItemNumber, amount;
    String description;

    public LineItem() {
    }

    public LineItem(int lineItemNumber, String description, int amount) {
        this.lineItemNumber = lineItemNumber;
        this.description = description;
        this.amount = amount;
        super.id = order.getId() + "_" + id_count;
        id_count++;
    }

    public int getAmount() {
        return amount;
    }

    public int getLineItemNumber() {
        return lineItemNumber;
    }

    public Order getOrder() {
        return order;
    }

    public String getDescription() {
        return description;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
