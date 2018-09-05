package dp.com.amarapp.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import dp.com.amarapp.R;
import dp.com.amarapp.databinding.ActivityPhoneBinding;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.view.callback.BaseInterface;
import dp.com.amarapp.viewmodel.PhoneViewModel;

public class PhoneActivity extends AppCompatActivity implements BaseInterface {

    private PhoneViewModel phoneViewModel;
    private ActivityPhoneBinding phoneBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initBinding();
    }

    public void initBinding(){
        phoneViewModel=new PhoneViewModel(PhoneActivity.this,this);
        phoneBinding= DataBindingUtil.setContentView(PhoneActivity.this, R.layout.activity_phone);
        phoneBinding.setPhoneViewModel(phoneViewModel);
    }

    @Override
    public void updateUi(int code) {
        System.out.println("code before switch :"+code);
        switch (code){
            case ConfigurationFile.Constants.NO_INTERNET_CONNECTION_CODE:
            {
                Snackbar.make(phoneBinding.llParent, R.string.no_internet_connection, Snackbar.LENGTH_LONG).show();
                break;
            }
            case ConfigurationFile.Constants.FILL_ALL_DATA_ERROR:
            {
                Snackbar.make(phoneBinding.llParent, R.string.enter_phone_number, Snackbar.LENGTH_LONG).show();
                break;
            }
            case ConfigurationFile.Constants.INVALED_PHONE:
            {
                Snackbar.make(phoneBinding.llParent, R.string.msg_invaled_phone, Snackbar.LENGTH_LONG).show();
                break;
            }
            case ConfigurationFile.Constants.SUCCESS_CODE_second:
            {
                Snackbar.make(phoneBinding.llParent,getString(R.string.activation_code_send), Snackbar.LENGTH_LONG).show();
                Intent i=new Intent(this,ActivationCodeActivity.class);
                startActivity(i);
                finishAffinity();
                break;
            }
            case ConfigurationFile.Constants.ALREADY_ACTIVATED:
            {
                Snackbar.make(phoneBinding.llParent, R.string.this_phone_is_activated, Snackbar.LENGTH_LONG).show();
                System.out.println("Already activated on activity phone "+code);
                break;
            }

        }
    }
}
