package skeletons;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Alex on 4/23/2018.
 */
public class DateObject {

    @JsonProperty
    private long days;

    @JsonProperty
    private long weeks;

    @JsonProperty
    private double fee;

    public DateObject(long days) {
        this.days = days % 7;
        this.weeks = days / 7;
        this.fee = this.weeks * 0.25;
    }

    @JsonIgnore
    public double getFee() { return fee; }
}
