package dp.com.amarapp.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

import dp.com.amarapp.R;
import dp.com.amarapp.model.pojo.ParentDay;
import dp.com.amarapp.model.pojo.WorkDay;
import dp.com.amarapp.view.adapter.WorkingDayAdapter;

public class ExoandableWorkingDaysActivity extends AppCompatActivity {
    WorkingDayAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_workdays_layout);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        RecyclerView.ItemAnimator animator = recyclerView.getItemAnimator();
        if (animator instanceof DefaultItemAnimator) {
            ((DefaultItemAnimator) animator).setSupportsChangeAnimations(false);
        }

        adapter = new WorkingDayAdapter(handleDays());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
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
        list.add(fIRSTDay());
        list.add(secondDay());
        return list;

    }

    public ParentDay fIRSTDay(){
        //WorkDay(int id, String day, String from, String to, String shift)
        return new ParentDay("Sunday",Arrays.asList(
                new WorkDay(1,"Sunday","9:00","12:00","morning"),
                new WorkDay(2,"Sunday","5:00","8:00","night")
        ));
    }


    public ParentDay secondDay(){
        //WorkDay(int id, String day, String from, String to, String shift)
        return new ParentDay("Thursday",Arrays.asList(
                new WorkDay(3,"Thursday","9:00","12:00","morning"),
                new WorkDay(4,"Thursday","5:00","8:00","night")
        ));
    }



}
