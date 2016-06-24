package ge.edu.freeuni.sdp.iot.switches.sprinkler.model;

import org.json.JSONObject;

/**
 * Created by Nika Doghonadze
 */
public class HouseData {
    private String houseId;
    private String switchIpAddress;

    public HouseData(String houseId, String switchIpAddress) {
        this.houseId = houseId;
        this.switchIpAddress = switchIpAddress;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getSwitchIpAddress() {
        return switchIpAddress;
    }

    public void setSwitchIpAddress(String switchIpAddress) {
        this.switchIpAddress = switchIpAddress;
    }

    public static HouseData fromJson(JSONObject jsonObject) {
        String houseId = jsonObject.getJSONObject("RowKey").getString("_");
        String switchIpAddress = jsonObject.getJSONObject("sprinkler_ip").getString("_");
        return new HouseData(houseId, switchIpAddress);
    }
}
