package dp.com.amarapp.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.View;

import dp.com.amarapp.R;
import dp.com.amarapp.databinding.ActivityCompanyRegisterStep2Binding;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.view.callback.BaseInterface;
import dp.com.amarapp.viewmodel.CompanyRegisterViewModelStep2;
import dp.com.amarapp.viewmodel.ToolbarViewModel;

/**
 * Created by DELL on 24/07/2018.
 */

public class CompanyRegisterActivityStep2 extends BaseActivity implements BaseInterface{

    private CompanyRegisterViewModelStep2 registerViewModelStep2;
    private ActivityCompanyRegisterStep2Binding registerStep2Binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
        setUpActionBar();
    }

    public void setUpActionBar(){
        setSupportActionBar( registerStep2Binding.toolbar.toolbar);
        registerStep2Binding.toolbar.toolbar.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        registerStep2Binding.toolbar.setViewmodel(new ToolbarViewModel(CompanyRegisterActivityStep2.this, ConfigurationFile.Constants.BACK_IMAGE_VISIBILITY_CODE));}
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        registerViewModelStep2.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    public void updateUi(int code) {
        switch (code){
            case (ConfigurationFile.Constants.FILL_ALL_DATA_ERROR):
            {
                Snackbar.make(registerStep2Binding.llParent, R.string.msg_fill_data,Snackbar.LENGTH_LONG).show();
                break;
            }
            case (ConfigurationFile.Constants.INVALED_PHONE):
            {
                Snackbar.make(registerStep2Binding.llParent, R.string.msg_invaled_phone,Snackbar.LENGTH_LONG).show();
                break;
            }
            case (ConfigurationFile.Constants.SUCCESS_CODE):
            {
                Snackbar.make(registerStep2Binding.llParent,"success",Snackbar.LENGTH_LONG).show();
                break;
            }
            case (ConfigurationFile.Constants.INVALED_DATA_CODE):
            {
                Snackbar.make(registerStep2Binding.llParent, R.string.invaled_data,Snackbar.LENGTH_LONG).show();
                break;
            }
            case (ConfigurationFile.Constants.SERVER_ERROR):
            {
                Snackbar.make(registerStep2Binding.llParent, R.string.server_error, Snackbar.LENGTH_LONG).show();
                break;
            }
            case (ConfigurationFile.Constants.EXISET_PHONE_CODE):
            {
                Snackbar.make(registerStep2Binding.llParent, R.string.exist_phone, Snackbar.LENGTH_LONG).show();
                break;
            } case ConfigurationFile.Constants.MOVE_TO_CATEGORY_ACT:
            {
                Intent i = new Intent(this,CategoriesActivity.class);
                startActivityForResult(i, ConfigurationFile.IntentConstants.REQUEST_CODE_CATEGORY);
                break;
            }
            case (ConfigurationFile.Constants.SELECT_GATEGORY):
            {
                Snackbar.make(registerStep2Binding.llParent, R.string.select_category,Snackbar.LENGTH_LONG).show();
                break;
            }
            case ConfigurationFile.Constants.MOVE_TO_SPECIALIZATION_ACT:
            {
                Intent i = new Intent(this,SpecializationActivity.class);
                startActivityForResult(i, ConfigurationFile.IntentConstants.REQUEST_CODE_SPECIALIZATION);
                break;
            }
            case ConfigurationFile.Constants.GETIMAGE:
            {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickPhoto.setType("image/*"); //allows any image file type. Change * to specific extension to limit it

                startActivityForResult(pickPhoto , ConfigurationFile.Constants.PICK_IMAGE_REQUEST);
                break;
            }
        }

    }

    public void initBinding(){
        registerViewModelStep2=new CompanyRegisterViewModelStep2(CompanyRegisterActivityStep2.this,this);
        registerStep2Binding= DataBindingUtil.setContentView(CompanyRegisterActivityStep2.this, R.layout.activity_company_register_step2);
        registerStep2Binding.setCompanyRegisterViewModelStep2(registerViewModelStep2);
    }
}
