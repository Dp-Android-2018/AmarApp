package dp.com.amarapp.viewmodel;

import android.app.Activity;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;

import dp.com.amarapp.model.pojo.ParentDay;
import dp.com.amarapp.model.pojo.WorkDay;
import dp.com.amarapp.model.response.CompanyWorkDaysResponse;
import dp.com.amarapp.network.ApiClient;
import dp.com.amarapp.network.EndPoints;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.utils.CustomUtils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class CompanyProfileViewModel_7 extends Observable {
    Activity activity;
    private ObservableList<WorkDay>workDays;
    private String token="Bearer ";
    private int id;



    public CompanyProfileViewModel_7(Activity activity) {
        this.activity = activity;
        workDays=new ObservableArrayList<>();
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
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("ERRor +"+throwable.getMessage());

                    }
                });
    }

    public ArrayList<ParentDay> getHandleDays(){
        ArrayList<ParentDay>list=new ArrayList<>();
        list.add(saturday());
        list.add(sunday());
        list.add(monday());
        list.add(tuesday());
        list.add(wednesday());
        list.add(thursday());
        list.add(friday());

        //list.add(secondDay());
        return list;
    }

    public ParentDay saturday(){
        //getWorkDays();
        System.out.println("satarday "+workDays.size());
        //WorkDay(int id, String day, String from, String to, String shift)
        int m=-1,n=-1,flag=0;
        if(workDays!=null) {
            for (int i = 0; i < workDays.size() && flag < 2; i++) {
                System.out.println("day 1 "+workDays.get(i).getDay()+":"+workDays.get(i).getFrom());
                if (workDays.get(i).getDay().equals("saturday")) {
                    if (workDays.get(i).getShift().equals("night")) {
                        n = i;
                        flag++;
                    } else if (workDays.get(i).getShift().equals("morning")) {
                        m = i;
                        flag++;
                    }
                }
            }
        }
        System.out.println("flag is :"+flag+" m is :"+m+" n is : "+n);
        if (flag==0){
            return new ParentDay("السبت", Arrays.asList(
                    new WorkDay(1,"","00:00","00:00","دوام صباحى"),
                    new WorkDay(2,"","00:00","00:00","دوام مسائى")
            ));
        }else if(flag==1&&n==-1){
            return new ParentDay("السبت",Arrays.asList(
                    new WorkDay(1,workDays.get(m).getDay(),workDays.get(m).getFrom(),workDays.get(m).getTo(),"دوام صباحى"),
                    new WorkDay(2,"","00:00","00:00","دوام مسائى")
            ));
        }else if(flag==1&&m==-1){
            return new ParentDay("السبت",Arrays.asList(
                    new WorkDay(1,"","00:00","00:00","دوام صباحى"),
                    new WorkDay(2,workDays.get(n).getDay(),workDays.get(n).getFrom(),workDays.get(n).getTo(),"دوام مسائى")
            ));
        }else {
            return new ParentDay("السبت",Arrays.asList(
                    new WorkDay(1,workDays.get(m).getDay(),workDays.get(m).getFrom(),workDays.get(m).getTo(),"دوام صباحى"),
                    new WorkDay(2,workDays.get(n).getDay(),workDays.get(n).getFrom(),workDays.get(n).getTo(),"دوام مسائى")
            ));
        }
    }
    public ParentDay sunday(){
        //getWorkDays();
        System.out.println("satarday "+workDays.size());
        //WorkDay(int id, String day, String from, String to, String shift)
        int m=-1,n=-1,flag=0;
        if(workDays!=null) {
            for (int i = 0; i < workDays.size() && flag < 2; i++) {
                System.out.println("day 1 "+workDays.get(i).getDay()+":"+workDays.get(i).getFrom());
                if (workDays.get(i).getDay().equals("sunday")) {
                    if (workDays.get(i).getShift().equals("night")) {
                        n = i;
                        flag++;
                    } else if (workDays.get(i).getShift().equals("morning")) {
                        m = i;
                        flag++;
                    }
                }
            }
        }
        System.out.println("flag is :"+flag+" m is :"+m+" n is : "+n);
        if (flag==0){
            return new ParentDay("الأحد",Arrays.asList(
                    new WorkDay(1,"","00:00","00:00","دوام صباحى"),
                    new WorkDay(2,"","00:00","00:00","دوام مسائى")
            ));
        }else if(flag==1&&n==-1){
            return new ParentDay("الأحد",Arrays.asList(
                    new WorkDay(1,workDays.get(m).getDay(),workDays.get(m).getFrom(),workDays.get(m).getTo(),"دوام صباحى"),
                    new WorkDay(2,"","00:00","00:00","دوام مسائى")
            ));
        }else if(flag==1&&m==-1){
            return new ParentDay("الأحد",Arrays.asList(
                    new WorkDay(1,"","00:00","00:00","دوام صباحى"),
                    new WorkDay(2,workDays.get(n).getDay(),workDays.get(n).getFrom(),workDays.get(n).getTo(),"دوام مسائى")
            ));
        }else {
            return new ParentDay("الأحد",Arrays.asList(
                    new WorkDay(1,workDays.get(m).getDay(),workDays.get(m).getFrom(),workDays.get(m).getTo(),"دوام صباحى"),
                    new WorkDay(2,workDays.get(n).getDay(),workDays.get(n).getFrom(),workDays.get(n).getTo(),"دوام مسائى")
            ));
        }
    }

    public ParentDay monday(){
        //getWorkDays();
        System.out.println("satarday "+workDays.size());
        //WorkDay(int id, String day, String from, String to, String shift)
        int m=-1,n=-1,flag=0;
        if(workDays!=null) {
            for (int i = 0; i < workDays.size() && flag < 2; i++) {
                System.out.println("day 1 "+workDays.get(i).getDay()+":"+workDays.get(i).getFrom());
                if (workDays.get(i).getDay().equals("monday")) {
                    if (workDays.get(i).getShift().equals("night")) {
                        n = i;
                        flag++;
                    } else if (workDays.get(i).getShift().equals("morning")) {
                        m = i;
                        flag++;
                    }
                }
            }
        }
        System.out.println("flag is :"+flag+" m is :"+m+" n is : "+n);
        if (flag==0){
            return new ParentDay("الإثنين",Arrays.asList(
                    new WorkDay(1,"","00:00","00:00","دوام صباحى"),
                    new WorkDay(2,"","00:00","00:00","دوام مسائى")
            ));
        }else if(flag==1&&n==-1){
            return new ParentDay("الإثنين",Arrays.asList(
                    new WorkDay(1,workDays.get(m).getDay(),workDays.get(m).getFrom(),workDays.get(m).getTo(),"دوام صباحى"),
                    new WorkDay(2,"","00:00","00:00","دوام مسائى")
            ));
        }else if(flag==1&&m==-1){
            return new ParentDay("الإثنين",Arrays.asList(
                    new WorkDay(1,"","00:00","00:00","دوام صباحى"),
                    new WorkDay(2,workDays.get(n).getDay(),workDays.get(n).getFrom(),workDays.get(n).getTo(),"دوام مسائى")
            ));
        }else {
            return new ParentDay("الإثنين",Arrays.asList(
                    new WorkDay(1,workDays.get(m).getDay(),workDays.get(m).getFrom(),workDays.get(m).getTo(),"دوام صباحى"),
                    new WorkDay(2,workDays.get(n).getDay(),workDays.get(n).getFrom(),workDays.get(n).getTo(),"دوام مسائى")
            ));
        }
    }
    public ParentDay tuesday(){
        //getWorkDays();
        System.out.println("satarday "+workDays.size());
        //WorkDay(int id, String day, String from, String to, String shift)
        int m=-1,n=-1,flag=0;
        if(workDays!=null) {
            for (int i = 0; i < workDays.size() && flag < 2; i++) {
                System.out.println("day 1 "+workDays.get(i).getDay()+":"+workDays.get(i).getFrom());
                if (workDays.get(i).getDay().equals("tuesday")) {
                    if (workDays.get(i).getShift().equals("night")) {
                        n = i;
                        flag++;
                    } else if (workDays.get(i).getShift().equals("morning")) {
                        m = i;
                        flag++;
                    }
                }
            }
        }
        System.out.println("flag is :"+flag+" m is :"+m+" n is : "+n);
        if (flag==0){
            return new ParentDay("الثلاثاء",Arrays.asList(
                    new WorkDay(1,"","00:00","00:00","دوام صباحى"),
                    new WorkDay(2,"","00:00","00:00","دوام مسائى")
            ));
        }else if(flag==1&&n==-1){
            return new ParentDay("الثلاثاء",Arrays.asList(
                    new WorkDay(1,workDays.get(m).getDay(),workDays.get(m).getFrom(),workDays.get(m).getTo(),"دوام صباحى"),
                    new WorkDay(2,"","00:00","00:00","دوام مسائى")
            ));
        }else if(flag==1&&m==-1){
            return new ParentDay("الثلاثاء",Arrays.asList(
                    new WorkDay(1,"","00:00","00:00","دوام صباحى"),
                    new WorkDay(2,workDays.get(n).getDay(),workDays.get(n).getFrom(),workDays.get(n).getTo(),"دوام مسائى")
            ));
        }else {
            return new ParentDay("الثلاثاء",Arrays.asList(
                    new WorkDay(1,workDays.get(m).getDay(),workDays.get(m).getFrom(),workDays.get(m).getTo(),"دوام صباحى"),
                    new WorkDay(2,workDays.get(n).getDay(),workDays.get(n).getFrom(),workDays.get(n).getTo(),"دوام مسائى")
            ));
        }
    }

    public ParentDay wednesday(){
        //getWorkDays();
        System.out.println("satarday "+workDays.size());
        //WorkDay(int id, String day, String from, String to, String shift)
        int m=-1,n=-1,flag=0;
        if(workDays!=null) {
            for (int i = 0; i < workDays.size() && flag < 2; i++) {
                System.out.println("day 1 "+workDays.get(i).getDay()+":"+workDays.get(i).getFrom());
                if (workDays.get(i).getDay().equals("wednesday")) {
                    if (workDays.get(i).getShift().equals("night")) {
                        n = i;
                        flag++;
                    } else if (workDays.get(i).getShift().equals("morning")) {
                        m = i;
                        flag++;
                    }
                }
            }
        }
        System.out.println("flag is :"+flag+" m is :"+m+" n is : "+n);
        if (flag==0){
            return new ParentDay("الأربعاء",Arrays.asList(
                    new WorkDay(1,"","00:00","00:00","دوام صباحى"),
                    new WorkDay(2,"","00:00","00:00","دوام مسائى")
            ));
        }else if(flag==1&&n==-1){
            return new ParentDay("الأربعاء",Arrays.asList(
                    new WorkDay(1,workDays.get(m).getDay(),workDays.get(m).getFrom(),workDays.get(m).getTo(),"دوام صباحى"),
                    new WorkDay(2,"","00:00","00:00","دوام مسائى")
            ));
        }else if(flag==1&&m==-1){
            return new ParentDay("الأربعاء",Arrays.asList(
                    new WorkDay(1,"","00:00","00:00","دوام صباحى"),
                    new WorkDay(2,workDays.get(n).getDay(),workDays.get(n).getFrom(),workDays.get(n).getTo(),"دوام مسائى")
            ));
        }else {
            return new ParentDay("الأربعاء",Arrays.asList(
                    new WorkDay(1,workDays.get(m).getDay(),workDays.get(m).getFrom(),workDays.get(m).getTo(),"دوام صباحى"),
                    new WorkDay(2,workDays.get(n).getDay(),workDays.get(n).getFrom(),workDays.get(n).getTo(),"دوام مسائى")
            ));
        }
    }

    public ParentDay thursday(){
        //getWorkDays();
        System.out.println("satarday "+workDays.size());
        //WorkDay(int id, String day, String from, String to, String shift)
        int m=-1,n=-1,flag=0;
        if(workDays!=null) {
            for (int i = 0; i < workDays.size() && flag < 2; i++) {
                System.out.println("day 1 "+workDays.get(i).getDay()+":"+workDays.get(i).getFrom());
                if (workDays.get(i).getDay().equals("thursday")) {
                    if (workDays.get(i).getShift().equals("night")) {
                        n = i;
                        flag++;
                    } else if (workDays.get(i).getShift().equals("morning")) {
                        m = i;
                        flag++;
                    }
                }
            }
        }
        System.out.println("flag is :"+flag+" m is :"+m+" n is : "+n);
        if (flag==0){
            return new ParentDay("الخميس",Arrays.asList(
                    new WorkDay(1,"","00:00","00:00","دوام صباحى"),
                    new WorkDay(2,"","00:00","00:00","دوام مسائى")
            ));
        }else if(flag==1&&n==-1){
            return new ParentDay("الخميس",Arrays.asList(
                    new WorkDay(1,workDays.get(m).getDay(),workDays.get(m).getFrom(),workDays.get(m).getTo(),"دوام صباحى"),
                    new WorkDay(2,"","00:00","00:00","دوام مسائى")
            ));
        }else if(flag==1&&m==-1){
            return new ParentDay("الخميس",Arrays.asList(
                    new WorkDay(1,"","00:00","00:00","دوام صباحى"),
                    new WorkDay(2,workDays.get(n).getDay(),workDays.get(n).getFrom(),workDays.get(n).getTo(),"دوام مسائى")
            ));
        }else {
            return new ParentDay("الخميس",Arrays.asList(
                    new WorkDay(1,workDays.get(m).getDay(),workDays.get(m).getFrom(),workDays.get(m).getTo(),"دوام صباحى"),
                    new WorkDay(2,workDays.get(n).getDay(),workDays.get(n).getFrom(),workDays.get(n).getTo(),"دوام مسائى")
            ));
        }
    }
    public ParentDay friday(){
        //getWorkDays();
        System.out.println("satarday "+workDays.size());
        //WorkDay(int id, String day, String from, String to, String shift)
        int m=-1,n=-1,flag=0;
        if(workDays!=null) {
            for (int i = 0; i < workDays.size() && flag < 2; i++) {
                System.out.println("day 1 "+workDays.get(i).getDay()+":"+workDays.get(i).getFrom());
                if (workDays.get(i).getDay().equals("friday")) {
                    if (workDays.get(i).getShift().equals("night")) {
                        n = i;
                        flag++;
                    } else if (workDays.get(i).getShift().equals("morning")) {
                        m = i;
                        flag++;
                    }
                }
            }
        }
        System.out.println("flag is :"+flag+" m is :"+m+" n is : "+n);
        if (flag==0){
            return new ParentDay("الجمعة",Arrays.asList(
                    new WorkDay(1,"","00:00","00:00","دوام صباحى"),
                    new WorkDay(2,"","00:00","00:00","دوام مسائى")
            ));
        }else if(flag==1&&n==-1){
            return new ParentDay("الجمعة",Arrays.asList(
                    new WorkDay(1,workDays.get(m).getDay(),workDays.get(m).getFrom(),workDays.get(m).getTo(),"دوام صباحى"),
                    new WorkDay(2,"","00:00","00:00","دوام مسائى")
            ));
        }else if(flag==1&&m==-1){
            return new ParentDay("الجمعة",Arrays.asList(
                    new WorkDay(1,"","00:00","00:00","دوام صباحى"),
                    new WorkDay(2,workDays.get(n).getDay(),workDays.get(n).getFrom(),workDays.get(n).getTo(),"دوام مسائى")
            ));
        }else {
            return new ParentDay("الجمعة",Arrays.asList(
                    new WorkDay(1,workDays.get(m).getDay(),workDays.get(m).getFrom(),workDays.get(m).getTo(),"دوام صباحى"),
                    new WorkDay(2,workDays.get(n).getDay(),workDays.get(n).getFrom(),workDays.get(n).getTo(),"دوام مسائى")
            ));
        }
    }
}
