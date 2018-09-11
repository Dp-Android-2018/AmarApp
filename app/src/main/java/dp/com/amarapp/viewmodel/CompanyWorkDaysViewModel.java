package dp.com.amarapp.viewmodel;

import android.app.Activity;
import android.content.Context;

import java.util.List;

import dp.com.amarapp.model.pojo.WorkDay;
import dp.com.amarapp.utils.ConfigurationFile;

public class CompanyWorkDaysViewModel {
    private Context context;
    private List<WorkDay> workDays;
    public CompanyWorkDaysViewModel(Context context) {
        this.context = context;
        workDays=(List<WorkDay>)((Activity)context).getIntent().getSerializableExtra(ConfigurationFile.IntentConstants.COMPANY_WORK_DAYS);
        //System.out.println("Work day List on View Model :"+workDayList.size());
    }
}
