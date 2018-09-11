package dp.com.amarapp.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import dp.com.amarapp.model.pojo.WorkDay;

public class CompanyWorkDaysResponse  {
    @SerializedName("data")
    private List<WorkDay> workDays;

    public List<WorkDay> getWorkDays() {
        return workDays;
    }

    public void setWorkDays(List<WorkDay> workDays) {
        this.workDays = workDays;
    }
}
