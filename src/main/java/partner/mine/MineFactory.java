package partner.mine;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class MineFactory {

    @Produces
    @ApplicationScoped
    public MinesService createMineService() {
        final MinesServiceService minesServiceService = new MinesServiceService();
        return minesServiceService.getMinesServicePort();
    }
}
