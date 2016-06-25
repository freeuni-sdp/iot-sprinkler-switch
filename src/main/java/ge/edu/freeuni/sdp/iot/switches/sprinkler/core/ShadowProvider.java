package ge.edu.freeuni.sdp.iot.switches.sprinkler.core;

import ge.edu.freeuni.sdp.iot.switches.sprinkler.model.HouseData;
import ge.edu.freeuni.sdp.iot.switches.sprinkler.model.SwitchStatus;

/**
 * Created by Nika Doghonadze
 */
public abstract class ShadowProvider implements SwitchProvider {
    private HouseRegistry houseRegistry;

    public ShadowProvider(HouseRegistry houseRegistry) {
        this.houseRegistry = houseRegistry;
    }

    @Override
    public SwitchStatus findSwitchStatus(String houseId) {
        HouseData houseData = houseRegistry.getHouseData(houseId);
        if (houseData == null)
            return null;

        return readSwitchStatus(houseData);
    }


    @Override
    public SwitchStatus updateSwitchStatus(SwitchStatus switchStatus) {
        HouseData houseData = houseRegistry.getHouseData(switchStatus.getHouseId());
        if (houseData == null)
            return null;

        return sendSwitchCommand(houseData, switchStatus);
    }

    protected abstract SwitchStatus readSwitchStatus(HouseData houseData);
    protected abstract SwitchStatus sendSwitchCommand(HouseData houseData, SwitchStatus switchStatus);
}
