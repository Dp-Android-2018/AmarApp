package dp.com.amarapp.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.view.View;

import dp.com.amarapp.model.pojo.LoginResponseContent;
import dp.com.amarapp.model.request.CodeRequest;
import dp.com.amarapp.model.response.CompanyLoginResponse;
import dp.com.amarapp.model.response.DefaultResponse;
import dp.com.amarapp.network.ApiClient;
import dp.com.amarapp.network.EndPoints;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.utils.CustomUtils;
import dp.com.amarapp.utils.NetWorkConnection;
import dp.com.amarapp.utils.SharedPrefrenceUtils;
import dp.com.amarapp.utils.ValidationUtils;
import dp.com.amarapp.view.callback.BaseInterface;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class ActivationCodeViewModel {
    Context context;
    BaseInterface callback;
    public ObservableField<String> code;
    private String token="Bearer ";
    CodeRequest codeRequest;
    SharedPrefrenceUtils pref;
    public ActivationCodeViewModel(Context context, BaseInterface callback) {
        this.context = context;
        this.callback = callback;
        token+= CustomUtils.getInstance().getSaveUserObject(context).getToken();
        code=new ObservableField<>();
    }

    public void checkEmptyData(View view){
        if(ValidationUtils.isEmpty(code.get())){
            callback.updateUi(ConfigurationFile.Constants.FILL_ALL_DATA_ERROR);
        }else {
            if(code.get().length()==4){
                System.out.println("Code from text : "+code.get());
            codeRequest=new CodeRequest(code.get());
            active();
            }else{
                callback.updateUi(ConfigurationFile.Constants.INVALED_DATA_CODE);
            }
        }
    }

    public void active(){
        if(NetWorkConnection.isConnectingToInternet(context)){
            CustomUtils.getInstance().showProgressDialog(context);
            ApiClient.getClient().create(EndPoints.class).activePhone(ConfigurationFile.Constants.API_KEY,
                    ConfigurationFile.Constants.CONTENT_TYPE, ConfigurationFile.Constants.CONTENT_TYPE,token,codeRequest)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Response<DefaultResponse>>() {
                        @Override
                        public void accept(Response<DefaultResponse> defaultResponseResponse) throws Exception {
                            CustomUtils.getInstance().cancelDialog();
                            System.out.println("Code Is Activation code  :"+defaultResponseResponse.code());
                            if (defaultResponseResponse.code()== ConfigurationFile.Constants.SUCCESS_CODE_second){
                                CompanyLoginResponse loginResponse=new CompanyLoginResponse();
                                loginResponse=(CompanyLoginResponse) CustomUtils.getInstance().getSaveUserObject(context);
                                loginResponse.setStatus("true");
                                saveDataToPrefs(loginResponse);
                                callback.updateUi(ConfigurationFile.Constants.SUCCESS_CODE_second);
                            }else if(defaultResponseResponse.code()== ConfigurationFile.Constants.ALREADY_ACTIVATED){
                                callback.updateUi(ConfigurationFile.Constants.ALREADY_ACTIVATED);
                            }
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            CustomUtils.getInstance().cancelDialog();
                        }
                    });

        }else{
            callback.updateUi(ConfigurationFile.Constants.NO_INTERNET_CONNECTION_CODE);
        }
    }

    public void saveDataToPrefs(LoginResponseContent data){
        pref=new SharedPrefrenceUtils(context);
        pref.saveObjectToSharedPreferences(ConfigurationFile.SharedPrefConstants.PREF_AMAR_CLIENT_OBJ_DATA,data);
    }

}
