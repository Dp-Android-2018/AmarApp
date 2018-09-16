package dp.com.amarapp.viewmodel;

import android.app.Activity;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;

import dp.com.amarapp.model.pojo.BaseObjParentDay;
import dp.com.amarapp.model.pojo.FullTimeWorkDay;
import dp.com.amarapp.model.pojo.WorkDay;
import dp.com.amarapp.model.response.CompanyWorkDaysResponse;
import dp.com.amarapp.network.ApiClient;
import dp.com.amarapp.network.EndPoints;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.utils.CustomUtils;
import dp.com.amarapp.view.adapter.SetWorkingDaysAdapter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class CompanyProfileViewModel_7 extends Observable {
    Activity activity;
    private ObservableList<WorkDay>workDays;
    private String token="Bearer ";
    private int id;
    SetWorkingDaysAdapter adapter;
    RecyclerView view;

    public CompanyProfileViewModel_7(Activity activity, RecyclerView view) {
        this.activity = activity;
        workDays=new ObservableArrayList<>();
        this.view=view;
        token += CustomUtils.getInstance().getSaveUserObject(activity).getToken();
        id=CustomUtils.getInstance().getSaveUserObject(activity).getId();
        getWorkDays();
    }


    public void getWorkDays(){
        CustomUtils.getInstance().showProgressDialog(activity);
        ApiClient.getClient().create(EndPoints.class).companyWorkDays(ConfigurationFile.Constants.API_KEY,
                ConfigurationFile.Constants.CONTENT_TYPE,ConfigurationFile.Constants.CONTENT_TYPE,token,id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Response<CompanyWorkDaysResponse>>() {
                    @Override
                    public void accept(Response<CompanyWorkDaysResponse> companyWorkDaysResponseResponse) throws Exception {
                        CustomUtils.getInstance().cancelDialog();
                        System.out.println("Code Work Days :"+companyWorkDaysResponseResponse.code());
                        System.out.println("token is : "+token);
                        if(companyWorkDaysResponseResponse.code()==ConfigurationFile.Constants.SUCCESS_CODE_second){
                            workDays.addAll(companyWorkDaysResponseResponse.body().getWorkDays());
                            System.out.println("work days size :"+workDays.size());
                            adapter=new SetWorkingDaysAdapter(getHandleDays());
                            view.setAdapter(adapter);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("ERRor +"+throwable.getMessage());
                    }
                });
    }

    public ArrayList<BaseObjParentDay> getHandleDays(){
      ArrayList<BaseObjParentDay>list=new ArrayList<>();
        list.add(new BaseObjParentDay("saturday",getDayItem("saturday")));
        list.add(new BaseObjParentDay("sunday", getDayItem("sunday")));
        list.add(new BaseObjParentDay("monday", getDayItem("monday")));
        list.add(new BaseObjParentDay("tuesday", getDayItem("tuesday")));
        list.add(new BaseObjParentDay("wednesday", getDayItem("wednesday")));
        list.add(new BaseObjParentDay("thursday", getDayItem("thursday")));
        list.add(new BaseObjParentDay("friday", getDayItem("friday")));
        return list;
    }
    public List<FullTimeWorkDay>getDayItem(String day){
        System.out.println("method :"+day);
        System.out.println("work days size on method :"+workDays.size());
        FullTimeWorkDay fullTimeWorkDay=new FullTimeWorkDay();
        for(int i=0;i<workDays.size();i++){
            System.out.println("day from workdays :"+workDays.get(i).getDay()+"/"+workDays.get(i).getShift()+"/"+workDays.get(i).getFrom()+"/"+workDays.get(i).getTo());
            if(workDays.get(i).getDay().equals(day)){
                if(workDays.get(i).getShift().equals("morning")){
                    fullTimeWorkDay.setMfrom(workDays.get(i).getFrom());
                    fullTimeWorkDay.setmTo(workDays.get(i).getTo());
                }else if(workDays.get(i).getShift().equals("night")){
                    fullTimeWorkDay.setnFrom(workDays.get(i).getFrom());
                    fullTimeWorkDay.setnTo(workDays.get(i).getTo());
                }
            }
        }
        System.out.println("Day : "+day+":"+fullTimeWorkDay.getMfrom()+"/"+fullTimeWorkDay.getnFrom()+"/"+fullTimeWorkDay.getmTo()+"/"+fullTimeWorkDay.getnTo());
        return Arrays.asList(fullTimeWorkDay);
    }


}
