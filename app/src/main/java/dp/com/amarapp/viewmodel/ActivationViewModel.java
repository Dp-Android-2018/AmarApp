package dp.com.amarapp.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.view.View;

import dp.com.amarapp.model.response.DefaultResponse;
import dp.com.amarapp.network.ApiClient;
import dp.com.amarapp.network.EndPoints;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.utils.CustomUtils;
import dp.com.amarapp.utils.NetWorkConnection;
import dp.com.amarapp.view.activity.PhoneActivity;
import dp.com.amarapp.view.callback.BaseInterface;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class ActivationViewModel {
    Context context;
    BaseInterface callback;
    private String token="Bearer ";
    public ObservableField<String> name;

    public ActivationViewModel(Context context,BaseInterface callback) {
        this.context = context;
        this.callback=callback;
        name=new ObservableField<>();
        System.out.println("name on splash :"+ CustomUtils.getInstance().getSaveUserObject(context).getName());
        name.set(CustomUtils.getInstance().getSaveUserObject(context).getName());
        token+= CustomUtils.getInstance().getSaveUserObject(context).getToken();
    }
    public void sendNewMail(View view){
        sendActivationMail();
    }

    public void activeUsingPhone(View view){
        Intent intent=new Intent(context, PhoneActivity.class);
        context.startActivity(intent);
    }

    public void sendActivationMail(){
        if(NetWorkConnection.isConnectingToInternet(context)){
            CustomUtils.getInstance().showProgressDialog(context);
            ApiClient.getClient().create(EndPoints.class).sendActivationMail(ConfigurationFile.Constants.API_KEY,
                    ConfigurationFile.Constants.CONTENT_TYPE, ConfigurationFile.Constants.CONTENT_TYPE,token)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Response<DefaultResponse>>() {
                        @Override
                        public void accept(Response<DefaultResponse> defaultResponseResponse) throws Exception {
                            CustomUtils.getInstance().cancelDialog();
                            System.out.println("Code is :"+defaultResponseResponse.code());
                            if(defaultResponseResponse.code()== ConfigurationFile.Constants.SUCCESS_CODE_second){
                                System.out.println("Message :"+defaultResponseResponse.body().getMessage());
                                callback.updateUi(ConfigurationFile.Constants.SUCCESS_CODE_second);
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
}
