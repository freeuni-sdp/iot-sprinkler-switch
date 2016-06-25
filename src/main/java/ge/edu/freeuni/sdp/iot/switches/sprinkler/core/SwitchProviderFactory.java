package ge.edu.freeuni.sdp.iot.switches.sprinkler.core;

import javax.ws.rs.client.ClientBuilder;

/**
 * Created by Nika Doghonadze
 */
public class SwitchProviderFactory {
    private static SwitchProvider instance;

    public synchronized static SwitchProvider getSwitchProviderInstance() {
        if (instance == null) {
            String appUri = "https://iot-garden-simulator.herokuapp.com";
            instance = new ShadowProviderSimulator(
                    new HouseRegistryProxy(),
                    appUri,
                    ClientBuilder.newClient());
        }
        
        return instance;
    }
}
