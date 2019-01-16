package partner.zeitarbeit;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class ZeitarbeitFactory {

    @Produces
    @ApplicationScoped
    public PickupService createPickUpService() {
        final PickupServiceService pickupServiceService = new PickupServiceService();
        return pickupServiceService.getPickupServicePort();
    }
}
