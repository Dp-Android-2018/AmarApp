package dp.com.amarapp.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class WorkDay implements Serializable{

    @SerializedName("id")
    private int id;

    @SerializedName("day")
    private String day;

    @SerializedName("from")
    private String from;

    @SerializedName("to")
    private String to;

    @SerializedName("shift")
    private String shift;

    public void setId(int id) {
        this.id = id;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public int getId() {
        return id;
    }

    public String getDay() {
        return day;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getShift() {
        return shift;
    }
}
