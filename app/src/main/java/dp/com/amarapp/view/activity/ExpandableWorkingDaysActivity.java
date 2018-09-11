package dp.com.amarapp.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dp.com.amarapp.R;
import dp.com.amarapp.model.pojo.ParentDay;
import dp.com.amarapp.model.pojo.WorkDay;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.view.adapter.WorkingDayAdapter;

public class ExpandableWorkingDaysActivity extends BaseActivity {
    WorkingDayAdapter adapter;
    //private int id;
    //private String token="Bearer ";
    List<WorkDay> workDays;
    Toolbar toolbar;
    public ExpandableWorkingDaysActivity() {
        workDays=new ArrayList<>();
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // token+= CustomUtils.getInstance().getSaveUserObject(ExpandableWorkingDaysActivity.this).getToken();
        workDays=(List<WorkDay>)getIntent().getSerializableExtra(ConfigurationFile.IntentConstants.COMPANY_WORK_DAYS);

        // getWorkDays();
        setContentView(R.layout.activity_expandable_workdays_layout);
        toolbar=findViewById(R.id.toolbar);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.ItemAnimator animator = recyclerView.getItemAnimator();
        if (animator instanceof DefaultItemAnimator) {
            ((DefaultItemAnimator) animator).setSupportsChangeAnimations(false);
        }
        setUpActionBar();
        adapter = new WorkingDayAdapter(handleDays());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
    public void setUpActionBar(){
        setSupportActionBar(toolbar);
        toolbar.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        toolbar.findViewById(R.id.back_arrow).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        adapter.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        adapter.onRestoreInstanceState(savedInstanceState);
    }


    public ArrayList<ParentDay> handleDays(){
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
        return new ParentDay("السبت",Arrays.asList(
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


//    public ParentDay secondDay(){
//        //WorkDay(int id, String day, String from, String to, String shift)
//        return new ParentDay("Thursday",Arrays.asList(
//                new WorkDay(3,"Thursday","9:00","12:00","morning"),
//                new WorkDay(4,"Thursday","5:00","8:00","night")
//        ));
//    }

}
