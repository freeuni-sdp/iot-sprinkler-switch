package ge.edu.freeuni.sdp.iot.switches.sprinkler.services;

import ge.edu.freeuni.sdp.iot.switches.sprinkler.core.*;
import ge.edu.freeuni.sdp.iot.switches.sprinkler.model.SwitchCommand;
import ge.edu.freeuni.sdp.iot.switches.sprinkler.model.SwitchStatus;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by Nika Doghonadze
 */
@Path("/houses")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class SwitchService {

    protected SwitchProvider getProvider() {
        return SwitchProviderFactory.getSwitchProviderInstance();
    }


    @GET
    @Path("{id}")
    public SwitchStatus get(@PathParam("id") String houseId) {
        SwitchProvider provider = getProvider();
        SwitchStatus switchStatus = provider.findSwitchStatus(houseId);

        if (switchStatus == null)
            throw new NotFoundException();

        return switchStatus;
    }

    @PUT
    @Path("{id}")
    public SwitchStatus put(@PathParam("id") String houseId, SwitchCommand order) {
        if (!order.isValid())
            throw new BadRequestException();

        SwitchProvider provider = getProvider();
        SwitchStatus switchStatus = provider.findSwitchStatus(houseId);

        if (switchStatus == null)
            throw new NotFoundException();

        switchStatus.setStatus(order.getChangeStatusTo());

        if (order.getChangeStatusTo().equals("on")) {
            switchStatus.setSecondsLeft(order.getTimeout());
        } else {
            switchStatus.setSecondsLeft(null);
        }

        return provider.updateSwitchStatus(switchStatus);
    }

}
