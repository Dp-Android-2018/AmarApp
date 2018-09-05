package dp.com.amarapp.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.WindowManager;

import dp.com.amarapp.R;
import dp.com.amarapp.databinding.ActivityChangePasswordBinding;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.view.callback.BaseInterface;
import dp.com.amarapp.viewmodel.ChangePasswordViewModel;

public class ChangePasswordActivity extends AppCompatActivity implements BaseInterface {
    private ChangePasswordViewModel changePasswordViewModel;
    private ActivityChangePasswordBinding changePasswordBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initBinding();
    }

    @Override
    public void updateUi(int code) {
        switch (code){
            case (ConfigurationFile.Constants.NO_INTERNET_CONNECTION_CODE):
            {
                Snackbar.make(changePasswordBinding.rlParent, R.string.no_internet_connection,Snackbar.LENGTH_LONG).show();
                break;
            }
            case (ConfigurationFile.Constants.FILL_ALL_DATA_ERROR):
            {
                Snackbar.make(changePasswordBinding.rlParent, R.string.msg_fill_data,Snackbar.LENGTH_LONG).show();
                break;
            }
            case (ConfigurationFile.Constants.INVALED_PASSWORD):
            {
                Snackbar.make(changePasswordBinding.rlParent, R.string.msg_invaled_password,Snackbar.LENGTH_LONG).show();
                break;
            }
            case (ConfigurationFile.Constants.PASSWORD_MATCHES_CODE):
            {
                Snackbar.make(changePasswordBinding.rlParent, R.string.confirm_password,Snackbar.LENGTH_LONG).show();
                break;
            }
            case (ConfigurationFile.Constants.SUSBENDED):
            {
                Snackbar.make(changePasswordBinding.rlParent, R.string.server_error,Snackbar.LENGTH_LONG).show();
                break;
            }
            case (ConfigurationFile.Constants.SUCCESS_CODE_second):
            {
                Snackbar.make(changePasswordBinding.rlParent, R.string.change_password_successed,Snackbar.LENGTH_LONG).show();
                break;
            }
            case (ConfigurationFile.Constants.INVALED_EMAIL_PASSWORD):
            {
                Snackbar.make(changePasswordBinding.rlParent, R.string.invaled_password,Snackbar.LENGTH_LONG).show();
                break;
            }
            case (ConfigurationFile.Constants.NEW_PASS_EQUAL_OLD_PASS):
            {
                Snackbar.make(changePasswordBinding.rlParent, R.string.write_new_pass,Snackbar.LENGTH_LONG).show();
                break;
            }


        }


    }
    public void initBinding(){
        changePasswordViewModel=new ChangePasswordViewModel(this,ChangePasswordActivity.this);
        changePasswordBinding= DataBindingUtil.setContentView(ChangePasswordActivity.this, R.layout.activity_change_password);
        changePasswordBinding.setChangePasswordViewModel(changePasswordViewModel);

    }
}
