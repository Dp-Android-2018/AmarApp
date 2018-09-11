package dp.com.amarapp.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.view.View;

import java.util.Observable;

import dp.com.amarapp.model.pojo.City;
import dp.com.amarapp.model.pojo.LoginResponseContent;
import dp.com.amarapp.model.request.CheckMailRequest;
import dp.com.amarapp.model.request.CheckPhoneRequest;
import dp.com.amarapp.model.request.ClientRegisterRequest;
import dp.com.amarapp.model.response.Country;
import dp.com.amarapp.network.ApiClient;
import dp.com.amarapp.network.EndPoints;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.utils.CustomUtils;
import dp.com.amarapp.utils.NetWorkConnection;
import dp.com.amarapp.utils.SharedPrefrenceUtils;
import dp.com.amarapp.utils.ValidationUtils;
import dp.com.amarapp.view.activity.ActivationActivity;
import dp.com.amarapp.view.callback.BaseInterface;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by DELL on 21/07/2018.
 */

public class ClientRegisterViewModel extends Observable{

    public ObservableField<String> name;
    public ObservableField<String> email;
    public ObservableField<String> phone;
    public ObservableField<String> countryName;
    public ObservableField<String> cityName;
    public ObservableField<String> password;
    public ObservableField<String> confirm_password;
    private CompositeDisposable compositeDisposable;
    private Context context;
    private BaseInterface callback;
    private ClientRegisterRequest clientRegisterRequest;
    private CheckPhoneRequest phoneRequest;
    private CheckMailRequest checkMailRequest;
    SharedPrefrenceUtils pref;
    private boolean cityFlag;
    private Country country;
   private City city;

    public ClientRegisterViewModel(Context context, BaseInterface callback) {
        this.context = context;
        this.callback = callback;
        initializeVariables();
    }


    public void initializeVariables(){
        name=new ObservableField<>();
        email=new ObservableField<>();
        phone=new ObservableField<>();
        password=new ObservableField<>();
        confirm_password=new ObservableField<>();
        compositeDisposable=new CompositeDisposable();
        countryName=new ObservableField<>();
        cityName=new ObservableField<>();
        cityFlag=false;
    }



    public void registerAction(View view){
        if (checkEmptyData()){
            if (!checkMail()){
                callback.updateUi(ConfigurationFile.Constants.INVALED_EMAIL);
            }else if(!checkPhone()) {
                callback.updateUi(ConfigurationFile.Constants.INVALED_PHONE);
            }else if(!checkPassword()){
                callback.updateUi(ConfigurationFile.Constants.INVALED_PASSWORD);

            }else if (!password.get().equals(confirm_password.get())){
                callback.updateUi(ConfigurationFile.Constants.PASSWORD_MATCHES_CODE);
            }else{
                clientRegisterRequest=new ClientRegisterRequest(name.get(),email.get(),password.get(),confirm_password.get(),
                        phone.get(),city.getId(),country.getId());
                clientRegisterRequest.setDeviceToken(CustomUtils.getInstance().getFirebaseToken(context));
                phoneRequest=new CheckPhoneRequest(phone.get());
                checkMailRequest=new CheckMailRequest(email.get());
                checkExistMail();
            }
        }
    }
    public boolean checkEmptyData() {
        if (ValidationUtils.isEmpty(name.get()) ||
                ValidationUtils.isEmpty(email.get()) ||
                ValidationUtils.isEmpty(phone.get()) ||
                ValidationUtils.isEmpty(password.get()) ||
                ValidationUtils.isEmpty(confirm_password.get()) ||
                country.getId() <= 0 || city.getId()<=0) {
            callback.updateUi(ConfigurationFile.Constants.FILL_ALL_DATA_ERROR);
            return false;}else return true;

    }
    public boolean checkMail(){

        return ValidationUtils.isMail(email.get());
    }

    public boolean checkPhone(){
        return ValidationUtils.isPhone(phone.get());
    }
    public boolean checkPassword(){
        if(password.get().length()<8 ){
            return false;
        }
        return true;
    }



    public void saveDataToPrefs(LoginResponseContent data){
        pref=new SharedPrefrenceUtils(context);
        pref.saveObjectToSharedPreferences(ConfigurationFile.SharedPrefConstants.PREF_AMAR_CLIENT_OBJ_DATA,data);
    }

    public void checkExistMail(){
        if(NetWorkConnection.isConnectingToInternet(context)){
            CustomUtils.getInstance().showProgressDialog((Activity)context);
            System.out.println("Register Success Mail :"+email.get());
            ApiClient.getClient().create(EndPoints.class).checkMail(ConfigurationFile.Constants.API_KEY,
                    ConfigurationFile.Constants.CONTENT_TYPE,ConfigurationFile.Constants.CONTENT_TYPE,checkMailRequest)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(integerResponse -> {
                        CustomUtils.getInstance().cancelDialog();
                        System.out.println("Register Success Mail CODE:"+integerResponse.body());
                        if(integerResponse.code()==ConfigurationFile.Constants.SUCCESS_CODE_second){
                            if(integerResponse.body()==0){
                                checkEsistPhone();
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

    public void checkEsistPhone(){
        if(NetWorkConnection.isConnectingToInternet(context)){
            CustomUtils.getInstance().showProgressDialog((Activity)context);
            ApiClient.getClient().create(EndPoints.class).checkPhone(ConfigurationFile.Constants.API_KEY,
                    ConfigurationFile.Constants.CONTENT_TYPE,ConfigurationFile.Constants.CONTENT_TYPE,phoneRequest)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(integerResponse -> {
                        CustomUtils.getInstance().cancelDialog();
                        if(integerResponse.code()==ConfigurationFile.Constants.SUCCESS_CODE_second){
                            if(integerResponse.body()==0){
                                register();
                            }else{

                                callback.updateUi(ConfigurationFile.Constants.EXISET_PHONE_CODE);
                            }
                        }

                    }, throwable -> {
                        CustomUtils.getInstance().cancelDialog();
                        System.out.println("Exiset phone Ex:"+throwable.getMessage());

                    });
        }else{
            callback.updateUi(ConfigurationFile.Constants.NO_INTERNET_CONNECTION_CODE);
        }
    }

    public void register(){
        if(NetWorkConnection.isConnectingToInternet(context)){
            CustomUtils.getInstance().showProgressDialog((Activity)context);
            ApiClient.getClient().create(EndPoints.class).clientRegister(ConfigurationFile.Constants.API_KEY,
                    ConfigurationFile.Constants.CONTENT_TYPE, ConfigurationFile.Constants.CONTENT_TYPE,clientRegisterRequest)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(clientRegisterResponseResponse -> {
                        CustomUtils.getInstance().cancelDialog();
                        System.out.println("Code :"+ clientRegisterResponseResponse.code());
                        switch (clientRegisterResponseResponse.code()){

                            case ConfigurationFile.Constants.SUCCESS_CODE:
                            {
                                saveDataToPrefs(clientRegisterResponseResponse.body().getResponse());
                                setChanged();
                                Intent intent=new Intent(context,ActivationActivity.class);
                                intent.putExtra(name.get(),ConfigurationFile.IntentConstants.USER_NAME);
                                context.startActivity(intent);
                                ((Activity)context).finishAffinity();
                                break;
                            }
                            case ConfigurationFile.Constants.INVALED_DATA_CODE:
                            {
                                callback.updateUi(ConfigurationFile.Constants.INVALED_DATA_CODE);
                                break;
                            }
                            case ConfigurationFile.Constants.SERVER_ERROR:
                            {
                                callback.updateUi(ConfigurationFile.Constants.SERVER_ERROR);
                                break;
                            }
                        }

                    }, throwable -> {
                        CustomUtils.getInstance().cancelDialog();
                        System.out.println("Client Register Ex:"+throwable.getMessage());
                    });
        }else {
            callback.updateUi(ConfigurationFile.Constants.NO_INTERNET_CONNECTION_CODE);
        }
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

    public void setCountryName(){
        countryName.set(country.getName()!=null?country.getName():"");
    }
    public void setCityName(){
        cityName.set(city.getName()!=null?city.getName():"");
    }


}
