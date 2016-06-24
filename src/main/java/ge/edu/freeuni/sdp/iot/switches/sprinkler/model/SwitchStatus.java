package ge.edu.freeuni.sdp.iot.switches.sprinkler.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Nika Doghonadze
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SwitchStatus {

    @JsonProperty("house_id")
    private String houseId;

    @JsonProperty("status")
    private String status;

    @JsonProperty("seconds_left")
    private Integer secondsLeft;


    public SwitchStatus() {

    }

    public SwitchStatus(String houseId, String status, Integer secondsLeft) {
        this.houseId = houseId;
        this.status = status;
        this.secondsLeft = secondsLeft;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getSecondsLeft() {
        return secondsLeft;
    }

    public void setSecondsLeft(Integer secondsLeft) {
        this.secondsLeft = secondsLeft;
    }
}
