package dp.com.amarapp.view.holder;

import android.support.v7.widget.RecyclerView;

import dp.com.amarapp.databinding.SliderImageItemBinding;
import dp.com.amarapp.viewmodel.ImageListItemSliderViewModel;

public class SliderViewHolder extends RecyclerView.ViewHolder {
    SliderImageItemBinding binding;

    public SliderViewHolder(SliderImageItemBinding binding) {
        super(binding.rlParent);
        this.binding = binding;
    }
    public void bindImageItem(String link){
        if(binding.getImageListItemSliderViewModel()==null)
            binding.setImageListItemSliderViewModel(new ImageListItemSliderViewModel(itemView.getContext(),link));
        else
            binding.getImageListItemSliderViewModel().setImageLink(link);
    }
}
