package dp.com.amarapp.viewmodel;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import dp.com.amarapp.model.pojo.CategoriesContent;
import dp.com.amarapp.model.response.CategoriesResponse;
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

public class CategoriesViewModel {
    private Context context;
    private BaseInterface callback;
    public List<CategoriesContent>categoriesContentList;

    public CategoriesViewModel(Context context, BaseInterface callback) {
        this.context = context;
        this.callback = callback;
        categoriesContentList=new ArrayList<>();
        getCategories();
    }


    public void getCategories(){
        if(NetWorkConnection.isConnectingToInternet(context)){
            CustomUtils.getInstance().showProgressDialog(context);
            ApiClient.getClient().create(EndPoints.class).getCategories(ConfigurationFile.Constants.API_KEY,
                    ConfigurationFile.Constants.CONTENT_TYPE,ConfigurationFile.Constants.CONTENT_TYPE)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(categoriesResponseResponse -> {
                        CustomUtils.getInstance().cancelDialog();
                        System.out.println("Code Category : "+categoriesResponseResponse.code());
                        if (categoriesResponseResponse.code()==ConfigurationFile.Constants.SUCCESS_CODE_second){
                            System.out.println("Category Data : "+categoriesResponseResponse.body().toString());
                            categoriesContentList.addAll(categoriesResponseResponse.body().getCategories());
                            System.out.println("size Category View Model : "+categoriesResponseResponse.body().getCategories().size());
                            callback.updateUi(ConfigurationFile.Constants.CATEGORY_ADAPTER);
                        }

                    }, throwable -> {
                        CustomUtils.getInstance().cancelDialog();
                        System.out.println("Category ERRor : "+throwable.getMessage());

                    });

        }else{
            callback.updateUi(ConfigurationFile.Constants.NO_INTERNET_CONNECTION_CODE);
        }
    }

}
