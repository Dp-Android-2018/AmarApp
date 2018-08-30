package dp.com.amarapp.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.view.View;
import android.widget.Toast;

import dp.com.amarapp.R;
import dp.com.amarapp.model.pojo.City;
import dp.com.amarapp.model.pojo.LoginResponseContent;
import dp.com.amarapp.model.request.UpdateMetaDataRequest;
import dp.com.amarapp.model.response.CompanyLoginResponse;
import dp.com.amarapp.model.response.Country;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.utils.CustomUtils;
import dp.com.amarapp.utils.UpdateMetadata;
import dp.com.amarapp.utils.ValidationUtils;
import dp.com.amarapp.view.callback.BaseInterface;

public class CompanyProfileViewModel_1 {
    Activity activity;
    private boolean cityFlag;
    private CompanyLoginResponse company;
    private UpdateMetaDataRequest request;
    private BaseInterface callback;
    public ObservableField<String> name;
    public ObservableField<String> countryName;
    public ObservableField<String> cityName;
    Country country;
    City city;

    public CompanyProfileViewModel_1(Activity activity,BaseInterface callback) {
        this.activity = activity;
        this.callback=callback;
        request=new UpdateMetaDataRequest();
        company= (CompanyLoginResponse) CustomUtils.getInstance().getSaveUserObject(activity);
        System.out.println("Company Name :"+company.getName());
        initVariables();
    }

    public void initVariables(){
        name=new ObservableField<>();
        name.set(company.getName());
        cityFlag=false;
        countryName=new ObservableField<>();
        countryName.set(company.getCountry()!=null?company.getCountry().getName():"");
        //(CustomUtils.getInstance().getSaveUserObject(activity).getCountry().getName());
        cityName=new ObservableField<>();
        cityName.set(company.getCity()!=null?company.getCity().getName():"");
        //(CustomUtils.getInstance().getSaveUserObject(activity).getCity().getName());
    }

    public String getMail(){
        if (company.getEmail()!=null)
        return company.getEmail();
        else
            return "no Data";
    }

    public void getCountries(View view){
        cityFlag=true;
        callback.updateUi(ConfigurationFile.Constants.MOVE_TO_COUNTRY_ACT);
    }

    public void getCities(View view){
        if(cityFlag) {
            callback.updateUi(ConfigurationFile.Constants.MOVE_TO_CITY_ACT);
        }
        else{
            callback.updateUi(ConfigurationFile.Constants.SELECT_COUNTRY);
        }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ConfigurationFile.IntentConstants.REQUEST_CODE_COUNTRY) {
            country = (Country) data.getSerializableExtra(ConfigurationFile.IntentConstants.COUNTRY_DATA);
            setCountryName();
        } else if (requestCode == ConfigurationFile.IntentConstants.REQUEST_CODE_CITY) {
            city = (City) data.getSerializableExtra(ConfigurationFile.IntentConstants.CITY_DATA);
            setCityName();
        }
    }

    public void save(View view) {
        if (!ValidationUtils.isEmpty(name.get())) {
            request.setName(name.get());
        }
        if (city.getId() >= 0) {
            request.setCityId(city.getId());
        }
        if (country.getId()> 0) {
            request.setCountryId(country.getId());
        }
        request.setSpecializationId(CustomUtils.getInstance().getSaveUserObject(activity).getSpecialization().getId());

        UpdateMetadata updat = new UpdateMetadata(request, activity, "Bearer " + company.getToken(),
                ConfigurationFile.FragmentID.FRAGMENT1);
        System.out.println("countryId : "+country.getId()+" "+country.getName());
        System.out.println("cityId : "+city.getId()+" "+city.getName());
        updat.setCountyCity(country,city);
        updat.call();
    }

        public void setCountryName(){
            countryName.set(country.getName());
        }
        public void setCityName(){
            cityName.set(city.getName());
        }
}
