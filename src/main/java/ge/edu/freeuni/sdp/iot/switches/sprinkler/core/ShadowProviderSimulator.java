package ge.edu.freeuni.sdp.iot.switches.sprinkler.core;

import ge.edu.freeuni.sdp.iot.switches.sprinkler.model.HouseData;
import ge.edu.freeuni.sdp.iot.switches.sprinkler.model.SwitchStatus;

/**
 * Created by Nika Doghonadze
 */
public class ShadowProviderSimulator extends ShadowProvider {

    public ShadowProviderSimulator(HouseRegistry houseRegistry) {
        super(houseRegistry);
    }

    @Override
    protected SwitchStatus readSwitchStatus(HouseData houseData) {
        //TODO READ DATA FROM SIMULATOR
        return null;
    }

    @Override
    protected void sendSwitchCommand(HouseData houseData, SwitchStatus switchStatus) {
        //TODO SEND COMMAND TO SIMULATOR
    }
}
