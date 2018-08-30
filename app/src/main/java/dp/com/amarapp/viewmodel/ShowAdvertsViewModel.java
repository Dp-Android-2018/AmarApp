package dp.com.amarapp.viewmodel;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import java.util.ArrayList;
import java.util.List;

import dp.com.amarapp.model.pojo.AdvertContent;
import dp.com.amarapp.model.response.AdvertResponse;
import dp.com.amarapp.network.ApiClient;
import dp.com.amarapp.network.EndPoints;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.utils.CustomUtils;
import dp.com.amarapp.utils.NetWorkConnection;
import dp.com.amarapp.view.callback.BaseInterface;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class ShowAdvertsViewModel {
    Context context;
    BaseInterface callback;
    public ObservableList<AdvertContent> adverts;

    public ShowAdvertsViewModel(Context context,BaseInterface callback) {
        this.context = context;
        this.callback=callback;
        adverts=new ObservableArrayList<>();
        getAdverts();
    }

    public void getAdverts(){
        if(NetWorkConnection.isConnectingToInternet(context)){
            CustomUtils.getInstance().showProgressDialog(context);
            ApiClient.getClient().create(EndPoints.class).getAdverts(ConfigurationFile.Constants.API_KEY,
                    ConfigurationFile.Constants.CONTENT_TYPE,ConfigurationFile.Constants.CONTENT_TYPE)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(advertResponseResponse -> {
                        CustomUtils.getInstance().cancelDialog();
                        System.out.println("adverts response code : "+advertResponseResponse.code());
                        if(advertResponseResponse.code()==ConfigurationFile.Constants.SUCCESS_CODE_second){
                            adverts.addAll(advertResponseResponse.body().getAdvertContent());
                            System.out.println("adverts data : "+adverts.get(0).getTitle());
                        }
                    }, throwable -> CustomUtils.getInstance().cancelDialog());

        }else{
            callback.updateUi(ConfigurationFile.Constants.NO_INTERNET_CONNECTION_CODE);

        }
    }
}
