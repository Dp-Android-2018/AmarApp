package dp.com.amarapp.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.ArrayList;
import java.util.List;

import dp.com.amarapp.R;
import dp.com.amarapp.model.pojo.BaseObjParentDay;
import dp.com.amarapp.model.pojo.FullTimeWorkDay;
import dp.com.amarapp.view.holder.ParentViewHolder;
import dp.com.amarapp.view.holder.SetChildViewHolder;

public class SetWorkingDaysAdapter extends ExpandableRecyclerViewAdapter<ParentViewHolder,SetChildViewHolder> {

    View parentView;
    //SetChildViewHolder childViewHolder=null;
    ArrayList<SetChildViewHolder>viewHolders=new ArrayList<>();
    public SetWorkingDaysAdapter(List<? extends ExpandableGroup> groups) {
        super(groups);
    }

    @Override
    public ParentViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        parentView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_expandable_parent, parent, false);
        return new ParentViewHolder(parentView);
    }

    @Override
    public SetChildViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.full_day_item, parent, false);
        return new SetChildViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(SetChildViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        FullTimeWorkDay workDay = ((BaseObjParentDay) group).getItems().get(childIndex);
        holder.setDay(group.getTitle());
        System.out.println("child postion 2:"+group.getTitle());
        System.out.println("Work day in child view holder : "+workDay.getnTo()+"/"+workDay.getmTo()+"/"+workDay.getnFrom()+"/"+workDay.getMfrom());
        holder.setData(workDay);
        if(workDay!=null)
            viewHolders.add(holder);
    }

    @Override
    public void onBindGroupViewHolder(ParentViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.setDayTitle(group);

    }

    public ArrayList<SetChildViewHolder> getChildViewHolder() {
        return viewHolders;
    }
}
