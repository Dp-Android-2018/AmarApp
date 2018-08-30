package dp.com.amarapp.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.view.View;

import java.util.Observable;

import dp.com.amarapp.model.pojo.City;
import dp.com.amarapp.model.pojo.LoginResponseContent;
import dp.com.amarapp.model.response.Country;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.utils.CustomUtils;
import dp.com.amarapp.view.activity.ChangePasswordActivity;
import dp.com.amarapp.view.callback.BaseInterface;

public class ClientSettingsViewModel extends Observable {

    public ObservableField<String> name;
    public ObservableField<String> mail;
    public ObservableField<String> phone;
    public ObservableField<String> countryName;
    public ObservableField<String> cityName;
    public ObservableField<String> password;
    private LoginResponseContent responseContent;
    private Context context;
    private BaseInterface callback;
    private boolean cityFlag;
    Country country;
    City city;

    public ClientSettingsViewModel(Context context, BaseInterface callback) {
        this.context = context;
        this.callback = callback;
        responseContent= CustomUtils.getInstance().getSaveUserObject(context);
        initializeVariables();
        setClientData();
    }

    public void initializeVariables(){
        name=new ObservableField<>();
        mail=new ObservableField<>();
        phone=new ObservableField<>();
        countryName =new ObservableField<>();
        cityName =new ObservableField<>();
        cityFlag=false;
    }
    public void setClientData(){
        name.set(responseContent.getName());
        mail.set(responseContent.getEmail());
        phone.set(responseContent.getPhone());
        countryName.set(responseContent.getCountry().getName());
        cityName.set(responseContent.getCity().getName());
    }
    public void changePassword(View view){
        Intent intent=new Intent(context, ChangePasswordActivity.class);
        context.startActivity(intent);
    }

    public void logOut(View view){

       CustomUtils.getInstance().clearSharedPref(context);
        callback.updateUi(ConfigurationFile.Constants.LOGOUT);
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


}
