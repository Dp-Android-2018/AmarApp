package dp.com.amarapp.view.holder;

import android.support.v7.widget.RecyclerView;

import dp.com.amarapp.databinding.ImageListItemBinding;
import dp.com.amarapp.viewmodel.ImageListItemViewModel;

public class AddprojectViewHolder extends RecyclerView.ViewHolder {
    ImageListItemBinding binding;

    public AddprojectViewHolder(ImageListItemBinding imageListItemBinding) {
        super(imageListItemBinding.rlParent);
        this.binding = imageListItemBinding;
    }

    public void bindImageItem(String link){
        if(binding.getImageListItemViewModel()==null)
            binding.setImageListItemViewModel(new ImageListItemViewModel(itemView.getContext(),link));
        else
            binding.getImageListItemViewModel().setImageLink(link);

    }
}
