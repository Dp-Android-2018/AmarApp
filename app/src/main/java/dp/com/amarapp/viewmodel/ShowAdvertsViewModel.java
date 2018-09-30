package dp.com.amarapp.viewmodel;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ObservableList;
import android.view.View;

import dp.com.amarapp.model.pojo.AdvertContent;
import dp.com.amarapp.network.ApiClient;
import dp.com.amarapp.network.EndPoints;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.utils.CustomUtils;
import dp.com.amarapp.utils.NetWorkConnection;
import dp.com.amarapp.view.callback.BaseInterface;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ShowAdvertsViewModel {
    Context context;
    BaseInterface callback;
    public ObservableList<AdvertContent> adverts;
    public ObservableInt visibality;
    public ObservableField<String> text;

    public ShowAdvertsViewModel(Context context,BaseInterface callback) {
        this.context = context;
        this.callback=callback;
        adverts=new ObservableArrayList<>();
        visibality=new ObservableInt(View.GONE);
        text=new ObservableField<>();
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
                            System.out.println("adverts size : "+adverts.size());
                            if(adverts.size()<=0){
                                System.out.println("adverts size2 : "+adverts.size());
                                text.set("لا يوجد اعلانات");
                                visibality.set(View.VISIBLE);
                            }
                            System.out.println("adverts data : "+adverts.get(0).getTitle());
                        }
                    }, throwable -> {
                        CustomUtils.getInstance().cancelDialog();
                        text.set("حدث خطأ فى المصادفة تأكد من إتصال الإنترنت");
                        visibality.set(View.VISIBLE);

                            }
                    );

        }else{
            callback.updateUi(ConfigurationFile.Constants.NO_INTERNET_CONNECTION_CODE);

        }
    }
}
