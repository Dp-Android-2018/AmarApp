package dp.com.amarapp.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.graphics.Bitmap;
import android.view.View;

import java.util.Observable;

import dp.com.amarapp.model.request.ForgetPasswordRequest;
import dp.com.amarapp.model.response.DefaultResponse;
import dp.com.amarapp.network.ApiClient;
import dp.com.amarapp.network.EndPoints;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.utils.CustomUtils;
import dp.com.amarapp.utils.NetWorkConnection;
import dp.com.amarapp.utils.ValidationUtils;
import dp.com.amarapp.view.activity.PhoneActivity;
import dp.com.amarapp.view.callback.BaseInterface;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class ForgetPasswordViewModel extends Observable {
    public ObservableField<String> email;
    private Context context;
    private BaseInterface callback;
    private ForgetPasswordRequest request;

    public ForgetPasswordViewModel(Context context, BaseInterface callback) {
        this.context = context;
        this.callback = callback;
        email=new ObservableField<>();
    }

    public void chechEmptyData(View view){
        if(ValidationUtils.isEmpty(email.get())){
            callback.updateUi(ConfigurationFile.Constants.FILL_ALL_DATA_ERROR);
        }else if(!ValidationUtils.isMail(email.get())){
            callback.updateUi(ConfigurationFile.Constants.INVALED_EMAIL);
        }else{
            request=new ForgetPasswordRequest();
            request.setEmail(email.get());
            callApi();
        }
    }

    public void callApi(){
        if(NetWorkConnection.isConnectingToInternet(context)){
            CustomUtils.getInstance().showProgressDialog(context);
            ApiClient.getClient().create(EndPoints.class).forgetPassword(ConfigurationFile.Constants.API_KEY,
                    ConfigurationFile.Constants.CONTENT_TYPE,ConfigurationFile.Constants.CONTENT_TYPE,request)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Response<DefaultResponse>>() {
                        @Override
                        public void accept(Response<DefaultResponse> defaultResponseResponse) throws Exception {
                            CustomUtils.getInstance().cancelDialog();
                            System.out.println("Cod on forget Password is : "+defaultResponseResponse.code());
                            if (defaultResponseResponse.code()==ConfigurationFile.Constants.SUCCESS_CODE_second){
                                callback.updateUi(ConfigurationFile.Constants.SUCCESS_CODE_second);
                            }else {
                                callback.updateUi(ConfigurationFile.Constants.INVALED_DATA_CODE);
                            }

                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            CustomUtils.getInstance().cancelDialog();
                            System.out.println("EX on forget password :"+throwable.getMessage());

                        }
                    });

        }else {
            callback.updateUi(ConfigurationFile.Constants.NO_INTERNET_CONNECTION_CODE);
        }

    }
}
