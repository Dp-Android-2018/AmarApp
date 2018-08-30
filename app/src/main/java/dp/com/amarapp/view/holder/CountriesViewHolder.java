package dp.com.amarapp.view.holder;

import android.support.v7.widget.RecyclerView;

import dp.com.amarapp.databinding.CountryListItemBinding;
import dp.com.amarapp.model.response.Country;
import dp.com.amarapp.view.callback.CountryCallback;
import dp.com.amarapp.viewmodel.ItemCountryViewModel;

public class CountriesViewHolder extends RecyclerView.ViewHolder {


    private CountryListItemBinding binding;
    private CountryCallback countryCallback;
    public CountriesViewHolder(CountryListItemBinding binding, CountryCallback baseInterface) {
        super(binding.rlParent);
        this.binding=binding;
        this.countryCallback =baseInterface;
    }

    public void bindItemCountry(Country country){
        if(binding.getItemCountryViewModel()==null)
            binding.setItemCountryViewModel(new ItemCountryViewModel(country,(itemView.getContext()), countryCallback));
        else
            binding.getItemCountryViewModel().setCountryItem(country);
    }


}
