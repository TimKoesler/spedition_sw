
package partner.zeitarbeit;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse f\u00fcr orderStatus.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="orderStatus"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="New"/&gt;
 *     &lt;enumeration value="Planned"/&gt;
 *     &lt;enumeration value="inProgess"/&gt;
 *     &lt;enumeration value="Done"/&gt;
 *     &lt;enumeration value="Cancelled"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "orderStatus")
@XmlEnum
public enum OrderStatus {

    @XmlEnumValue("New")
    NEW("New"),
    @XmlEnumValue("Planned")
    PLANNED("Planned"),
    @XmlEnumValue("inProgess")
    IN_PROGESS("inProgess"),
    @XmlEnumValue("Done")
    DONE("Done"),
    @XmlEnumValue("Cancelled")
    CANCELLED("Cancelled");
    private final String value;

    OrderStatus(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static OrderStatus fromValue(String v) {
        for (OrderStatus c: OrderStatus.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
