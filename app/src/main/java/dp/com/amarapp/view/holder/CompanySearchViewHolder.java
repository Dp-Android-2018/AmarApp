package dp.com.amarapp.view.holder;

import android.support.v7.widget.RecyclerView;

import dp.com.amarapp.databinding.CompanySearchListItemBinding;
import dp.com.amarapp.model.response.CompanyLoginResponse;
import dp.com.amarapp.viewmodel.ItemCompanyViewModel;

public class CompanySearchViewHolder extends RecyclerView.ViewHolder{
    private CompanySearchListItemBinding binding;
    public CompanySearchViewHolder(CompanySearchListItemBinding binding) {
        super(binding.rlParent);
        this.binding=binding;
    }

    public void bindItemCompany(CompanyLoginResponse companyInfo){
        if(binding.getItemCompanyViewModel()==null)
            binding.setItemCompanyViewModel(new ItemCompanyViewModel(companyInfo,itemView.getContext()));
        else
            binding.getItemCompanyViewModel().setCompanyItem(companyInfo);

    }

}
