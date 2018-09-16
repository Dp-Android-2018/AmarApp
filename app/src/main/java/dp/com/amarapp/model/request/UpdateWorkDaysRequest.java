package dp.com.amarapp.model.request;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import dp.com.amarapp.model.pojo.WorkDay;

public class UpdateWorkDaysRequest {

    @SerializedName("days")
    private List<WorkDay> days;

    public List<WorkDay> getDays() {
        return days;
    }

    public void setDays(List<WorkDay> days) {
        this.days = days;
    }
}
