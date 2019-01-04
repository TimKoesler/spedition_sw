
package partner.zeitarbeit;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java-Klasse f\u00fcr createOrder complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="createOrder"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Username" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="OrderType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PickUpAddress" type="{http://services.zeitarbeit.othr.de/}address" minOccurs="0"/&gt;
 *         &lt;element name="DeliveryAddress" type="{http://services.zeitarbeit.othr.de/}address" minOccurs="0"/&gt;
 *         &lt;element name="LineItems" type="{http://services.zeitarbeit.othr.de/}lineItem" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="AmountKG" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="Date" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createOrder", propOrder = {
    "username",
    "password",
    "orderType",
    "pickUpAddress",
    "deliveryAddress",
    "lineItems",
    "amountKG",
    "date"
})
public class CreateOrder {

    @XmlElement(name = "Username")
    protected String username;
    @XmlElement(name = "Password")
    protected String password;
    @XmlElement(name = "OrderType")
    protected String orderType;
    @XmlElement(name = "PickUpAddress")
    protected Address pickUpAddress;
    @XmlElement(name = "DeliveryAddress")
    protected Address deliveryAddress;
    @XmlElement(name = "LineItems")
    protected List<LineItem> lineItems;
    @XmlElement(name = "AmountKG")
    protected int amountKG;
    @XmlElement(name = "Date")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar date;

    /**
     * Ruft den Wert der username-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsername() {
        return username;
    }

    /**
     * Legt den Wert der username-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsername(String value) {
        this.username = value;
    }

    /**
     * Ruft den Wert der password-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Legt den Wert der password-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Ruft den Wert der orderType-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderType() {
        return orderType;
    }

    /**
     * Legt den Wert der orderType-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderType(String value) {
        this.orderType = value;
    }

    /**
     * Ruft den Wert der pickUpAddress-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Address }
     *     
     */
    public Address getPickUpAddress() {
        return pickUpAddress;
    }

    /**
     * Legt den Wert der pickUpAddress-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Address }
     *     
     */
    public void setPickUpAddress(Address value) {
        this.pickUpAddress = value;
    }

    /**
     * Ruft den Wert der deliveryAddress-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Address }
     *     
     */
    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    /**
     * Legt den Wert der deliveryAddress-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Address }
     *     
     */
    public void setDeliveryAddress(Address value) {
        this.deliveryAddress = value;
    }

    /**
     * Gets the value of the lineItems property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the lineItems property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLineItems().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LineItem }
     * 
     * 
     */
    public List<LineItem> getLineItems() {
        if (lineItems == null) {
            lineItems = new ArrayList<LineItem>();
        }
        return this.lineItems;
    }

    /**
     * Ruft den Wert der amountKG-Eigenschaft ab.
     * 
     */
    public int getAmountKG() {
        return amountKG;
    }

    /**
     * Legt den Wert der amountKG-Eigenschaft fest.
     * 
     */
    public void setAmountKG(int value) {
        this.amountKG = value;
    }

    /**
     * Ruft den Wert der date-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDate() {
        return date;
    }

    /**
     * Legt den Wert der date-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDate(XMLGregorianCalendar value) {
        this.date = value;
    }

}
