import ge.edu.freeuni.sdp.iot.switches.sprinkler.core.SwitchProvider;
import ge.edu.freeuni.sdp.iot.switches.sprinkler.model.SwitchStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nika Doghonadze
 */
public class FakeSwitchProvider implements SwitchProvider {
    private static FakeSwitchProvider instance;

    public synchronized static FakeSwitchProvider instance() {
        if (instance == null)
            instance = new FakeSwitchProvider(new HashMap<String, SwitchStatus>());

        return instance;
    }

    private Map<String, SwitchStatus> map;
    public FakeSwitchProvider(Map<String, SwitchStatus> map) {
        this.map = map;
    }

    @Override
    public SwitchStatus findSwitchStatus(String houseId) {
        if (map.containsKey(houseId))
            return map.get(houseId);
        return null;
    }

    @Override
    public SwitchStatus updateSwitchStatus(SwitchStatus switchStatus) {
        map.put(switchStatus.getHouseId(), switchStatus);
        return map.get(switchStatus.getHouseId());
    }

    public void clear() {
        map.clear();
    }

    public boolean contains(String houseId) {
        return map.containsKey(houseId);
    }
}
