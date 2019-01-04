
package partner.zeitarbeit;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse f\u00fcr orderType.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="orderType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Human_Transport"/&gt;
 *     &lt;enumeration value="Item_Transport"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "orderType")
@XmlEnum
public enum OrderType {

    @XmlEnumValue("Human_Transport")
    HUMAN_TRANSPORT("Human_Transport"),
    @XmlEnumValue("Item_Transport")
    ITEM_TRANSPORT("Item_Transport");
    private final String value;

    OrderType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static OrderType fromValue(String v) {
        for (OrderType c: OrderType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
