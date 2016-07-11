import ge.edu.freeuni.sdp.iot.switches.sprinkler.model.SwitchCommand;
import ge.edu.freeuni.sdp.iot.switches.sprinkler.model.SwitchStatus;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Nika Doghonadze
 */
public class SwitchServiceTest extends JerseyTest {
    @Override
    protected Application configure() {
        return new ResourceConfig(FakeSwitchService.class);
    }

    @Test(expected = NotFoundException.class)
    public void get_status_empty_map_expect_not_found() throws Exception{
        FakeSwitchProvider.instance().clear();

        SwitchStatus res =
                target("houses/gf")
                .request()
                .get(SwitchStatus.class);

        assertEquals(null, res);
    }

    @Test(expected = NotFoundException.class)
    public void get_status_one_elem_map_expect_not_found() throws Exception {
        SwitchStatus offStatus = new SwitchStatus("asd", "off", 0);

        FakeSwitchProvider.instance().clear();
        FakeSwitchProvider.instance().updateSwitchStatus(offStatus);

        SwitchStatus res =
                target("houses/" + UUID.randomUUID().toString())
                        .request()
                        .get(SwitchStatus.class);

        assertTrue(res.equals(offStatus));
    }


    @Test
    public void get_status_expect_off() throws Exception {
        SwitchStatus offStatus = new SwitchStatus("asd", "off", 0);

        FakeSwitchProvider.instance().clear();
        FakeSwitchProvider.instance().updateSwitchStatus(offStatus);

        SwitchStatus res =
                target("houses/asd")
                        .request()
                        .get(SwitchStatus.class);

        assertTrue(res.equals(offStatus));
    }

    @Test
    public void get_status_expect_on_20_secs() throws Exception {
        String id = UUID.randomUUID().toString();
        SwitchStatus onStatus = new SwitchStatus(id, "on", 20);

        FakeSwitchProvider.instance().clear();
        FakeSwitchProvider.instance().updateSwitchStatus(onStatus);

        SwitchStatus res =
                target("houses/" + id)
                        .request()
                        .get(SwitchStatus.class);

        assertTrue(res.equals(onStatus));
    }


    @Test
    public void put_status_expect_off() throws Exception {
        String id = UUID.randomUUID().toString();

        SwitchStatus onStatus = new SwitchStatus(id, "on", 10);
        FakeSwitchProvider.instance().updateSwitchStatus(onStatus);


        SwitchCommand command = new SwitchCommand("off", 0);
        SwitchStatus res =
                target("houses/" + id)
                        .request()
                        .put(Entity.entity(command.toString(), MediaType.APPLICATION_JSON), SwitchStatus.class);


        SwitchStatus offStatus = new SwitchStatus(id, "off", 0);
        assertTrue(res.equals(offStatus));
    }


    @Test
    public void put_status_expect_on_12_secs() throws Exception {
        String id = UUID.randomUUID().toString();

        SwitchStatus onStatus = new SwitchStatus(id, "on", 1);
        FakeSwitchProvider.instance().updateSwitchStatus(onStatus);


        SwitchCommand command = new SwitchCommand("on", 12);
        SwitchStatus res =
                target("houses/" + id)
                        .request()
                        .put(Entity.entity(command.toString(), MediaType.APPLICATION_JSON), SwitchStatus.class);


        SwitchStatus expectedStatus = new SwitchStatus(id, "on", 12);
        assertTrue(res.equals(expectedStatus));
    }

    @Test
    public void put_status_expect_provider_on_12_secs() throws Exception {
        String id = UUID.randomUUID().toString();

        SwitchStatus onStatus = new SwitchStatus(id, "on", 1);
        FakeSwitchProvider.instance().updateSwitchStatus(onStatus);


        SwitchCommand command = new SwitchCommand("on", 12);
        target("houses/" + id)
                .request()
                .put(Entity.entity(command.toString(), MediaType.APPLICATION_JSON), SwitchStatus.class);


        SwitchStatus expectedStatus = new SwitchStatus(id, "on", 12);
        assertTrue(FakeSwitchProvider.instance().findSwitchStatus(id).equals(expectedStatus));
    }


    @Test(expected = NotFoundException.class)
    public void put_status_on_expect_not_found() throws Exception {
        FakeSwitchProvider.instance().clear();

        SwitchCommand command = new SwitchCommand("on", 12);
        target("houses/asdasd")
                .request()
                .put(Entity.entity(command.toString(), MediaType.APPLICATION_JSON), SwitchStatus.class);
    }


    @Test(expected = NotFoundException.class)
    public void put_status_off_expect_not_found() throws Exception {
        FakeSwitchProvider.instance().clear();

        SwitchCommand command = new SwitchCommand("off", 0);
        target("houses/asdasd")
                .request()
                .put(Entity.entity(command.toString(), MediaType.APPLICATION_JSON), SwitchStatus.class);
    }

    @Test
    public void put_status_on_from_off_default_value() throws Exception {
        String id = UUID.randomUUID().toString();

        SwitchStatus onStatus = new SwitchStatus(id, "off", 0);
        FakeSwitchProvider.instance().updateSwitchStatus(onStatus);

        SwitchCommand command = new SwitchCommand("on", null);
        SwitchStatus res = target("houses/" + id)
                .request()
                .put(Entity.entity(command.toString(), MediaType.APPLICATION_JSON), SwitchStatus.class);


        SwitchStatus expectedStatus = new SwitchStatus(id, "on", 60);
        assertTrue(FakeSwitchProvider.instance().findSwitchStatus(id).equals(expectedStatus));
        assertTrue(res.equals(expectedStatus));
    }


    @Test
    public void put_status_on_from_on_default_value() throws Exception {
        String id = UUID.randomUUID().toString();

        SwitchStatus onStatus = new SwitchStatus(id, "on", 23);
        FakeSwitchProvider.instance().updateSwitchStatus(onStatus);

        SwitchCommand command = new SwitchCommand("on", null);
        SwitchStatus res = target("houses/" + id)
                .request()
                .put(Entity.entity(command.toString(), MediaType.APPLICATION_JSON), SwitchStatus.class);


        SwitchStatus expectedStatus = new SwitchStatus(id, "on", 60);
        assertTrue(FakeSwitchProvider.instance().findSwitchStatus(id).equals(expectedStatus));
        assertTrue(res.equals(expectedStatus));
    }
}
