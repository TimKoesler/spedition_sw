
package partner.zeitarbeit;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the partner.zeitarbeit package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _PickUp_QNAME = new QName("http://services.zeitarbeit.othr.de/", "pickUp");
    private final static QName _PickUpResponse_QNAME = new QName("http://services.zeitarbeit.othr.de/", "pickUpResponse");
    private final static QName _LoginException_QNAME = new QName("http://service.koesler.sw.othr/", "LoginException");
    private final static QName _CreateOrder_QNAME = new QName("http://service.koesler.sw.othr/", "createOrder");
    private final static QName _CreateOrderResponse_QNAME = new QName("http://service.koesler.sw.othr/", "createOrderResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: partner.zeitarbeit
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PickUp }
     * 
     */
    public PickUp createPickUp() {
        return new PickUp();
    }

    /**
     * Create an instance of {@link PickUpResponse }
     * 
     */
    public PickUpResponse createPickUpResponse() {
        return new PickUpResponse();
    }

    /**
     * Create an instance of {@link Order }
     * 
     */
    public Order createOrder() {
        return new Order();
    }

    /**
     * Create an instance of {@link GeneratedIdEntity }
     * 
     */
    public GeneratedIdEntity createGeneratedIdEntity() {
        return new GeneratedIdEntity();
    }

    /**
     * Create an instance of {@link Customer }
     * 
     */
    public Customer createCustomer() {
        return new Customer();
    }

    /**
     * Create an instance of {@link Address }
     * 
     */
    public Address createAddress() {
        return new Address();
    }

    /**
     * Create an instance of {@link StringIdEntity }
     * 
     */
    public StringIdEntity createStringIdEntity() {
        return new StringIdEntity();
    }

    /**
     * Create an instance of {@link LineItem }
     * 
     */
    public LineItem createLineItem() {
        return new LineItem();
    }

    /**
     * Create an instance of {@link Worker }
     * 
     */
    public Worker createWorker() {
        return new Worker();
    }

    /**
     * Create an instance of {@link Experience }
     * 
     */
    public Experience createExperience() {
        return new Experience();
    }

    /**
     * Create an instance of {@link Job }
     * 
     */
    public Job createJob() {
        return new Job();
    }

    /**
     * Create an instance of {@link CreateOrderResponse }
     * 
     */
    public CreateOrderResponse createCreateOrderResponse() {
        return new CreateOrderResponse();
    }

    /**
     * Create an instance of {@link CreateOrder }
     * 
     */
    public CreateOrder createCreateOrder() {
        return new CreateOrder();
    }

    /**
     * Create an instance of {@link LoginException }
     * 
     */
    public LoginException createLoginException() {
        return new LoginException();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PickUp }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PickUp }{@code >}
     */
    @XmlElementDecl(namespace = "http://services.zeitarbeit.othr.de/", name = "pickUp")
    public JAXBElement<PickUp> createPickUp(PickUp value) {
        return new JAXBElement<PickUp>(_PickUp_QNAME, PickUp.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PickUpResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PickUpResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://services.zeitarbeit.othr.de/", name = "pickUpResponse")
    public JAXBElement<PickUpResponse> createPickUpResponse(PickUpResponse value) {
        return new JAXBElement<PickUpResponse>(_PickUpResponse_QNAME, PickUpResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoginException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link LoginException }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.koesler.sw.othr/", name = "LoginException")
    public JAXBElement<LoginException> createLoginException(LoginException value) {
        return new JAXBElement<LoginException>(_LoginException_QNAME, LoginException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateOrder }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CreateOrder }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.koesler.sw.othr/", name = "createOrder")
    public JAXBElement<CreateOrder> createCreateOrder(CreateOrder value) {
        return new JAXBElement<CreateOrder>(_CreateOrder_QNAME, CreateOrder.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateOrderResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CreateOrderResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.koesler.sw.othr/", name = "createOrderResponse")
    public JAXBElement<CreateOrderResponse> createCreateOrderResponse(CreateOrderResponse value) {
        return new JAXBElement<CreateOrderResponse>(_CreateOrderResponse_QNAME, CreateOrderResponse.class, null, value);
    }

}
