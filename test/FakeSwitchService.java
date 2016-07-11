import ge.edu.freeuni.sdp.iot.switches.sprinkler.core.SwitchProvider;
import ge.edu.freeuni.sdp.iot.switches.sprinkler.services.SwitchService;

/**
 * Created by Nika Doghonadze
 */
public class FakeSwitchService extends SwitchService {
    @Override
    protected SwitchProvider getProvider() {
        return FakeSwitchProvider.instance();
    }
}
