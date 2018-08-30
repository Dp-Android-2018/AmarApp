package dp.com.amarapp.view.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import dp.com.amarapp.R;
import dp.com.amarapp.databinding.ImageListItemBinding;
import dp.com.amarapp.view.holder.AddprojectViewHolder;

public class AddProjectAdapter extends RecyclerView.Adapter<AddprojectViewHolder> {
    ObservableList<String> imageLinks;


    public AddProjectAdapter(ObservableList<String> imageLinks) {
        this.imageLinks = imageLinks;
    }

    @NonNull
    @Override
    public AddprojectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ImageListItemBinding binding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.image_list_item,parent,false);
        return new AddprojectViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AddprojectViewHolder holder, int position) {
        holder.bindImageItem(imageLinks.get(position));

    }

    @Override
    public int getItemCount() {
        return imageLinks.size();
    }
}
