package dp.com.amarapp.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.view.View;

import dp.com.amarapp.model.request.CheckPhoneRequest;
import dp.com.amarapp.model.response.DefaultResponse;
import dp.com.amarapp.network.ApiClient;
import dp.com.amarapp.network.EndPoints;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.utils.CustomUtils;
import dp.com.amarapp.utils.NetWorkConnection;
import dp.com.amarapp.utils.ValidationUtils;
import dp.com.amarapp.view.callback.BaseInterface;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class PhoneViewModel {
    Context context;
    BaseInterface callback;
    public ObservableField<String> phone;
    private String token="Bearer ";
    CheckPhoneRequest phoneRequest;
    public PhoneViewModel(Context context, BaseInterface callback) {
        this.context = context;
        this.callback = callback;
        token+= CustomUtils.getInstance().getSaveUserObject(context).getToken();
        System.out.println("Token is : "+token);
        phone=new ObservableField<>();
    }

    public void checkEmptyData(View view){
        if(ValidationUtils.isEmpty(phone.get())){
            callback.updateUi(ConfigurationFile.Constants.FILL_ALL_DATA_ERROR);
        }else {
            phoneRequest=new CheckPhoneRequest(phone.get());
                sendCode();
        }
    }

    public void sendCode(){
        if(NetWorkConnection.isConnectingToInternet(context)){
            CustomUtils.getInstance().showProgressDialog(context);
            ApiClient.getClient().create(EndPoints.class).sendActivationCode(ConfigurationFile.Constants.API_KEY,
                    ConfigurationFile.Constants.CONTENT_TYPE, ConfigurationFile.Constants.CONTENT_TYPE,token,phoneRequest)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Response<DefaultResponse>>() {
                        @Override
                        public void accept(Response<DefaultResponse> defaultResponseResponse) throws Exception {
                            CustomUtils.getInstance().cancelDialog();
                            System.out.println("Code Is in  phone View Model :  "+defaultResponseResponse.code());
                            switch (defaultResponseResponse.code()){
                                case ConfigurationFile.Constants.SUCCESS_CODE_second:
                                {
                                    System.out.println("Code Is   phone view Model  "+defaultResponseResponse.code());
                                    callback.updateUi(ConfigurationFile.Constants.SUCCESS_CODE_second);
                                    break;
                                }
                                case ConfigurationFile.Constants.ALREADY_ACTIVATED:
                                {
                                    System.out.println("code in els in view model "+defaultResponseResponse.code());
                                    callback.updateUi(ConfigurationFile.Constants.ALREADY_ACTIVATED);
                                    break;
                                }
                                default:
                                    System.out.println("default :"+defaultResponseResponse.code());
                            }
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            CustomUtils.getInstance().cancelDialog();
                            System.out.println("Phone Ex :"+throwable.getMessage());

                        }
                    });

        }else{
            callback.updateUi(ConfigurationFile.Constants.NO_INTERNET_CONNECTION_CODE);
        }
    }

}
