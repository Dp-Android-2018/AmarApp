package dp.com.amarapp.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import dp.com.amarapp.R;
import dp.com.amarapp.databinding.ActivityCompanyInformationBinding;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.viewmodel.CompanyInformationViewModel;
import dp.com.amarapp.viewmodel.ToolbarViewModel;

public class CompanyInformationActivity extends BaseActivity {
    private CompanyInformationViewModel informationViewModel;
    private ActivityCompanyInformationBinding informationBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
    }

    public void initBinding(){
        informationViewModel=new CompanyInformationViewModel(CompanyInformationActivity.this);
        informationBinding= DataBindingUtil.setContentView(CompanyInformationActivity.this, R.layout.activity_company_information);
        informationBinding.setCompanyInformation(informationViewModel);
        setUpActionBar();
    }
    public void setUpActionBar(){
        setSupportActionBar( informationBinding.toolbar.toolbar);
        informationBinding.toolbar.toolbar.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        informationBinding.toolbar.setViewmodel(new ToolbarViewModel(CompanyInformationActivity.this, ConfigurationFile.Constants.BACK_IMAGE_VISIBILITY_CODE));
    }
}
