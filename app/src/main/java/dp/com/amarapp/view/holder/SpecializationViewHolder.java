package dp.com.amarapp.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import dp.com.amarapp.databinding.SpecializationListItemBinding;
import dp.com.amarapp.model.pojo.Specialization;
import dp.com.amarapp.view.callback.SpecializationCallback;
import dp.com.amarapp.viewmodel.ItemSpecializationViewModel;

public class SpecializationViewHolder extends RecyclerView.ViewHolder {

    SpecializationListItemBinding binding;
    SpecializationCallback specializationCallback;

    public SpecializationViewHolder(SpecializationListItemBinding binding, SpecializationCallback specializationCallback) {
        super(binding.rlParent);
        this.binding = binding;
        this.specializationCallback = specializationCallback;
    }

    public void bindItemSpecialization(Specialization specialization){
        if(binding.getItemSpecializationViewModel()==null)
            binding.setItemSpecializationViewModel(new ItemSpecializationViewModel(specialization,specializationCallback));
        else
            binding.getItemSpecializationViewModel().setSpecialization(specialization);
    }
}
