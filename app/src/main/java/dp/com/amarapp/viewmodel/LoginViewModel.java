package dp.com.amarapp.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.view.View;

import java.util.Observable;

import dp.com.amarapp.model.pojo.LoginResponseContent;
import dp.com.amarapp.model.request.LoginRequest;
import dp.com.amarapp.model.response.LoginResponse;
import dp.com.amarapp.network.ApiClient;
import dp.com.amarapp.network.EndPoints;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.utils.CustomUtils;
import dp.com.amarapp.utils.NetWorkConnection;
import dp.com.amarapp.utils.SharedPrefrenceUtils;
import dp.com.amarapp.utils.ValidationUtils;
import dp.com.amarapp.view.activity.ClientRegisterActivity;
import dp.com.amarapp.view.activity.ContainerActivity;
import dp.com.amarapp.view.activity.LoginActivity;
import dp.com.amarapp.view.callback.BaseInterface;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by DELL on 22/07/2018.
 */

public class LoginViewModel extends Observable {

   public ObservableField<String> email;
    public ObservableField<String> password;
    private Context context;
    private BaseInterface callback;
    private LoginRequest loginRequest;
    private SharedPrefrenceUtils pref;

    public LoginViewModel(Context context,BaseInterface callback) {
        this.context=context;
        this.callback=callback;
        initializeVariables();
    }

    public void loginAction(View view){
        if(!checkEmptyData()){
            if(!checkMail()) {
                callback.updateUi(ConfigurationFile.Constants.INVALED_EMAIL);
            }else{
                loginRequest=new LoginRequest(email.get(),password.get());
                login();
            }
        }else{
            callback.updateUi(ConfigurationFile.Constants.FILL_ALL_DATA_ERROR);
        }
    }

    public boolean checkEmptyData(){
        if(ValidationUtils.isEmpty(email.get())||ValidationUtils.isEmpty(password.get())){
            return true;
        }
        return false;
    }

    public boolean checkMail(){

        return ValidationUtils.isMail(email.get());
    }


    public void initializeVariables() {
        email = new ObservableField<>();
        password = new ObservableField<>();
    }


    public void login(){
        if(NetWorkConnection.isConnectingToInternet(context)){
            CustomUtils.getInstance().showProgressDialog((Activity)context);
            ApiClient.getClient().create(EndPoints.class).login(ConfigurationFile.Constants.API_KEY,
                    ConfigurationFile.Constants.CONTENT_TYPE,ConfigurationFile.Constants.CONTENT_TYPE,loginRequest)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(loginResponseResponse -> {
                        CustomUtils.getInstance().cancelDialog();
                        switch (loginResponseResponse.code()){
                            case (ConfigurationFile.Constants.SUCCESS_CODE_second):
                            {
                                saveDataToPrefs(loginResponseResponse.body().getLoginResponseContent());
                                System.out.println("data saved to pref is : "+CustomUtils.getInstance().getSaveUserObject(context));

                                callback.updateUi(ConfigurationFile.Constants.SUCCESS_CODE_second);
                                break;
                            }
                            case (ConfigurationFile.Constants.INVALED_EMAIL_PASSWORD):
                            {
                                callback.updateUi(ConfigurationFile.Constants.INVALED_EMAIL_PASSWORD);
                                break;
                            }
                            case (ConfigurationFile.Constants.INVALED_DATA_CODE):
                            {
                                callback.updateUi(ConfigurationFile.Constants.INVALED_DATA_CODE);
                                break;
                            }
                            case (ConfigurationFile.Constants.SUSBENDED):
                            {
                                callback.updateUi(ConfigurationFile.Constants.SUSBENDED);
                                break;
                            }
                        }

                    }, throwable -> {
                        CustomUtils.getInstance().cancelDialog();
                        System.out.println("ERROR login :"+throwable.getMessage());
                    });

        }else{
            callback.updateUi(ConfigurationFile.Constants.NO_INTERNET_CONNECTION_CODE);
        }

    }

    public void newUser(View view){
        callback.updateUi(ConfigurationFile.Constants.MOVE_TO_MEMBERSHIP_ACTIVITY);
    }
    public void fogetPassword(View view){
        callback.updateUi(ConfigurationFile.Constants.MOVE_TO_FORGET_PASSWORD_ACTIVITY);
    }
    public void skipRegister(View view){
        if(NetWorkConnection.isConnectingToInternet(context))
            callback.updateUi(ConfigurationFile.Constants.MOVE_TO_HOME_ACTIVITY);
        else
            callback.updateUi(ConfigurationFile.Constants.NO_INTERNET_CONNECTION_CODE);
    }

    public void saveDataToPrefs(LoginResponseContent data){
        pref=new SharedPrefrenceUtils(context);
        pref.saveObjectToSharedPreferences(ConfigurationFile.SharedPrefConstants.PREF_AMAR_CLIENT_OBJ_DATA,data);
    }




}
