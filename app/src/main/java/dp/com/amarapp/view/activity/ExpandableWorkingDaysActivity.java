package dp.com.amarapp.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.function.Predicate;

import dp.com.amarapp.R;
import dp.com.amarapp.model.pojo.ParentDay;
import dp.com.amarapp.model.pojo.WorkDay;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.view.adapter.WorkingDayAdapter;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

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


    public List<ParentDay> handleDays(){
        ArrayList<ParentDay>list=new ArrayList<>();
        HashMap<String,List<WorkDay>>tempmap=new HashMap<>();
        for (WorkDay workDay:workDays){
          if (tempmap.containsKey(workDay.getDay())){
              List<WorkDay>day=tempmap.get(workDay.getDay());
              day.add(workDay);
              tempmap.put(workDay.getDay(),day);
          }else {
              List<WorkDay> templists=new ArrayList<>();
              templists.add(workDay);
              tempmap.put(workDay.getDay(),templists);
          }
        }

        for (Map.Entry<String,List<WorkDay>> entry : tempmap.entrySet())
        {
           list.add(new ParentDay(entry.getKey(),entry.getValue()));
        }
        return list;
    }



}
