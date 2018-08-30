package dp.com.amarapp.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import dp.com.amarapp.R;
import dp.com.amarapp.databinding.ActivityCompanyInformationBinding;
import dp.com.amarapp.viewmodel.CompanyInformationViewModel;

public class CompanyInformationActivity extends AppCompatActivity {
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
    }
}
