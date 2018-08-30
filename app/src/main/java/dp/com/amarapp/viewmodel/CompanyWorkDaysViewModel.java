package dp.com.amarapp.viewmodel;

import android.app.Activity;
import android.content.Context;

import java.util.List;

import dp.com.amarapp.model.pojo.WorkDay;
import dp.com.amarapp.model.response.CompanyLoginResponse;
import dp.com.amarapp.utils.ConfigurationFile;

public class CompanyWorkDaysViewModel {
    private Context context;
    private CompanyLoginResponse companyLoginResponse;
    private List<WorkDay> workDayList;
    public CompanyWorkDaysViewModel(Context context) {
        this.context = context;
        companyLoginResponse=(CompanyLoginResponse) ((Activity)context).getIntent().getSerializableExtra(ConfigurationFile.IntentConstants.COMPANYITEMINFO);
        workDayList=companyLoginResponse.getWorkDays();
        //System.out.println("Work day List on View Model :"+workDayList.size());
    }
}
