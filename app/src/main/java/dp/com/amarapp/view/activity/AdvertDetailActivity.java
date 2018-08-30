package dp.com.amarapp.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;

import dp.com.amarapp.R;
import dp.com.amarapp.databinding.ActivityDetailedAdvertBinding;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.viewmodel.DetailedAdvertViewModel;
import dp.com.amarapp.viewmodel.ToolbarViewModel;

public class AdvertDetailActivity extends BaseActivity {

    private DetailedAdvertViewModel advertViewModel;
    private ActivityDetailedAdvertBinding advertDetailBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
        setUpActionBar();
    }
    public void initBinding(){
        advertViewModel=new DetailedAdvertViewModel(AdvertDetailActivity.this);
        advertDetailBinding= DataBindingUtil.setContentView(AdvertDetailActivity.this, R.layout.activity_detailed_advert);
        advertDetailBinding.tvAdvDesc.setMovementMethod(new ScrollingMovementMethod());
        advertDetailBinding.setDetailedAdvert(advertViewModel);
    }

    public void setUpActionBar(){
        setSupportActionBar( advertDetailBinding.toolbar.toolbar);
        advertDetailBinding.toolbar.setViewmodel(new ToolbarViewModel(AdvertDetailActivity.this, ConfigurationFile.Constants.BACK_IMAGE_VISIBILITY_CODE));}
}
