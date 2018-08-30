package dp.com.amarapp.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import dp.com.amarapp.R;
import dp.com.amarapp.databinding.CityListItemBinding;
import dp.com.amarapp.model.pojo.City;
import dp.com.amarapp.view.callback.CityCallback;
import dp.com.amarapp.view.holder.CityViewHolder;

public class CityAdapter extends RecyclerView.Adapter<CityViewHolder> {
    List<City> cities;
    private CityCallback cityCallback;

    public CityAdapter(List<City> cities,CityCallback cityCallback) {
        this.cities = cities;
        this.cityCallback=cityCallback;
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CityListItemBinding binding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.city_list_item,parent,false);
        return new CityViewHolder(binding,cityCallback);
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder holder, int position) {
        holder.bindItemCity(cities.get(position));

    }

    @Override
    public int getItemCount() {
        return cities.size();
    }
}
