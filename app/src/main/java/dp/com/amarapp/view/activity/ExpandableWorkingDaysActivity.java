package dp.com.amarapp.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dp.com.amarapp.R;
import dp.com.amarapp.model.pojo.ParentDay;
import dp.com.amarapp.model.pojo.WorkDay;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.view.adapter.WorkingDayAdapter;

public class ExpandableWorkingDaysActivity extends BaseActivity {
    WorkingDayAdapter adapter;
    List<WorkDay> workDays;
    Toolbar toolbar;
    public ExpandableWorkingDaysActivity() {
        workDays=new ArrayList<>();
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        workDays=(List<WorkDay>)getIntent().getSerializableExtra(ConfigurationFile.IntentConstants.COMPANY_WORK_DAYS);

        // getWorkDays();
        setContentView(R.layout.activity_expandable_workdays_layout);
        toolbar=findViewById(R.id.toolbar);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        TextView textView=findViewById(R.id.text);
        ImageView imageView=findViewById(R.id.image);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.ItemAnimator animator = recyclerView.getItemAnimator();
        if (animator instanceof DefaultItemAnimator) {
            ((DefaultItemAnimator) animator).setSupportsChangeAnimations(false);
        }
        setUpActionBar();
        if(handleDays().size()>0) {
            adapter = new WorkingDayAdapter(handleDays());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }else{
            recyclerView.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.VISIBLE);
        }
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
