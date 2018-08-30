package dp.com.amarapp.view.adapter;


import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import dp.com.amarapp.R;
import dp.com.amarapp.databinding.AdvertListItemBinding;
import dp.com.amarapp.databinding.CompanySearchListItemBinding;
import dp.com.amarapp.model.pojo.AdvertContent;
import dp.com.amarapp.view.holder.AdvertViewHolder;
import dp.com.amarapp.view.holder.CompanySearchViewHolder;

public class AdvertsAdapter extends RecyclerView.Adapter<AdvertViewHolder> {
    private List<AdvertContent> adverts;

    public AdvertsAdapter(List<AdvertContent> adverts) {
        this.adverts = adverts;
    }

    @NonNull
    @Override
    public AdvertViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdvertListItemBinding binding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.advert_list_item,parent,false);
        return new AdvertViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdvertViewHolder holder, int position) {
        if (adverts.get(position).getTitle()!=null)
            //System.out.println("Responses Size  Binding :"+response.get(position).getName());
            holder.bindItemAdvert(adverts.get(position));

    }

    @Override
    public int getItemCount() {
        return adverts.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
