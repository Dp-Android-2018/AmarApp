package dp.com.amarapp.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import dp.com.amarapp.R;
import dp.com.amarapp.databinding.ActivityDetailedProjectBinding;
import dp.com.amarapp.viewmodel.DetailedProjectViewModel;

public class DetailedProjectActivity extends AppCompatActivity {
    private DetailedProjectViewModel projectViewModel;
    private ActivityDetailedProjectBinding projectBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
    }

    public void initBinding(){
        projectViewModel=new DetailedProjectViewModel(DetailedProjectActivity.this);
        projectBinding= DataBindingUtil.setContentView(DetailedProjectActivity.this, R.layout.activity_detailed_project);
        projectBinding.setDetailedProjectViewModel(projectViewModel);
    }
}
