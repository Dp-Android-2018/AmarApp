package dp.com.amarapp.viewmodel;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ObservableList;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.Observable;

import dp.com.amarapp.model.response.CompanyLoginResponse;
import dp.com.amarapp.network.ApiClient;
import dp.com.amarapp.network.EndPoints;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.utils.CustomUtils;
import dp.com.amarapp.utils.NetWorkConnection;
import dp.com.amarapp.view.callback.BaseInterface;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CompanySearchViewModel extends Observable {

    private Context context;
    private BaseInterface callback;
    public ObservableList<CompanyLoginResponse> searchResponse;
    private String countryId;
    private String cityId;
    private String categoryId;
    private String specializationId;
    public ObservableField<String>text;
    public ObservableInt visibality;
    private String sort;
    private String next=null;
    private String pageId="0";
    private boolean loading=false;

    public CompanySearchViewModel(Context context, BaseInterface callback,int countryId,int cityId,int categoryId,int specializationId) {
        setPrams(countryId,cityId,categoryId,specializationId);
        this.context = context;
        this.callback = callback;
        searchResponse=new ObservableArrayList<>();
        text=new ObservableField<>();
        visibality=new ObservableInt(View.GONE);
        search(null,pageId);
    }

    public void setPrams(int countryId,int cityId,int categoryId,int specializationId){
        if(countryId>0)
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
                       // loading = true;
                        System.out.println("Load More Data Success ");
                        search(null,pageId);
                    }
                }
            }
        };
    }

    public void search(String sort,String _pageID) {
        System.out.println("page id is : "+pageId);
        if (NetWorkConnection.isConnectingToInternet(context)) {
            CustomUtils.getInstance().showProgressDialog(context);
            loading=true;
            System.out.println("After Loading : "+loading);
            ApiClient.getClient().create(EndPoints.class).companySearch(ConfigurationFile.Constants.API_KEY,
                    ConfigurationFile.Constants.CONTENT_TYPE, ConfigurationFile.Constants.CONTENT_TYPE,
                    countryId,cityId,specializationId,categoryId,sort,_pageID)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(companiesSearchResponseResponse -> {
                        CustomUtils.getInstance().cancelDialog();
                        System.out.println("Country Is :"+countryId);
                        System.out.println("Code is : "+companiesSearchResponseResponse.code());
                        System.out.println("ids on company search view Model :"+countryId+"  id2 : "+cityId+"  id3 : "+categoryId+"  id4 : "+specializationId+"id5 :"+sort);
                        if (companiesSearchResponseResponse.code() == ConfigurationFile.Constants.SUCCESS_CODE_second) {
                            if(companiesSearchResponseResponse.body().getSearchResponses().size()==0){
                                callback.updateUi(999);
                            }
                            //callback.updateUi(ConfigurationFile.Constants.SUCCESS_CODE_second);
                            if(sort!=null) {
                                searchResponse.clear();
                            }
                            next=companiesSearchResponseResponse.body().getLinks().getNext();
                            System.out.println("next is : "+next);
                            if(next!=null){
                                pageId=next.substring(next.length()-1);
                            }
                            searchResponse.addAll(companiesSearchResponseResponse.body().getSearchResponses());
                            System.out.println("Size is : "+searchResponse.size());
                            loading=false;
                            if(searchResponse.size()<=0){
                                text.set("لا يوجد نتائج فى البحث");
                                visibality.set(View.VISIBLE);
                            }
                        }
                    }, throwable -> {
                        CustomUtils.getInstance().cancelDialog();
                        text.set("حدث خطأ فى المصادفة تأكد من إتصال الانترنت");
                        visibality.set(View.VISIBLE);
                    });

        } else {
            callback.updateUi(ConfigurationFile.Constants.NO_INTERNET_CONNECTION_CODE);
        }
    }
}
