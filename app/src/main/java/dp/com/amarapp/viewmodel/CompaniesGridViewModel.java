package dp.com.amarapp.viewmodel;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ObservableList;
import android.view.View;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.Toast;

import dp.com.amarapp.R;
import dp.com.amarapp.model.response.CompanyLoginResponse;
import dp.com.amarapp.network.ApiClient;
import dp.com.amarapp.network.EndPoints;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.utils.CustomUtils;
import dp.com.amarapp.utils.NetWorkConnection;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CompaniesGridViewModel {
    private Context context;
    public ObservableList<CompanyLoginResponse> companies;
    private String next=null;
    private String pageId="0";
    private boolean loading=false;
    public ObservableInt visibality;
    public ObservableField<String> text;

    public CompaniesGridViewModel(Context context) {
        this.context = context;
        companies=new ObservableArrayList<>();
        text=new ObservableField<>();
        visibality=new ObservableInt(View.GONE);
        getCompanies(pageId);
        System.out.println("Companies grid Size view model: "+companies.size());
    }

    public void getCompanies(String _pageID) {
        System.out.println("page id grid : "+_pageID);
        if (NetWorkConnection.isConnectingToInternet(context)) {
            CustomUtils.getInstance().showProgressDialog(context);
            loading=true;
            ApiClient.getClient().create(EndPoints.class).companySearch(ConfigurationFile.Constants.API_KEY,
                    ConfigurationFile.Constants.CONTENT_TYPE, ConfigurationFile.Constants.CONTENT_TYPE,
            null,null,null,null,null,_pageID)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(companiesSearchResponseResponse -> {
                        CustomUtils.getInstance().cancelDialog();
                        if (companiesSearchResponseResponse.code() == ConfigurationFile.Constants.SUCCESS_CODE_second) {
                            //callback.updateUi(ConfigurationFile.Constants.SUCCESS_CODE_second);
                            companies.addAll(companiesSearchResponseResponse.body().getSearchResponses());
                            next=companiesSearchResponseResponse.body().getLinks().getNext();
                            if(next!=null){
                                pageId=next.substring(next.length()-1);
                            }
                            loading=false;
                            System.out.println("Companies grid Size: "+companies.size());
                            if(companies.size()<=0){
                                text.set("لا يوجد شركات");
                                visibality.set(View.VISIBLE);
                            }
                        }
                    }, throwable -> {
                        CustomUtils.getInstance().cancelDialog();
                        System.out.println("ERROR 1" + throwable);
                        text.set("حدث خطأ فى المصادفة تأكد من إتصال الإنترنت");
                        visibality.set(View.VISIBLE);

                    });

        } else {
            Toast.makeText(context, R.string.no_internet_connection,Toast.LENGTH_LONG).show();
        }
    }

    public GridView.OnScrollListener onScrollListener(){
        return new GridView.OnScrollListener(){
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(firstVisibleItem+visibleItemCount>=totalItemCount){
                    if (next != null && loading == false) {
                        loading = true;
                        System.out.println("Load More Data Success ");
                        getCompanies(pageId);
                    }
                }
            }
        };
    }
}
