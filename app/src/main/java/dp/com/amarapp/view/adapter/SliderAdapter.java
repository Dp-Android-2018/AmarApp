package dp.com.amarapp.view.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import dp.com.amarapp.R;
import dp.com.amarapp.databinding.SliderImageItemBinding;
import dp.com.amarapp.view.holder.SliderViewHolder;

public class SliderAdapter extends RecyclerView.Adapter<SliderViewHolder> {

    ObservableList<String> imageLinks;

    public SliderAdapter(ObservableList<String> imageLinks) {
        this.imageLinks = imageLinks;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SliderImageItemBinding binding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.slider_image_item,parent,false);
        return new SliderViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        holder.bindImageItem(imageLinks.get(position));
    }

    @Override
    public int getItemCount() {
        return imageLinks.size();
    }
}
