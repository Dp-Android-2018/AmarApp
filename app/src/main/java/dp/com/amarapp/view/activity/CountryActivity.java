package dp.com.amarapp.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import dp.com.amarapp.R;
import dp.com.amarapp.databinding.ActivityCountryBinding;
import dp.com.amarapp.model.response.Country;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.view.adapter.CountriesAdapter;
import dp.com.amarapp.view.callback.BaseInterface;
import dp.com.amarapp.view.callback.CountryCallback;
import dp.com.amarapp.viewmodel.CountryViewModel;
import dp.com.amarapp.viewmodel.ToolbarViewModel;

public class CountryActivity extends BaseActivity implements CountryCallback,BaseInterface{
    private CountryViewModel countryViewModel;
    private ActivityCountryBinding countryBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
        setUpActionBar();
    }

    public void initBinding(){
        countryViewModel=new CountryViewModel(CountryActivity.this,this);
        countryBinding= DataBindingUtil.setContentView(CountryActivity.this, R.layout.activity_country);
        countryBinding.setCountryViewModel(countryViewModel);
        countryBinding.rvCountries.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

    }

    public void setUpActionBar(){
        setSupportActionBar( countryBinding.toolbar.toolbar);
        countryBinding.toolbar.setViewmodel(new ToolbarViewModel(CountryActivity.this, ConfigurationFile.Constants.BACK_IMAGE_VISIBILITY_CODE));}

    @Override
    public void getCountries(Country country) {
        System.out.println("Country Code :"+country.getId());
        Intent i=getIntent();
        i.putExtra(ConfigurationFile.IntentConstants.COUNTRY_DATA,country);
        setResult(ConfigurationFile.IntentConstants.REQUEST_CODE_COUNTRY,i);
        finish();
    }

    @Override
    public void updateUi(int code) {
        if(code==ConfigurationFile.Constants.COUNTRY_ADAPTER){
            setAdapter();
        }

    }

    public void setAdapter(){
        System.out.println("Size of list is Adapter Setter :"+countryViewModel.responses.size());
        CountriesAdapter adapter=new CountriesAdapter(countryViewModel.responses,this);
        countryBinding.rvCountries.setAdapter(adapter);
    }
}
