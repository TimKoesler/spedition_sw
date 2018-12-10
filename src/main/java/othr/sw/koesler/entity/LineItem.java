package othr.sw.koesler.entity;

import javax.persistence.ManyToOne;

public class LineItem {

    @ManyToOne
    Order order;

    int lineItemNumber, amount;
    String description;

    public LineItem(int lineItemNumber, String description, int amount) {
        this.lineItemNumber = lineItemNumber;
        this.description = description;
        this.amount = amount;
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
