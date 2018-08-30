package dp.com.amarapp.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.Observable;

import dp.com.amarapp.model.response.CompaniesSearchResponse;
import dp.com.amarapp.model.response.CompanyLoginResponse;
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

public class CompanySearchViewModel extends Observable {

    private Context context;
    private BaseInterface callback;
    public ObservableList<CompanyLoginResponse> searchResponse;
    private String countryId;
    private String cityId;
    private String categoryId;
    private String specializationId;
    private String sort;
    private String next=null;
    private String pageId="0";
    private boolean loading=false;

    public CompanySearchViewModel(Context context, BaseInterface callback) {
        this.context = context;
        this.callback = callback;
        searchResponse=new ObservableArrayList<>();
        search(null,pageId);
    }

    public void search(String sort,String _pageID) {
        if (NetWorkConnection.isConnectingToInternet(context)) {
            CustomUtils.getInstance().showProgressDialog((Activity) context);
            loading=true;
            ApiClient.getClient().create(EndPoints.class).companySearch(ConfigurationFile.Constants.API_KEY,
                    ConfigurationFile.Constants.CONTENT_TYPE, ConfigurationFile.Constants.CONTENT_TYPE,
                    countryId,cityId,specializationId,categoryId,sort,_pageID)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(companiesSearchResponseResponse -> {
                        CustomUtils.getInstance().cancelDialog();
                        System.out.println("Code is : "+companiesSearchResponseResponse.code());
                        System.out.println("id1 :"+categoryId+"  id2 : "+cityId+"  id3 : "+categoryId+"  id4 : "+specializationId+"id5 :"+sort);
                        if (companiesSearchResponseResponse.code() == ConfigurationFile.Constants.SUCCESS_CODE_second) {
                            System.out.println(" city name View model :"+companiesSearchResponseResponse.
                                    body().getSearchResponses().get(0).getCity().getName());

                            callback.updateUi(ConfigurationFile.Constants.SUCCESS_CODE_second);
                            if(sort!=null) {
                                searchResponse.clear();
                            }
                            next=companiesSearchResponseResponse.body().getLinks().getNext();
                            if(next!=null){
                                pageId=next.substring(next.length()-1);
                            }
                            searchResponse.addAll(companiesSearchResponseResponse.body().getSearchResponses());
                            loading=false;
                        }
                    }, throwable -> {
                        CustomUtils.getInstance().cancelDialog();
                        System.out.println("ERROR 1" + throwable);
                    });

        } else {
            callback.updateUi(ConfigurationFile.Constants.NO_INTERNET_CONNECTION_CODE);
        }
    }

    public void setPrams(int countryId,int cityId,int categoryId,int specializationId){
        if(categoryId>0)
            this.countryId=String.valueOf(countryId);
        else
            this.categoryId=null;
        if(cityId>0)
            this.cityId=String.valueOf(cityId);
        else
            this.cityId=null;
        if(categoryId>0)
            this.categoryId=String.valueOf(categoryId);
        else
            this.categoryId=null;
        if (specializationId>0)
            this.specializationId=String.valueOf(specializationId);
        else
            this.specializationId=null;
        //System.out.println("id1 :"+categoryId+"  id2 : "+cityId+"  id3 : "+categoryId+"  id4 : "+specializationId);
    }

    public RecyclerView.OnScrollListener onScrollListener(){
        return new RecyclerView.OnScrollListener(){
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition()==searchResponse.size()-1) {
                    if (next != null && loading == false) {
                        loading = true;
                        System.out.println("Load More Data Success ");
                        search(null,pageId);
                    }
                }
            }
        };
    }
}
