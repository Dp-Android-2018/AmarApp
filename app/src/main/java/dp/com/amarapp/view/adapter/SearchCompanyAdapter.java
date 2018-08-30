package dp.com.amarapp.view.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Observable;

import dp.com.amarapp.R;
import dp.com.amarapp.databinding.CompanySearchListItemBinding;
import dp.com.amarapp.model.response.CompanyLoginResponse;
import dp.com.amarapp.view.holder.CompanySearchViewHolder;

public class SearchCompanyAdapter extends RecyclerView.Adapter<CompanySearchViewHolder> {

    private ObservableList<CompanyLoginResponse> response;

    public SearchCompanyAdapter(ObservableList<CompanyLoginResponse> response) {
        this.response = response;
        //System.out.println("Responses Size Adapter ::"+response.size());
    }

    @NonNull
    @Override
    public CompanySearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CompanySearchListItemBinding binding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.company_search_list_item,parent,false);
        return new CompanySearchViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CompanySearchViewHolder holder, int position) {
        if (response.get(position).getName()!=null)
        //System.out.println("Responses Size  Binding :"+response.get(position).getName());
        holder.bindItemCompany(response.get(position));
    }

    @Override
    public int getItemCount() {
       // System.out.println("Responses Size Adapter 2");
        return response.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
