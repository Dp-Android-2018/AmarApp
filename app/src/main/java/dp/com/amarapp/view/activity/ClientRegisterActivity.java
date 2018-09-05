package dp.com.amarapp.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.View;

import dp.com.amarapp.R;
import dp.com.amarapp.databinding.ActivityClientRegisterBinding;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.utils.CustomUtils;
import dp.com.amarapp.view.callback.BaseInterface;
import dp.com.amarapp.viewmodel.ClientRegisterViewModel;
import dp.com.amarapp.viewmodel.ToolbarViewModel;

/**
 * Created by DELL on 21/07/2018.
 */

public class ClientRegisterActivity extends BaseActivity implements BaseInterface{

    private ClientRegisterViewModel clientRegisterViewModel;
    private ActivityClientRegisterBinding clientRegisterBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
        setUpActionBar();
    }

    public void setUpActionBar(){
        setSupportActionBar( clientRegisterBinding.toolbar.toolbar);
        clientRegisterBinding.toolbar.toolbar.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        clientRegisterBinding.toolbar.setViewmodel(new ToolbarViewModel(ClientRegisterActivity.this, ConfigurationFile.Constants.BACK_IMAGE_VISIBILITY_CODE));}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        clientRegisterViewModel.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    public void updateUi(int code) {
        switch (code){
            case (ConfigurationFile.Constants.SUCCESS_CODE):
            {
                CustomUtils.getInstance().moveTOHome(this);
                break;
            }
            case (ConfigurationFile.Constants.FILL_ALL_DATA_ERROR):
            {
                Snackbar.make(clientRegisterBinding.llParent,R.string.msg_fill_data,Snackbar.LENGTH_LONG).show();
                break;
            }
            case (ConfigurationFile.Constants.INVALED_EMAIL):
            {
                Snackbar.make(clientRegisterBinding.llParent,R.string.msg_invaled_mail,Snackbar.LENGTH_LONG).show();
                break;
            }
            case (ConfigurationFile.Constants.INVALED_PHONE):
            {
                Snackbar.make(clientRegisterBinding.llParent,R.string.msg_invaled_phone,Snackbar.LENGTH_LONG).show();
                break;
            }
            case (ConfigurationFile.Constants.INVALED_PASSWORD):
            {
                Snackbar.make(clientRegisterBinding.llParent, R.string.msg_invaled_password,Snackbar.LENGTH_LONG).show();
                break;
            }
            case (ConfigurationFile.Constants.PASSWORD_MATCHES_CODE):
            {
                Snackbar.make(clientRegisterBinding.llParent, R.string.confirm_password,Snackbar.LENGTH_LONG).show();
                break;
            }
            case (ConfigurationFile.Constants.EXISET_MAIL_CODE):
            {
                Snackbar.make(clientRegisterBinding.llParent, R.string.exist_mail,Snackbar.LENGTH_LONG).show();
                break;
            }
            case (ConfigurationFile.Constants.EXISET_PHONE_CODE):
            {
                Snackbar.make(clientRegisterBinding.llParent,R.string.exist_phone,Snackbar.LENGTH_LONG).show();
                break;
            }

            case (ConfigurationFile.Constants.NO_INTERNET_CONNECTION_CODE):
            {
                Snackbar.make(clientRegisterBinding.llParent, R.string.no_internet_connection,Snackbar.LENGTH_LONG).show();
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
                Snackbar.make(clientRegisterBinding.llParent, R.string.select_country,Snackbar.LENGTH_LONG).show();
                break;
            }
        }

    }

    public void initBinding(){
        clientRegisterViewModel=new ClientRegisterViewModel(ClientRegisterActivity.this,this);
        clientRegisterBinding= DataBindingUtil.setContentView(ClientRegisterActivity.this, R.layout.activity_client_register);
        clientRegisterBinding.setClientRegisterViewModel(clientRegisterViewModel);
    }


}
