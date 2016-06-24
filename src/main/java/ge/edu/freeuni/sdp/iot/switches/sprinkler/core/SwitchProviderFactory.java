package ge.edu.freeuni.sdp.iot.switches.sprinkler.core;

/**
 * Created by Nika Doghonadze
 */
public class SwitchProviderFactory {
    private static SwitchProvider instance;

    public synchronized static SwitchProvider getSwitchProviderInstance() {
        if (instance == null)
            instance = new ShadowProviderStub(new HouseRegistryProxy());

        return instance;
    }
}
