package dp.com.amarapp.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.databinding.ObservableField;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.Observable;

import dp.com.amarapp.model.request.ChangePasswordRequest;
import dp.com.amarapp.network.ApiClient;
import dp.com.amarapp.network.EndPoints;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.utils.CustomBinder;
import dp.com.amarapp.utils.CustomUtils;
import dp.com.amarapp.utils.NetWorkConnection;
import dp.com.amarapp.utils.ValidationUtils;
import dp.com.amarapp.view.callback.BaseInterface;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.operators.observable.ObservableSerialized;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class ChangePasswordViewModel extends Observable {

    public ObservableField<String> oldPassword;

    public ObservableField<String> newPassword;
    public ObservableField<String> confNewPassword;
    private ChangePasswordRequest changePasswordRequest;
    private BaseInterface callback;
    private Context context;
    private String token="Bearer ";

    public ChangePasswordViewModel(BaseInterface callback, Context context) {
        this.callback = callback;
        this.context = context;
        token+=CustomUtils.getInstance().getSaveUserObject(context).getToken();
        initializeVariables();
    }
    public void checkEmptyData(){
        if(ValidationUtils.isEmpty(oldPassword.get())||
                ValidationUtils.isEmpty(newPassword.get())||
                ValidationUtils.isEmpty(confNewPassword.get())) {
            callback.updateUi(ConfigurationFile.Constants.FILL_ALL_DATA_ERROR);
            return;
        }else{
            if(newPassword.get().length()<8){
                callback.updateUi(ConfigurationFile.Constants.INVALED_PASSWORD);
                return;
            }else {
                if(! newPassword.get().equals(confNewPassword.get())){
                    callback.updateUi(ConfigurationFile.Constants.PASSWORD_MATCHES_CODE);
                    return;
                }
                else if(oldPassword.get().equals(newPassword.get())){
                    callback.updateUi(ConfigurationFile.Constants.NEW_PASS_EQUAL_OLD_PASS);
                }
                changePasswordRequest=new ChangePasswordRequest(oldPassword.get().trim(),newPassword.get().trim(),confNewPassword.get().trim());
                confirm();
            }
        }
    }

    public void initializeVariables() {
        oldPassword = new ObservableField<>();
        newPassword = new ObservableField<>();
        confNewPassword = new ObservableField<>();
    }
    public void confirmAction(View view){
        checkEmptyData();
    }
    public void confirm(){
        if(NetWorkConnection.isConnectingToInternet(context)){
            System.out.println("token1 is : "+token);
            CustomUtils.getInstance().showProgressDialog((Activity)context);
            ApiClient.getClient().create(EndPoints.class).changePassword(ConfigurationFile.Constants.API_KEY,
                    ConfigurationFile.Constants.CONTENT_TYPE,ConfigurationFile.Constants.CONTENT_TYPE,"Bearer B32JCEsYOMDdwDGRVpTkFrZkWhVwPZvngzl3tlgpoVnY6O1qr0zCiMuvZHUL",changePasswordRequest)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(stringResponse -> {
                        System.out.println("Object Response :"+stringResponse);
                        CustomUtils.getInstance().cancelDialog();
                        System.out.println("code change  is :"+stringResponse.code());
                        System.out.println("message is : "+stringResponse.message());
                        System.out.println("token is : "+token);
                        if(stringResponse.code()==ConfigurationFile.Constants.SUCCESS_CODE_second){
                            callback.updateUi(ConfigurationFile.Constants.SUCCESS_CODE_second);
                        }
                        else if(stringResponse.code()==ConfigurationFile.Constants.SUSBENDED){
                            callback.updateUi(ConfigurationFile.Constants.SUSBENDED);
                        }
                        else if (stringResponse.code()==ConfigurationFile.Constants.INVALED_EMAIL_PASSWORD){
                            callback.updateUi(ConfigurationFile.Constants.INVALED_EMAIL_PASSWORD);
                        }
                    }, throwable -> {
                        System.out.println("error in change password :"+throwable);
                        CustomUtils.getInstance().cancelDialog();

                    });

        }else {
            callback.updateUi(ConfigurationFile.Constants.NO_INTERNET_CONNECTION_CODE);
        }
    }
}
