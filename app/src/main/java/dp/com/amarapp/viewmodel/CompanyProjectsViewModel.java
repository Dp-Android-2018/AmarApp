package dp.com.amarapp.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import dp.com.amarapp.model.pojo.CompanyProject;
import dp.com.amarapp.model.pojo.LoginResponseContent;
import dp.com.amarapp.model.response.CompanyProjectResponse;
import dp.com.amarapp.network.ApiClient;
import dp.com.amarapp.network.EndPoints;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.utils.CustomUtils;
import dp.com.amarapp.utils.NetWorkConnection;
import dp.com.amarapp.view.callback.BaseInterface;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class CompanyProjectsViewModel {

    public ObservableList<CompanyProject> projectList;
    private Context context;
    private BaseInterface callback;
    private String token="Bearer ";
    private int id;
    public CompanyProjectsViewModel(Context context,BaseInterface callback) {
        this.context = context;
        this.callback=callback;
        projectList=new ObservableArrayList<>();
        token +=CustomUtils.getInstance().getSaveUserObject(context).getToken();
        id=(int)((Activity)context).getIntent().getIntExtra(ConfigurationFile.IntentConstants.COMPANYID,1);
        getProjects();
    }

    public void getProjects(){
        if(NetWorkConnection.isConnectingToInternet(context)){
            CustomUtils.getInstance().showProgressDialog((Activity)context);
            ApiClient.getClient().create(EndPoints.class).companyProjects(ConfigurationFile.Constants.API_KEY,
                    ConfigurationFile.Constants.CONTENT_TYPE,ConfigurationFile.Constants.CONTENT_TYPE,token,id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(companyProjectResponseResponse -> {
                        CustomUtils.getInstance().cancelDialog();
                        if(companyProjectResponseResponse.code()==ConfigurationFile.Constants.SUCCESS_CODE_second){
                            callback.updateUi(ConfigurationFile.Constants.SUCCESS_CODE_second);
                            System.out.println("Data here :->"+companyProjectResponseResponse.body());
                            projectList.addAll(companyProjectResponseResponse.body().getResponse());
                            System.out.println("Company project name in view model : "+projectList.get(0).getName());
                            System.out.println("Company project address"+projectList.get(0).getAddress());
                        }else{
                            System.out.println("Code is : "+companyProjectResponseResponse.code());
                            System.out.println("Token is:"+token);
                            System.out.println("ID is : "+id);
                            callback.updateUi(777);
                        }
                    }, throwable -> {
                        CustomUtils.getInstance().cancelDialog();
                        System.out.println("ERROR ON COmpany projects"+throwable);
                    });

        }else {
            callback.updateUi(ConfigurationFile.Constants.NO_INTERNET_CONNECTION_CODE);
        }

    }
}
