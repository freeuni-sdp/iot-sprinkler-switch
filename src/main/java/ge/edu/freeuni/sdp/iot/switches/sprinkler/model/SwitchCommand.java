package ge.edu.freeuni.sdp.iot.switches.sprinkler.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONObject;

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
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("set_status", getChangeStatusTo());
        if (getTimeout() != null) {
            jsonObject.put("timeout", getTimeout());
        }
        return jsonObject.toString();
    }

    public static SwitchCommand fromStatus(SwitchStatus switchStatus) {
        SwitchCommand res = new SwitchCommand();
        res.setChangeStatusTo(switchStatus.getStatus());

        if (switchStatus.getSecondsLeft() != null)
            res.setTimeout(switchStatus.getSecondsLeft());

        return res;
    }
}
