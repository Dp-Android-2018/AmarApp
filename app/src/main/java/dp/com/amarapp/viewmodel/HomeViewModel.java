package dp.com.amarapp.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.Observable;

import dp.com.amarapp.model.pojo.AdvertContent;
import dp.com.amarapp.model.pojo.CategoriesContent;
import dp.com.amarapp.model.pojo.City;
import dp.com.amarapp.model.pojo.Specialization;
import dp.com.amarapp.model.response.AdvertResponse;
import dp.com.amarapp.model.response.Country;
import dp.com.amarapp.network.ApiClient;
import dp.com.amarapp.network.EndPoints;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.utils.CustomUtils;
import dp.com.amarapp.utils.NetWorkConnection;
import dp.com.amarapp.view.callback.BaseInterface;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import retrofit2.http.PUT;

/**
 * Created by DELL on 22/07/2018.
 */

public class HomeViewModel extends Observable {
    private Context context;
    private BaseInterface callback;
    public Country country;
    public City city;
    public CategoriesContent categoriesContent;
    public Specialization specialization;
    public String sort;
    private boolean specializationFlag;
    private boolean cityFlag;
    public ObservableField<String> countryName;
    public ObservableField<String> cityName;
    public ObservableField<String> categoryName;
    public ObservableField<String> specializationName;
    public ObservableList<AdvertContent> homeAdverts;

    public HomeViewModel(Context context, BaseInterface callback) {
        this.context = context;
        this.callback = callback;
        countryName=new ObservableField<>();
        cityName=new ObservableField<>();
        categoryName=new ObservableField<>();
        homeAdverts=new ObservableArrayList<>();
        specializationName=new ObservableField<>();
        specializationFlag=false;
        cityFlag=false;
        getHomeAdverts();
    }

    public void search(View view){
        callback.updateUi(ConfigurationFile.FragmentID.SEARCH);
    }
    public void getHomeAdverts() {
        if (NetWorkConnection.isConnectingToInternet(context)) {
            CustomUtils.getInstance().showProgressDialog(context);
            ApiClient.getClient().create(EndPoints.class).getAdverts(ConfigurationFile.Constants.API_KEY,
                    ConfigurationFile.Constants.CONTENT_TYPE, ConfigurationFile.Constants.CONTENT_TYPE)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(advertResponseResponse -> {
                        CustomUtils.getInstance().cancelDialog();
                        System.out.println("code home advert" + advertResponseResponse.code());
                        homeAdverts.addAll(advertResponseResponse.body().getAdvertContent());
                    }, throwable -> {
                        CustomUtils.getInstance().cancelDialog();
                        System.out.println("ERROR in home adver :" + throwable.getMessage());
                    });
        } else {
            callback.updateUi(ConfigurationFile.Constants.NO_INTERNET_CONNECTION_CODE);
        }

    }

    public void getCountries(View view){

       callback.updateUi(ConfigurationFile.Constants.MOVE_TO_COUNTRY_ACT);
    }

    public void getCities(View view){
        if(cityFlag) {
            callback.updateUi(ConfigurationFile.Constants.MOVE_TO_CITY_ACT);
        }
        else{
            callback.updateUi(ConfigurationFile.Constants.SELECT_COUNTRY);
        }
    }

    public void getCategories(View view){

        callback.updateUi(ConfigurationFile.Constants.MOVE_TO_CATEGORY_ACT);
    }

    public void getSpecialization(View view)
    {
        if (specializationFlag)
            callback.updateUi(ConfigurationFile.Constants.MOVE_TO_SPECIALIZATION_ACT);
        else
            callback.updateUi(ConfigurationFile.Constants.SELECT_GATEGORY);

    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==ConfigurationFile.IntentConstants.REQUEST_CODE_COUNTRY)
        {
            cityFlag=true;
            country=(Country)data.getSerializableExtra(ConfigurationFile.IntentConstants.COUNTRY_DATA);
            setCountryName();
        }
        else if(requestCode==ConfigurationFile.IntentConstants.REQUEST_CODE_CITY)
        {
            city=(City)data.getSerializableExtra(ConfigurationFile.IntentConstants.CITY_DATA);
            setCityName();
        }
        else if (requestCode==ConfigurationFile.IntentConstants.REQUEST_CODE_CATEGORY)
        {
            specializationFlag=true;
            categoriesContent=(CategoriesContent)data.getSerializableExtra(ConfigurationFile.IntentConstants.CATEGORY_DATA);
            setCategoryName();
        } else if (requestCode==ConfigurationFile.IntentConstants.REQUEST_CODE_SPECIALIZATION)
        {specialization=(Specialization)data.getSerializableExtra(ConfigurationFile.IntentConstants.SPECIALIZATION_DATA);
            setSpecializationName();
        }

    }
    public void setCountryName(){
         countryName.set(country.getName());
    }
    public void setCityName(){
        cityName.set(city.getName());
    }
    public void setCategoryName(){
        categoryName.set(categoriesContent.getName());
    }
    public void setSpecializationName(){
        specializationName.set(specialization.getName());
    }

}
