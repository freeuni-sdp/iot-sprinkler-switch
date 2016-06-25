package ge.edu.freeuni.sdp.iot.switches.sprinkler.core;

import ge.edu.freeuni.sdp.iot.switches.sprinkler.model.HouseData;

import javax.ws.rs.client.Client;

/**
 * Created by Nika Doghonadze
 */
public class ShadowProviderSimulator extends ShadowProviderReal {
    private String appUrl;

    public ShadowProviderSimulator(HouseRegistry houseRegistry, String appUrl, Client client) {
        super(houseRegistry, client);
        this.appUrl = appUrl;
    }

    @Override
    protected String getUrl(HouseData houseData) {
        return  appUrl + "/webapi/houses/" + houseData.getHouseId() +
                "/sprinkler-switch";
    }
}
