package dp.com.amarapp.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import dp.com.amarapp.model.pojo.City;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.view.callback.CityCallback;

public class ItemCityViewModel  {
    private Context context;
    private City city;
    private CityCallback cityCallback;

    public ItemCityViewModel(Context context, City city,CityCallback cityCallback) {
        this.context = context;
        this.city = city;
        this.cityCallback=cityCallback;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getName(){
        return city.getName();
    }

    public void onItemClick(View v){
        cityCallback.getCities(city);
    }
}
