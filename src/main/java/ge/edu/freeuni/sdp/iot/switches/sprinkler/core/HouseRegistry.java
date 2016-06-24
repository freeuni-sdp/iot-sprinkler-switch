package ge.edu.freeuni.sdp.iot.switches.sprinkler.core;

import ge.edu.freeuni.sdp.iot.switches.sprinkler.model.HouseData;

import java.util.List;

/**
 * Created by Nika Doghonadze
 */
interface HouseRegistry {
    List<HouseData> getAllHouses();
    HouseData getHouseData(String houseId);
}
