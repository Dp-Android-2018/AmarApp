package dp.com.amarapp.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import dp.com.amarapp.R;
import dp.com.amarapp.databinding.ActivityCompanyWorkDaysBinding;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.viewmodel.CompanyWorkDaysViewModel;
import dp.com.amarapp.viewmodel.ToolbarViewModel;

public class CompanyWorkDaysActivity extends BaseActivity {

    private CompanyWorkDaysViewModel workDaysViewModel;
    private ActivityCompanyWorkDaysBinding workDaysBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
        setUpActionBar();
    }

    public void initBinding(){
        workDaysViewModel=new CompanyWorkDaysViewModel(CompanyWorkDaysActivity.this);
        workDaysBinding= DataBindingUtil.setContentView(CompanyWorkDaysActivity.this, R.layout.activity_company_work_days);
        workDaysBinding.setWorkDaysViewModel(workDaysViewModel);
    }
    public void setUpActionBar(){
        setSupportActionBar( workDaysBinding.toolbar.toolbar);
        workDaysBinding.toolbar.toolbar.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        workDaysBinding.toolbar.setViewmodel(new ToolbarViewModel(CompanyWorkDaysActivity.this, ConfigurationFile.Constants.BACK_IMAGE_VISIBILITY_CODE));
    }
}
