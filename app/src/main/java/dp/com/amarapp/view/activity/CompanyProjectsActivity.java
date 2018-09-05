package dp.com.amarapp.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.View;

import dp.com.amarapp.R;
import dp.com.amarapp.databinding.ActivityCompanyProjectsBinding;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.view.callback.BaseInterface;
import dp.com.amarapp.viewmodel.CompanyProjectsViewModel;
import dp.com.amarapp.viewmodel.ToolbarViewModel;

public class CompanyProjectsActivity extends BaseActivity implements BaseInterface {
    private CompanyProjectsViewModel projectsViewModel;
    private ActivityCompanyProjectsBinding projectsBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
    }

    @Override
    public void updateUi(int code) {
        switch (code){
            case (ConfigurationFile.Constants.NO_INTERNET_CONNECTION_CODE):
            {
                Snackbar.make(projectsBinding.rlParent, R.string.no_internet_connection,Snackbar.LENGTH_LONG).show();
                break;
            }
            case (777):
            {
                Snackbar.make(projectsBinding.rlParent,"no data here",Snackbar.LENGTH_LONG).show();
                break;
            }
        }
    }

    public void initBinding(){
        projectsViewModel=new CompanyProjectsViewModel(CompanyProjectsActivity.this,this);
        projectsBinding= DataBindingUtil.setContentView(CompanyProjectsActivity.this,R.layout.activity_company_projects);
        projectsBinding.setProjectsViewModel(projectsViewModel);
        setUpActionBar();
    }

    public void setUpActionBar(){
        setSupportActionBar( projectsBinding.toolbar.toolbar);
        projectsBinding.toolbar.toolbar.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        projectsBinding.toolbar.setViewmodel(new ToolbarViewModel(CompanyProjectsActivity.this, ConfigurationFile.Constants.BACK_IMAGE_VISIBILITY_CODE));
    }
}
