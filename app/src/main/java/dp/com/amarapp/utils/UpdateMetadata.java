package dp.com.amarapp.utils;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.widget.Toast;

import com.google.gson.Gson;

import dp.com.amarapp.R;
import dp.com.amarapp.model.pojo.City;
import dp.com.amarapp.model.pojo.LoginResponseContent;
import dp.com.amarapp.model.request.UpdateMetaDataRequest;
import dp.com.amarapp.model.response.CompanyLoginResponse;
import dp.com.amarapp.model.response.Country;
import dp.com.amarapp.model.response.DefaultResponse;
import dp.com.amarapp.network.ApiClient;
import dp.com.amarapp.network.EndPoints;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class UpdateMetadata {

    private UpdateMetaDataRequest metaDataRequest;
    private Activity activity;
    private String token;
    private int flag;
    private City city;
    private Country country;
    private SharedPrefrenceUtils pref;
    public UpdateMetadata(UpdateMetaDataRequest metaDataRequest,Activity activity,String token,int flag) {
        this.metaDataRequest = metaDataRequest;
        this.activity=activity;
        this.token=token;
        this.flag=flag;
        System.out.println("toke is :"+token);
    }

    public void call(){
        if(NetWorkConnection.isConnectingToInternet(activity)){
            System.out.println("Meta Data Obj :"+new Gson().toJson(metaDataRequest));
            CustomUtils.getInstance().showProgressDialog(activity);
            ApiClient.getClient().create(EndPoints.class).updateMetadata(ConfigurationFile.Constants.API_KEY,
                    ConfigurationFile.Constants.CONTENT_TYPE,ConfigurationFile.Constants.CONTENT_TYPE,token,metaDataRequest)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(defaultResponseResponse -> {
                        CustomUtils.getInstance().cancelDialog();
                        System.out.println("code is : "+defaultResponseResponse.code());
                        if (defaultResponseResponse.code()==ConfigurationFile.Constants.SUCCESS_CODE_second){

                            switch (flag){
                                case ConfigurationFile.FragmentID.FRAGMENT1 :
                                {
                                    CompanyLoginResponse companyLoginResponse=CustomUtils.getInstance().getSaveUserObject(activity);
                                    companyLoginResponse.setName(metaDataRequest.getName());
                                    companyLoginResponse.setCity(city);
                                    companyLoginResponse.setCountry(country);
                                    System.out.println("City id : "+city.getId());
                                    System.out.println("Country id : "+country.getId());
                                    System.out.println("Company Name 2:"+metaDataRequest.getName());
                                    saveDataToPrefs(companyLoginResponse);
                                    //cityName
                                    //countryName
                                    break;
                                }
                                case ConfigurationFile.FragmentID.FRAGMENT2 :
                                {
                                    CompanyLoginResponse companyLoginResponse=CustomUtils.getInstance().getSaveUserObject(activity);
                                    companyLoginResponse.getMetaData().setWebsite(metaDataRequest.getWebsite());
                                    companyLoginResponse.setDescription(metaDataRequest.getDescription());
                                    companyLoginResponse.getMetaData().setLogo(metaDataRequest.getLogoUrl());
                                    saveDataToPrefs(companyLoginResponse);
                                    break;
                                }
                                case ConfigurationFile.FragmentID.FRAGMENT3 :
                                {
                                    CompanyLoginResponse companyLoginResponse=CustomUtils.getInstance().getSaveUserObject(activity);
                                    companyLoginResponse.getMetaData().setLicense_image(metaDataRequest.getLicenseImageUrl());
                                    saveDataToPrefs(companyLoginResponse);
                                    break;
                                }
                                case ConfigurationFile.FragmentID.FRAGMENT4 :
                                {
                                    CompanyLoginResponse companyLoginResponse=CustomUtils.getInstance().getSaveUserObject(activity);
                                    companyLoginResponse.getMetaData().setImages(metaDataRequest.getImgsUrl());
                                    saveDataToPrefs(companyLoginResponse);
                                    break;
                                }

                                case ConfigurationFile.FragmentID.FRAGMENT5 :
                                {
                                    CompanyLoginResponse companyLoginResponse=CustomUtils.getInstance().getSaveUserObject(activity);
                                    companyLoginResponse.getMetaData().setSocial(metaDataRequest.getSocial());
                                    saveDataToPrefs(companyLoginResponse);
                                    break;
                                }

                            }
                            CustomUtils.getInstance().cancelDialog();
                            System.out.println("Update Metadata message : "+ defaultResponseResponse.body().getMessage());
                            Snackbar.make(activity.findViewById(R.id.ll_parent),defaultResponseResponse.body().getMessage(),Snackbar.LENGTH_LONG).show();

                        }
                    }, throwable -> {
                        CustomUtils.getInstance().cancelDialog();
                        System.out.println("Update Ex :"+throwable);
                        Snackbar.make(activity.findViewById(R.id.ll_parent),throwable.getMessage(),Snackbar.LENGTH_LONG).show();

                    });
        }else{
            Snackbar.make(activity.findViewById(R.id.ll_parent),R.string.no_internet_connection,Snackbar.LENGTH_LONG).show();

        }
    }

    public void saveDataToPrefs(LoginResponseContent data){
        pref=new SharedPrefrenceUtils(activity);
        pref.saveObjectToSharedPreferences(ConfigurationFile.SharedPrefConstants.PREF_AMAR_CLIENT_OBJ_DATA,data);
    }

    public void setCountyCity(Country country,City city){
        System.out.println("countryId on meta data : "+country.getId()+" "+country.getName());
        System.out.println("cityId on meta data : "+city.getId()+" "+city.getName());
        this.country=country;
        this.city=city;
    }
}
