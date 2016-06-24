package ge.edu.freeuni.sdp.iot.switches.sprinkler.core;

import ge.edu.freeuni.sdp.iot.switches.sprinkler.model.HouseData;
import ge.edu.freeuni.sdp.iot.switches.sprinkler.model.SwitchStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Nika Doghonadze
 */
public class ShadowProviderStub extends ShadowProvider {
    private Map<String, SwitchStatus> map;


    public ShadowProviderStub(HouseRegistry houseRegistry) {
        super(houseRegistry);
        map = new HashMap<>();

        List<HouseData> allHouses = houseRegistry.getAllHouses();

        for (HouseData house : allHouses) {
            map.put(house.getHouseId(), new SwitchStatus(house.getHouseId(), "off", null));
        }
    }

    @Override
    protected SwitchStatus readSwitchStatus(HouseData houseData) {
        if (map.containsKey(houseData.getHouseId()))
            return map.get(houseData.getHouseId());
        return null;
    }

    @Override
    protected void sendSwitchCommand(HouseData houseData, SwitchStatus switchStatus) {
        map.put(houseData.getHouseId(), switchStatus);
    }
}
