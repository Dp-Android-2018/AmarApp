package dp.com.amarapp.model.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class WorkDay implements Parcelable,Serializable{

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

    public WorkDay() {
    }

    public WorkDay(int id, String day, String from, String to, String shift) {
        this.id = id;
        this.day = day;
        this.from = from;
        this.to = to;
        this.shift = shift;
    }

    protected WorkDay(Parcel in) {
        id = in.readInt();
        day = in.readString();
        from = in.readString();
        to = in.readString();
        shift = in.readString();
    }

    public static final Creator<WorkDay> CREATOR = new Creator<WorkDay>() {
        @Override
        public WorkDay createFromParcel(Parcel in) {
            return new WorkDay(in);
        }

        @Override
        public WorkDay[] newArray(int size) {
            return new WorkDay[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(day);
        dest.writeString(from);
        dest.writeString(to);
        dest.writeString(shift);
    }
}
