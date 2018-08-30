package dp.com.amarapp.view.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import dp.com.amarapp.R;
import dp.com.amarapp.databinding.AdvertListItemBinding;
import dp.com.amarapp.model.pojo.AdvertContent;
import dp.com.amarapp.view.holder.AdvertViewHolder;

public class HomeAdvertAdabter extends RecyclerView.Adapter<AdvertViewHolder> {
    private List<AdvertContent> homeAdverts;

    public HomeAdvertAdabter(List<AdvertContent> homeAdverts) {
        this.homeAdverts=homeAdverts;
    }

    @NonNull
    @Override
    public AdvertViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdvertListItemBinding binding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.advert_list_item,parent,false);
        return new AdvertViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdvertViewHolder holder, int position) {
        holder.bindItemAdvert(homeAdverts.get(position));

    }

    @Override
    public int getItemCount() {
        return homeAdverts.size();
    }
}
