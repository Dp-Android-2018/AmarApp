package dp.com.amarapp.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import dp.com.amarapp.Application.MyApplication;
import dp.com.amarapp.R;
import dp.com.amarapp.databinding.ActivitySpecializationBinding;
import dp.com.amarapp.model.pojo.Specialization;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.view.adapter.SpecializationAdapter;
import dp.com.amarapp.view.callback.BaseInterface;
import dp.com.amarapp.view.callback.SpecializationCallback;
import dp.com.amarapp.viewmodel.SpecializationViewModel;
import dp.com.amarapp.viewmodel.ToolbarViewModel;

public class SpecializationActivity extends BaseActivity implements SpecializationCallback {
    private SpecializationViewModel specializationViewModel;
    private ActivitySpecializationBinding specializationBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
        setUpActionBar();
    }

    @Override
    public void getSpecialization(Specialization specialization) {
        Intent i=getIntent();
        i.putExtra(ConfigurationFile.IntentConstants.SPECIALIZATION_DATA,specialization);
        setResult(ConfigurationFile.IntentConstants.REQUEST_CODE_SPECIALIZATION,i);
        finish();
    }

    public void initBinding(){
        specializationViewModel=new SpecializationViewModel();
        specializationBinding= DataBindingUtil.setContentView(SpecializationActivity.this, R.layout.activity_specialization);
        specializationBinding.setSpecializationViewModel(specializationViewModel);
        specializationBinding.rvSpecialization.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        SpecializationAdapter adapter=new SpecializationAdapter(MyApplication.getSpecializations(),this);
        specializationBinding.rvSpecialization.setAdapter(adapter);
    }

    public void setUpActionBar(){
        setSupportActionBar( specializationBinding.toolbar.toolbar);
        specializationBinding.toolbar.setViewmodel(new ToolbarViewModel(SpecializationActivity.this, ConfigurationFile.Constants.BACK_IMAGE_VISIBILITY_CODE));}
}
