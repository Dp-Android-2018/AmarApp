package dp.com.amarapp.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;

import dp.com.amarapp.R;
import dp.com.amarapp.databinding.ActivityActivationBinding;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.view.callback.BaseInterface;
import dp.com.amarapp.viewmodel.ActivationViewModel;
import dp.com.amarapp.viewmodel.ToolbarViewModel;

public class ActivationActivity extends BaseActivity implements BaseInterface {
    private ActivationViewModel activationViewModel;
    private ActivityActivationBinding activationBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("Hello Amar");
        initBinding();
    }

    public void initBinding(){
        activationViewModel=new ActivationViewModel(ActivationActivity.this,this);
        activationBinding= DataBindingUtil.setContentView(ActivationActivity.this, R.layout.activity_activation);
        activationBinding.setActivationViewModel(activationViewModel);
        setUpActionBar();
    }

    public void setUpActionBar(){
        setSupportActionBar( activationBinding.toolbar.toolbar);
        activationBinding.toolbar.setViewmodel(new ToolbarViewModel(ActivationActivity.this, ConfigurationFile.Constants.BACK_IMAGE_VISIBILITY_CODE));
    }


    @Override
    public void updateUi(int code) {

        switch (code){
            case ConfigurationFile.Constants.SUCCESS_CODE_second:
            {
                Snackbar.make(activationBinding.rlParent,getString(R.string.send_activation_mail), Snackbar.LENGTH_LONG).show();
                break;
            }

            case ConfigurationFile.Constants.NO_INTERNET_CONNECTION_CODE:
            {
                Snackbar.make(activationBinding.rlParent, R.string.no_internet_connection, Snackbar.LENGTH_LONG).show();
                break;
            }
        }
    }
}
