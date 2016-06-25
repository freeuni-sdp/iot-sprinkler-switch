package ge.edu.freeuni.sdp.iot.switches.sprinkler.core;

import ge.edu.freeuni.sdp.iot.switches.sprinkler.model.HouseData;
import ge.edu.freeuni.sdp.iot.switches.sprinkler.model.SwitchCommand;
import ge.edu.freeuni.sdp.iot.switches.sprinkler.model.SwitchStatus;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Nika Doghonadze
 */
public class ShadowProviderReal extends ShadowProvider {
    private Client client;

    public ShadowProviderReal(HouseRegistry houseRegistry, Client client) {
        super(houseRegistry);
        this.client = client;
    }

    @Override
    protected SwitchStatus readSwitchStatus(HouseData houseData) {
        String url = getUrl(houseData);
        Response response =
                client.target(url)
                        .request(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .get();

        if (response.getStatus() != Response.Status.OK.getStatusCode())
            return null;

        return response.readEntity(SwitchStatus.class);
    }

    @Override
    protected SwitchStatus sendSwitchCommand(HouseData houseData, SwitchCommand switchCommand) {
        String url = getUrl(houseData);

        Response response =
                client.target(url)
                        .request(MediaType.APPLICATION_JSON)
                        .put(Entity.json(switchCommand.toString()));

        if (response.getStatus() != Response.Status.OK.getStatusCode())
            return null;

        return response.readEntity(SwitchStatus.class);
    }


    protected String getUrl(HouseData houseData) {
        return "https://" + houseData.getSwitchIpAddress() +
                "/webapi/houses/" + houseData.getHouseId() +
                "/sprinkler-switch";
    }
}
