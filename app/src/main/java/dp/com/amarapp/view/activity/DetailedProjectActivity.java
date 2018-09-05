package dp.com.amarapp.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import dp.com.amarapp.R;
import dp.com.amarapp.databinding.ActivityDetailedProjectBinding;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.viewmodel.DetailedProjectViewModel;
import dp.com.amarapp.viewmodel.ToolbarViewModel;

public class DetailedProjectActivity extends BaseActivity {
    private DetailedProjectViewModel projectViewModel;
    private ActivityDetailedProjectBinding projectBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
        setUpActionBar();
    }

    public void initBinding(){
        projectViewModel=new DetailedProjectViewModel(DetailedProjectActivity.this);
        projectBinding= DataBindingUtil.setContentView(DetailedProjectActivity.this, R.layout.activity_detailed_project);
        projectBinding.setDetailedProjectViewModel(projectViewModel);
        projectBinding.slider.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));

    }

    public void setUpActionBar(){
        setSupportActionBar( projectBinding.toolbar.toolbar);
        projectBinding.toolbar.toolbar.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        projectBinding.toolbar.setViewmodel(new ToolbarViewModel(DetailedProjectActivity.this, ConfigurationFile.Constants.BACK_IMAGE_VISIBILITY_CODE));
    }
}
