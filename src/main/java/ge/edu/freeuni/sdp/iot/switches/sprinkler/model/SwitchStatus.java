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

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (obj == this)
            return true;

        if (getClass() != obj.getClass())
            return false;

        SwitchStatus other = (SwitchStatus) obj;

        if (houseId == null) {
            if (other.houseId != null)
                return false;
        } else if (!houseId.equals(other.houseId)) {
            return false;
        }

        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status)) {
            return false;
        }

        if (secondsLeft == null) {
            if (other.secondsLeft != null && other.secondsLeft != 0) {
                return false;
            }
        } else if (!secondsLeft.equals(other.secondsLeft) && other.secondsLeft != 0) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((houseId == null) ? 0 : houseId.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((secondsLeft == null) ? 0 : secondsLeft.hashCode());
        return result;
    }

}
