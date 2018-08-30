package dp.com.amarapp.viewmodel;

import android.view.View;

import dp.com.amarapp.model.pojo.Specialization;
import dp.com.amarapp.view.callback.SpecializationCallback;

public class ItemSpecializationViewModel {
    private Specialization specialization;
    private SpecializationCallback specializationCallback;

    public ItemSpecializationViewModel(Specialization specialization,SpecializationCallback specializationCallback) {
        this.specialization = specialization;
        this.specializationCallback=specializationCallback;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public String getName(){
        return specialization.getName();
    }

    public void onItemClick(View v){
        specializationCallback.getSpecialization(specialization);
    }
}
