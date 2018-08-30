package dp.com.amarapp.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import dp.com.amarapp.R;
import dp.com.amarapp.databinding.ActivityCompanyWorkDaysBinding;
import dp.com.amarapp.viewmodel.CompanyWorkDaysViewModel;

public class CompanyWorkDaysActivity extends AppCompatActivity {

    private CompanyWorkDaysViewModel workDaysViewModel;
    private ActivityCompanyWorkDaysBinding workDaysBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
    }

    public void initBinding(){
        workDaysViewModel=new CompanyWorkDaysViewModel(CompanyWorkDaysActivity.this);
        workDaysBinding= DataBindingUtil.setContentView(CompanyWorkDaysActivity.this, R.layout.activity_company_work_days);
        workDaysBinding.setWorkDaysViewModel(workDaysViewModel);
    }
}
