package dp.com.amarapp.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import dp.com.amarapp.Application.MyApplication;
import dp.com.amarapp.R;
import dp.com.amarapp.databinding.ActivityCitiesBinding;
import dp.com.amarapp.model.pojo.City;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.view.adapter.CityAdapter;
import dp.com.amarapp.view.callback.BaseInterface;
import dp.com.amarapp.view.callback.CityCallback;
import dp.com.amarapp.viewmodel.CityViewModel;
import dp.com.amarapp.viewmodel.ToolbarViewModel;

public class CityActivity extends BaseActivity implements BaseInterface,CityCallback {
    private CityViewModel cityViewModel;
    private ActivityCitiesBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVariables();
        setUpActionBar();
    }

    public void initVariables(){
        cityViewModel=new CityViewModel(CityActivity.this,this);
        binding= DataBindingUtil.setContentView(CityActivity.this, R.layout.activity_cities);
        binding.setCitiesViewModel(cityViewModel);
        binding.rvCities.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        CityAdapter adapter=new CityAdapter(MyApplication.getCities(),this);
        binding.rvCities.setAdapter(adapter);
    }
    public void setUpActionBar(){
        setSupportActionBar( binding.toolbar.toolbar);
        binding.toolbar.toolbar.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        binding.toolbar.setViewmodel(new ToolbarViewModel(CityActivity.this, ConfigurationFile.Constants.BACK_IMAGE_VISIBILITY_CODE));}
    @Override
    public void updateUi(int code) {

    }

    @Override
    public void getCities(City city) {
        System.out.println("City Pojo :"+city.getName());
        Intent i=getIntent();
        i.putExtra(ConfigurationFile.IntentConstants.CITY_DATA,city);
        setResult(ConfigurationFile.IntentConstants.REQUEST_CODE_CITY,i);
        finish();
    }
}
