
package partner.zeitarbeit;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.0
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "PickupServiceService", targetNamespace = "http://services.zeitarbeit.othr.de/", wsdlLocation = "http://im-lamport.oth-regensburg.de:8080/zeitarbeit-1.0-SNAPSHOT/PickupService?wsdl")
public class PickupServiceService
    extends Service
{

    private final static URL PICKUPSERVICESERVICE_WSDL_LOCATION;
    private final static WebServiceException PICKUPSERVICESERVICE_EXCEPTION;
    private final static QName PICKUPSERVICESERVICE_QNAME = new QName("http://services.zeitarbeit.othr.de/", "PickupServiceService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://im-lamport.oth-regensburg.de:8080/zeitarbeit-1.0-SNAPSHOT/PickupService?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        PICKUPSERVICESERVICE_WSDL_LOCATION = url;
        PICKUPSERVICESERVICE_EXCEPTION = e;
    }

    public PickupServiceService() {
        super(__getWsdlLocation(), PICKUPSERVICESERVICE_QNAME);
    }

    public PickupServiceService(WebServiceFeature... features) {
        super(__getWsdlLocation(), PICKUPSERVICESERVICE_QNAME, features);
    }

    public PickupServiceService(URL wsdlLocation) {
        super(wsdlLocation, PICKUPSERVICESERVICE_QNAME);
    }

    public PickupServiceService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, PICKUPSERVICESERVICE_QNAME, features);
    }

    public PickupServiceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public PickupServiceService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns PickupService
     */
    @WebEndpoint(name = "PickupServicePort")
    public PickupService getPickupServicePort() {
        return super.getPort(new QName("http://services.zeitarbeit.othr.de/", "PickupServicePort"), PickupService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns PickupService
     */
    @WebEndpoint(name = "PickupServicePort")
    public PickupService getPickupServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://services.zeitarbeit.othr.de/", "PickupServicePort"), PickupService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (PICKUPSERVICESERVICE_EXCEPTION!= null) {
            throw PICKUPSERVICESERVICE_EXCEPTION;
        }
        return PICKUPSERVICESERVICE_WSDL_LOCATION;
    }

}
