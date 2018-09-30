package dp.com.amarapp.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.Window;
import android.view.WindowManager;

import dp.com.amarapp.R;
import dp.com.amarapp.databinding.ActivityActivationCodeBinding;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.view.callback.BaseInterface;
import dp.com.amarapp.viewmodel.ActivationCodeViewModel;

public class ActivationCodeActivity extends BaseActivity implements BaseInterface {
    private ActivationCodeViewModel codeViewModel;
    private ActivityActivationCodeBinding codeBinding;

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
        codeViewModel=new ActivationCodeViewModel(ActivationCodeActivity.this,this);
        codeBinding= DataBindingUtil.setContentView(ActivationCodeActivity.this, R.layout.activity_activation_code);
        codeBinding.setCodeViewModel(codeViewModel);
    }
    @Override
    public void updateUi(int code) {
        switch (code){
            case ConfigurationFile.Constants.NO_INTERNET_CONNECTION_CODE:
            {
                Snackbar.make(codeBinding.llParent, R.string.no_internet_connection, Snackbar.LENGTH_LONG).show();
                break;
            }
            case ConfigurationFile.Constants.FILL_ALL_DATA_ERROR:
            {
                Snackbar.make(codeBinding.llParent, R.string.enter_activation_code, Snackbar.LENGTH_LONG).show();
                break;
            }
            case ConfigurationFile.Constants.SUCCESS_CODE_second:
            {
                Snackbar.make(codeBinding.llParent, R.string.activate_successfully, Snackbar.LENGTH_LONG).show();
                Intent i=new Intent(this,ContainerActivity.class);
                startActivity(i);
                finishAffinity();
                break;
            }
            case ConfigurationFile.Constants.ALREADY_ACTIVATED:
            {
                Snackbar.make(codeBinding.llParent,getString(R.string.invaled_activation_code), Snackbar.LENGTH_LONG).show();
                break;
            }
            case ConfigurationFile.Constants.INVALED_DATA_CODE:
            {
                Snackbar.make(codeBinding.llParent, R.string.activation_code_digits, Snackbar.LENGTH_LONG).show();
                break;
            }
        }
    }
}
