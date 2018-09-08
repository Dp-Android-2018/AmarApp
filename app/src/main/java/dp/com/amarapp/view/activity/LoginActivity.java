package dp.com.amarapp.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.WindowManager;

import dp.com.amarapp.R;
import dp.com.amarapp.databinding.ActivityLoginBinding;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.utils.CustomUtils;
import dp.com.amarapp.view.callback.BaseInterface;
import dp.com.amarapp.viewmodel.LoginViewModel;

/**
 * Created by DELL on 22/07/2018.
 */

public class LoginActivity extends BaseActivity implements BaseInterface {

    private LoginViewModel loginViewModel;
    private ActivityLoginBinding loginBinding;
    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initBinding();
    }

    public void initBinding(){
        loginViewModel=new LoginViewModel(LoginActivity.this,this);
        loginBinding= DataBindingUtil.setContentView(LoginActivity.this, R.layout.activity_login);
        loginBinding.setLoginViewModel(loginViewModel);
    }

    @Override
    public void updateUi(int code) {
        switch (code){
            case (ConfigurationFile.Constants.SUCCESS_CODE_second):
            {
               // if(CustomUtils.getInstance().getSaveUserObject())
                finish();
                break;
            }
            case (ConfigurationFile.Constants.INVALED_EMAIL_PASSWORD):
            {
                Snackbar.make(loginBinding.rlParent, R.string.incorrect_mail_or_password,Snackbar.LENGTH_LONG).show();
                break;
            }
            case (ConfigurationFile.Constants.NO_INTERNET_CONNECTION_CODE):
            {
                Snackbar.make(loginBinding.rlParent,R.string.no_internet_connection,Snackbar.LENGTH_LONG).show();
                break;
            }
            case (ConfigurationFile.Constants.FILL_ALL_DATA_ERROR):
            {
                Snackbar.make(loginBinding.rlParent,R.string.msg_fill_data,Snackbar.LENGTH_LONG).show();
                break;
            }
            case (ConfigurationFile.Constants.INVALED_DATA_CODE):
            {
                Snackbar.make(loginBinding.rlParent,"بيانات غير صحيحة",Snackbar.LENGTH_LONG).show();
                break;
            }
            case (ConfigurationFile.Constants.SUSBENDED):
            {
                Snackbar.make(loginBinding.rlParent,"حاول مره اخرى بعد قليل",Snackbar.LENGTH_LONG).show();
                break;
            }
            case (ConfigurationFile.Constants.INVALED_EMAIL):
            {
                Snackbar.make(loginBinding.rlParent,R.string.msg_invaled_mail,Snackbar.LENGTH_LONG).show();
                break;
            }
            case (ConfigurationFile.Constants.MOVE_TO_HOME_ACTIVITY):
            {
                CustomUtils.getInstance().moveTOHome(this);
                break;
            }
            case (ConfigurationFile.Constants.MOVE_TO_MEMBERSHIP_ACTIVITY):
            {
                intent=new Intent(this,MembershipActivity.class);
                startActivity(intent);
                break;
            }
            case (ConfigurationFile.Constants.MOVE_TO_FORGET_PASSWORD_ACTIVITY):
            {
                intent=new Intent(this,ForgetPasswordActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}
