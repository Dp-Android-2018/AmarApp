package dp.com.amarapp.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import dp.com.amarapp.databinding.AdvertListItemBinding;
import dp.com.amarapp.model.pojo.AdvertContent;
import dp.com.amarapp.viewmodel.ItemAdvertViewModel;
import dp.com.amarapp.viewmodel.ItemCompanyViewModel;

public class AdvertViewHolder extends RecyclerView.ViewHolder {
    private AdvertListItemBinding binding;

    public AdvertViewHolder(AdvertListItemBinding binding) {
        super(binding.rlParent);
        this.binding = binding;
    }

    public void bindItemAdvert(AdvertContent advert){
            if(binding.getAdvertItemViewModel()==null)
                binding.setAdvertItemViewModel(new ItemAdvertViewModel(itemView.getContext(),advert));
            else
                binding.getAdvertItemViewModel().setAdvert(advert);

    }
}
