package dp.com.amarapp.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import dp.com.amarapp.R;
import dp.com.amarapp.databinding.ActivityMembershipBinding;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.view.callback.BaseInterface;
import dp.com.amarapp.viewmodel.MemberShipViewModel;
import dp.com.amarapp.viewmodel.ToolbarViewModel;

/**
 * Created by DELL on 24/07/2018.
 */

public class MembershipActivity extends BaseActivity implements BaseInterface {

    private MemberShipViewModel memberShipViewModel;
    private ActivityMembershipBinding membershipBinding;
    private Intent intent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
        setUpActionBar();
    }

    public void initBinding(){
        memberShipViewModel=new MemberShipViewModel(MembershipActivity.this,this);
        membershipBinding= DataBindingUtil.setContentView(MembershipActivity.this,R.layout.activity_membership);
        membershipBinding.setMemberShipViewModel(memberShipViewModel);
    }

    public void setUpActionBar(){
        setSupportActionBar( membershipBinding.toolbar.toolbar);
        membershipBinding.toolbar.toolbar.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        membershipBinding.toolbar.setViewmodel(new ToolbarViewModel(MembershipActivity.this, ConfigurationFile.Constants.BACK_IMAGE_VISIBILITY_CODE));}
    @Override
    public void updateUi(int code) {

        switch (code){
            case (ConfigurationFile.Constants.CLIENT_REGISTRATION):
            {
                intent=new Intent(this,ClientRegisterActivity.class);
                startActivity(intent);
                break;
            }
            case (ConfigurationFile.Constants.COMPANY_REGISTRATION):
            {
                intent=new Intent(this,CompanyRegisterActivityStep1.class);
                startActivity(intent);
                break;
            }
            case (ConfigurationFile.Constants.MOVE_TO_HOME_ACTIVITY):
            {
                intent=new Intent(this,ContainerActivity.class);
                startActivity(intent);
                break;
            }
        }

    }
}
