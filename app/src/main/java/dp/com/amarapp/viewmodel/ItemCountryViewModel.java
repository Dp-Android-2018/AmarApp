package dp.com.amarapp.viewmodel;

import android.content.Context;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import dp.com.amarapp.Application.MyApplication;
import dp.com.amarapp.model.pojo.City;
import dp.com.amarapp.model.response.Country;
import dp.com.amarapp.view.callback.CountryCallback;

public class ItemCountryViewModel {
    private Country counrty;
    private Context context;
    private List<City> cities;
    private CountryCallback countryCallback;
    public ItemCountryViewModel(Country counrty, Context context, CountryCallback baseInterface) {
        this.counrty = counrty;
        this.context = context;
        this.countryCallback=baseInterface;
        cities=new ArrayList<>();
        cities.addAll(counrty.getCities());
    }

    public void setCountryItem(Country country){
        this.counrty=country;
    }

    public String getName(){
        return counrty.getName();
    }

    public void onItemClick(View v){
        countryCallback.getCountries(counrty);
        MyApplication.setCities(cities);
        System.out.println("size of the cities list : "+cities.size());
    }
}
