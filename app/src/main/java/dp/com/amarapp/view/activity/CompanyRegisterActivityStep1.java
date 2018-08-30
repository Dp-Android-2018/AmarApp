package dp.com.amarapp.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import dp.com.amarapp.R;
import dp.com.amarapp.databinding.ActivityCompanyRegisterStep1Binding;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.view.callback.BaseInterface;
import dp.com.amarapp.viewmodel.CompanyRegisterViewModelStep1;
import dp.com.amarapp.viewmodel.ToolbarViewModel;

/**
 * Created by DELL on 24/07/2018.
 */

public class CompanyRegisterActivityStep1 extends BaseActivity implements BaseInterface {

    private CompanyRegisterViewModelStep1 registerViewModelStep1;
    private ActivityCompanyRegisterStep1Binding registerStep1Binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
        setUpActionBar();
    }

    public void setUpActionBar(){
        setSupportActionBar( registerStep1Binding.toolbar.toolbar);
        registerStep1Binding.toolbar.setViewmodel(new ToolbarViewModel(CompanyRegisterActivityStep1.this, ConfigurationFile.Constants.BACK_IMAGE_VISIBILITY_CODE));}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        registerViewModelStep1.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    public void updateUi(int code) {
        switch (code){
            case (ConfigurationFile.Constants.FILL_ALL_DATA_ERROR):
            {
                Snackbar.make(registerStep1Binding.llParent, R.string.msg_fill_data,Snackbar.LENGTH_LONG).show();
                break;
            }
            case (ConfigurationFile.Constants.INVALED_EMAIL):
            {
                Snackbar.make(registerStep1Binding.llParent, R.string.msg_invaled_mail,Snackbar.LENGTH_LONG).show();
                break;
            }
            case (ConfigurationFile.Constants.INVALED_PASSWORD):
            {
                Snackbar.make(registerStep1Binding.llParent, R.string.msg_invaled_password,Snackbar.LENGTH_LONG).show();
                break;
            }
            case (ConfigurationFile.Constants.PASSWORD_MATCHES_CODE):
            {
                Snackbar.make(registerStep1Binding.llParent,R.string.confirm_password,Snackbar.LENGTH_LONG).show();
                break;
            }
            case (ConfigurationFile.Constants.EXISET_MAIL_CODE):
            {
                Snackbar.make(registerStep1Binding.llParent,R.string.exist_mail,Snackbar.LENGTH_LONG).show();
                break;
            }
            case (ConfigurationFile.Constants.NO_INTERNET_CONNECTION_CODE):
            {
                Snackbar.make(registerStep1Binding.llParent,R.string.no_internet_connection,Snackbar.LENGTH_LONG).show();
                break;
            }
            case ConfigurationFile.Constants.MOVE_TO_COUNTRY_ACT:
            {
                Intent i=new Intent(this, CountryActivity.class);
                startActivityForResult(i,ConfigurationFile.IntentConstants.REQUEST_CODE_COUNTRY);
                break;
            }
            case ConfigurationFile.Constants.MOVE_TO_CITY_ACT:
            {
                Intent i = new Intent(this,CityActivity.class);
                startActivityForResult(i, ConfigurationFile.IntentConstants.REQUEST_CODE_CITY);
                break;
            }
            case (ConfigurationFile.Constants.SELECT_COUNTRY):
            {
                Snackbar.make(registerStep1Binding.llParent, R.string.select_country,Snackbar.LENGTH_LONG).show();
                break;
            }

        }

    }

    public void initBinding(){
        registerViewModelStep1=new CompanyRegisterViewModelStep1(CompanyRegisterActivityStep1.this,this);
        registerStep1Binding= DataBindingUtil.setContentView(CompanyRegisterActivityStep1.this, R.layout.activity_company_register_step1);
        registerStep1Binding.setCompanyRegisterViewModelStep1(registerViewModelStep1);
    }
}
