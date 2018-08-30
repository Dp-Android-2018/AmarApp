package dp.com.amarapp.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.view.View;

import java.util.Observable;

import dp.com.amarapp.model.pojo.City;
import dp.com.amarapp.model.request.CheckMailRequest;
import dp.com.amarapp.model.request.CompanyRegisterRequest;
import dp.com.amarapp.model.response.Country;
import dp.com.amarapp.network.ApiClient;
import dp.com.amarapp.network.EndPoints;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.utils.CustomUtils;
import dp.com.amarapp.utils.NetWorkConnection;
import dp.com.amarapp.utils.ValidationUtils;
import dp.com.amarapp.view.activity.CompanyRegisterActivityStep2;
import dp.com.amarapp.view.callback.BaseInterface;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by DELL on 24/07/2018.
 */

public class CompanyRegisterViewModelStep1 extends Observable {

    public ObservableField<String> name;
    public ObservableField<String> email;
    public ObservableField<String> password;
    public ObservableField<String> confirm_password;
    public ObservableField<String> countryName;
    public ObservableField<String> cityName;
    private boolean cityFlag;
    private Context context;
    Country country;
    City city;
    private BaseInterface callback;
    private CompanyRegisterRequest companyRegisterRequest;
    private CheckMailRequest mailRequest;

    public CompanyRegisterViewModelStep1(Context context, BaseInterface callBack) {
        this.context = context;
        this.callback = callBack;
        initializeVariables();
    }

     public void initializeVariables(){
        name=new ObservableField<>();
        email=new ObservableField<>();
        countryName=new ObservableField<>();
        cityName=new ObservableField<>();
        password=new ObservableField<>();
        confirm_password=new ObservableField<>();
        cityFlag=false;
    }

    public boolean checkEmptyData() {
        if (ValidationUtils.isEmpty(name.get()) ||
                ValidationUtils.isEmpty(email.get()) ||
                ValidationUtils.isEmpty(password.get()) ||
                ValidationUtils.isEmpty(confirm_password.get()) ||
                country==null || city==null) {
            callback.updateUi(ConfigurationFile.Constants.FILL_ALL_DATA_ERROR);
            return false;
        }else{
            if(!ValidationUtils.isMail(email.get())){
                callback.updateUi(ConfigurationFile.Constants.INVALED_EMAIL);
                return false;
            }else{
                if(!(password.get().length()>=8)){
                    callback.updateUi(ConfigurationFile.Constants.INVALED_PASSWORD);
                    return false;
                }else{
                    if(! password.get().equals(confirm_password.get())){
                        callback.updateUi(ConfigurationFile.Constants.PASSWORD_MATCHES_CODE);
                        return false;
                    }
                }

            }
            companyRegisterRequest=new CompanyRegisterRequest();
            mailRequest=new CheckMailRequest(email.get());
            companyRegisterRequest.setName(name.get());
            companyRegisterRequest.setEmail(email.get());
            companyRegisterRequest.setPassword(password.get());
            companyRegisterRequest.setPassConf(confirm_password.get());
            companyRegisterRequest.setCityId(city.getId());
            checkExistMail();
            return true;
        }

    }

    public void checkExistMail(){
        if(NetWorkConnection.isConnectingToInternet(context)){
            CustomUtils.getInstance().showProgressDialog((Activity)context);
            System.out.println("Register Success Mail :"+email.get());
            ApiClient.getClient().create(EndPoints.class).checkMail(ConfigurationFile.Constants.API_KEY,
                    ConfigurationFile.Constants.CONTENT_TYPE,ConfigurationFile.Constants.CONTENT_TYPE,mailRequest)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(integerResponse -> {
                        CustomUtils.getInstance().cancelDialog();
                        System.out.println("Register Success Mail CODE:"+integerResponse.body());
                        if(integerResponse.code()==ConfigurationFile.Constants.SUCCESS_CODE_second){
                            if(integerResponse.body()==0){
                                next();
                                System.out.println("Register Success Mail");
                            }else {
                                callback.updateUi(ConfigurationFile.Constants.EXISET_MAIL_CODE);

                            }
                        }

                    }, throwable -> {
                        CustomUtils.getInstance().cancelDialog();
                        System.out.println("exiset mail Ex:"+throwable.getMessage());

                    });
        }else{
            callback.updateUi(ConfigurationFile.Constants.NO_INTERNET_CONNECTION_CODE);
        }
    }

    public void nextAction(View view){
        checkEmptyData();
    }

    public void next(){
        Intent intent=new Intent(context, CompanyRegisterActivityStep2.class);
        intent.putExtra(ConfigurationFile.SharedPrefConstants.PREF_REGISTER_OBJECT,companyRegisterRequest);
        context.startActivity(intent);

    }

    public void getCountries(View view){

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
        if (data != null){
            if (requestCode == ConfigurationFile.IntentConstants.REQUEST_CODE_COUNTRY) {
                cityFlag=true;
                country = (Country) data.getSerializableExtra(ConfigurationFile.IntentConstants.COUNTRY_DATA);
                setCountryName();
            } else if (requestCode == ConfigurationFile.IntentConstants.REQUEST_CODE_CITY) {
                city = (City) data.getSerializableExtra(ConfigurationFile.IntentConstants.CITY_DATA);
                setCityName();
            }
    }
    }

    public void setCountryName(){
        countryName.set(country.getName()!=null?country.getName():"");
    }
    public void setCityName(){
        cityName.set(city.getName()!=null?city.getName():"");
    }
}
