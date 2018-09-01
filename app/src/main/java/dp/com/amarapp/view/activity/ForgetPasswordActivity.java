package dp.com.amarapp.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import dp.com.amarapp.R;
import dp.com.amarapp.databinding.ActivityForgetPasswordBinding;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.view.callback.BaseInterface;
import dp.com.amarapp.viewmodel.ForgetPasswordViewModel;

/**
 * Created by DELL on 24/07/2018.
 */

public class ForgetPasswordActivity extends AppCompatActivity implements BaseInterface {

    private ForgetPasswordViewModel passwordViewModel;
    private ActivityForgetPasswordBinding forgetPasswordBinding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
    }

    public void initBinding(){
        passwordViewModel=new ForgetPasswordViewModel(ForgetPasswordActivity.this,this);
        forgetPasswordBinding= DataBindingUtil.setContentView(ForgetPasswordActivity.this,R.layout.activity_forget_password);
        forgetPasswordBinding.setForgetPasswordViewModel(passwordViewModel);
    }

    @Override
    public void updateUi(int code) {
        switch (code){
            case ConfigurationFile.Constants.FILL_ALL_DATA_ERROR:{
                Snackbar.make(forgetPasswordBinding.llParent,R.string.please_enter_your_mail,Snackbar.LENGTH_LONG).show();
                break;
            }
            case ConfigurationFile.Constants.INVALED_EMAIL:{
                Snackbar.make(forgetPasswordBinding.llParent,R.string.msg_invaled_mail,Snackbar.LENGTH_LONG).show();
                break;
            }
            case ConfigurationFile.Constants.NO_INTERNET_CONNECTION_CODE:{
                Snackbar.make(forgetPasswordBinding.llParent,R.string.no_internet_connection,Snackbar.LENGTH_LONG).show();
                break;
            }
            case ConfigurationFile.Constants.INVALED_DATA_CODE:{
                Snackbar.make(forgetPasswordBinding.llParent,getString(R.string.this_mail_not_exist),Snackbar.LENGTH_LONG).show();
                break;
            }
            case ConfigurationFile.Constants.SUCCESS_CODE_second:{
                Snackbar.make(forgetPasswordBinding.llParent,R.string.send_activation_mail,Snackbar.LENGTH_LONG).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i=new Intent(getApplicationContext(),LoginActivity.class);
                        startActivity(i);
                        finishAffinity();
                    }
                },2000);
                break;
            }
        }
    }
}
