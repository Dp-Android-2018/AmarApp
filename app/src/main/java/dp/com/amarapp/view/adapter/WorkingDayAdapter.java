package dp.com.amarapp.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

import dp.com.amarapp.R;
import dp.com.amarapp.model.pojo.ParentDay;
import dp.com.amarapp.model.pojo.WorkDay;
import dp.com.amarapp.view.holder.ChildViewHolder;
import dp.com.amarapp.view.holder.ParentViewHolder;

public class WorkingDayAdapter extends ExpandableRecyclerViewAdapter<ParentViewHolder,ChildViewHolder> {

    View parentView;
    public WorkingDayAdapter(List<? extends ExpandableGroup> groups) {
        super(groups);
    }
    @Override
    public ParentViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        parentView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_expandable_parent, parent, false);
        return new ParentViewHolder(parentView);
    }

    @Override
    public ChildViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.day_item, parent, false);
        return new ChildViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(ChildViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        final WorkDay workDay = ((ParentDay) group).getItems().get(childIndex);
        holder.setData(workDay);
    }

    @Override
    public void onBindGroupViewHolder(ParentViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.setDayTitle(group);

    }
}
