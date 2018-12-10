package othr.sw.koesler.entity.util;

import javax.persistence.Embeddable;

@Embeddable
public enum OrderType {
    PickUp, Delivery;
}
