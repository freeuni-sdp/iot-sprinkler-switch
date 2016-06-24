package ge.edu.freeuni.sdp.iot.switches.sprinkler.core;

import ge.edu.freeuni.sdp.iot.switches.sprinkler.model.SwitchStatus;

/**
 * Created by Nika Doghonadze
 */
public interface SwitchProvider {
    SwitchStatus findSwitchStatus(String houseId);
    void updateSwitchStatus(SwitchStatus switchStatus);
}
