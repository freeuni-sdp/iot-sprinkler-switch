package ge.edu.freeuni.sdp.iot.switches.sprinkler.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Nika Doghonadze
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SwitchCommand {

    @JsonProperty(value = "set_status", required = true)
    private String changeStatusTo;

    @JsonProperty("timeout")
    private Integer timeout = 60;

    public SwitchCommand() {

    }

    public SwitchCommand(String changeStatusTo, Integer timeout) {
        this.changeStatusTo = changeStatusTo;
        this.timeout = timeout;
    }

    public String getChangeStatusTo() {
        return changeStatusTo;
    }

    public void setChangeStatusTo(String changeStatusTo) {
        this.changeStatusTo = changeStatusTo;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public boolean isValid() {
        if (!changeStatusTo.equals("on") && !changeStatusTo.equals("off"))
            return false;

        if (timeout < 0)
            return false;

        return true;
    }

    @Override
    public String toString() {
        return "{\n" +
                "    \"set_status\": \""+ getChangeStatusTo() +"\",\n" +
                "    \"timeout\": "+ getTimeout()+ "\n" +
                "}";
    }

    public static SwitchCommand fromStatus(SwitchStatus switchStatus) {
       return new SwitchCommand(switchStatus.getStatus(), switchStatus.getSecondsLeft());
    }
}
