package dp.com.amarapp.view.holder;

import android.support.v7.widget.RecyclerView;


import dp.com.amarapp.databinding.CityListItemBinding;
import dp.com.amarapp.model.pojo.City;
import dp.com.amarapp.view.callback.CityCallback;
import dp.com.amarapp.viewmodel.ItemCityViewModel;

public class CityViewHolder extends RecyclerView.ViewHolder {

    private CityListItemBinding binding;
    private CityCallback cityCallback;

    public CityViewHolder(CityListItemBinding binding,CityCallback cityCallback) {
        super(binding.rlParent);
        this.binding = binding;
        this.cityCallback=cityCallback;
    }

    public void bindItemCity(City city){
        if(binding.getItemCityViewModel()==null)
            binding.setItemCityViewModel(new ItemCityViewModel(itemView.getContext(),city,cityCallback));
        else
            binding.getItemCityViewModel().setCity(city);
    }
}
