package database;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Alex on 4/22/2018.
 */
@Entity
@Table(name = "guest")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Guest {

    @Id
    @JsonProperty
    private String id;

    @JsonProperty
    private String first;

    @JsonProperty
    private String last;

    @JsonProperty
    private double fee;

    public Guest() {
        this.id = randId();
    }

    public Guest(String first, String last) {
        this.first = first;
        this.last = last; this.fee = 0.0;
        this.id = randId();
    }

    public Guest(String id, String first, String last, double fee) {
        this.first = first; this.id = id;
        this.last = last;   this.fee = fee;
    }

    @JsonIgnore
    public String getId() { return id; }
    public void setId(String id) {this.id = id; }

    @JsonIgnore
    public String getFirst() { return first; }
    public void setFirst(String first) { this.first = first; }

    @JsonIgnore
    public String getLast() { return last; }
    public void setLast(String last) { this.last = last; }

    @JsonIgnore
    public double getFee() { return fee; }
    public void setFee(double fee) { this.fee = fee; }

    public void addFee(double fee) {
        this.fee += fee;
    }

    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = UPPER.toLowerCase();
    private static final String DIGITS = "0123456789";
    private static final String ALPHA_NUM = UPPER + LOWER + DIGITS;

    private static String randId() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 8; ++i)
            str.append(ALPHA_NUM.charAt((int) (Math.random() * ALPHA_NUM.length())));
        return str.toString();
    }
}
