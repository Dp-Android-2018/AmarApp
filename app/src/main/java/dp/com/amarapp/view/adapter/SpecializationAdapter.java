package dp.com.amarapp.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import dp.com.amarapp.R;
import dp.com.amarapp.databinding.SpecializationListItemBinding;
import dp.com.amarapp.model.pojo.Specialization;
import dp.com.amarapp.view.callback.SpecializationCallback;
import dp.com.amarapp.view.holder.SpecializationViewHolder;

public class SpecializationAdapter extends RecyclerView.Adapter<SpecializationViewHolder> {
    private List<Specialization>specializationList;
    private SpecializationCallback specializationCallback;

    public SpecializationAdapter(List<Specialization> specializationList, SpecializationCallback specializationCallback) {
        this.specializationList = specializationList;
        this.specializationCallback = specializationCallback;
    }

    @NonNull
    @Override
    public SpecializationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SpecializationListItemBinding binding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.specialization_list_item,parent,false);
        return new SpecializationViewHolder(binding,specializationCallback);
    }

    @Override
    public void onBindViewHolder(@NonNull SpecializationViewHolder holder, int position) {
        holder.bindItemSpecialization(specializationList.get(position));

    }

    @Override
    public int getItemCount() {
        return specializationList.size();
    }
}
