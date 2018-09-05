package dp.com.amarapp.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import dp.com.amarapp.R;
import dp.com.amarapp.databinding.ActivityCompanyDetailedBinding;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.view.callback.BaseInterface;
import dp.com.amarapp.viewmodel.CompanyDetailedViewModel;
import dp.com.amarapp.viewmodel.ToolbarViewModel;

public class CompanyDetailedActivity extends BaseActivity implements BaseInterface {
    private CompanyDetailedViewModel detailedViewModel;
    private ActivityCompanyDetailedBinding detailedBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
        setUpActionBar();
    }

    public void initBinding(){
        detailedViewModel=new CompanyDetailedViewModel(CompanyDetailedActivity.this,this);
        detailedBinding= DataBindingUtil.setContentView(CompanyDetailedActivity.this, R.layout.activity_company_detailed);
        detailedBinding.setCompanyDetailedViewModel(detailedViewModel);
        detailedBinding.rvComments.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        detailedBinding.rvImages.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
    }
    public void setUpActionBar(){
        setSupportActionBar( detailedBinding.toolbar.toolbar);
        detailedBinding.toolbar.toolbar.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        detailedBinding.toolbar.setViewmodel(new ToolbarViewModel(CompanyDetailedActivity.this, ConfigurationFile.Constants.BACK_IMAGE_VISIBILITY_CODE));
    }


    @Override
    public void updateUi(int code) {
        switch (code){

            case ConfigurationFile.Constants.YOU_ARE_COMPANY: {
                Snackbar.make(detailedBinding.llParent, "كتابة تعليق مُتاح للعملاء فقط", Snackbar.LENGTH_LONG).show();
                break;
            }
            case ConfigurationFile.Constants.SUCCESS_CODE: {
                Snackbar.make(detailedBinding.llParent, "تم إضافه التعليق بنجاح", Snackbar.LENGTH_LONG).show();
                break;
            }
            case ConfigurationFile.Constants.NO_INTERNET_CONNECTION_CODE: {
                Snackbar.make(detailedBinding.llParent,R.string.no_internet_connection, Snackbar.LENGTH_LONG).show();
                break;
            }
            case ConfigurationFile.Constants.ALREADY_ACTIVATED:{
                Snackbar.make(detailedBinding.llParent, R.string.more_comments_illagel, Snackbar.LENGTH_LONG).show();
                break;
            }
            case ConfigurationFile.Constants.FILL_ALL_DATA_ERROR:{
                Snackbar.make(detailedBinding.llParent, R.string.msg_fill_data, Snackbar.LENGTH_LONG).show();
                break;
            }
        }
    }
}
