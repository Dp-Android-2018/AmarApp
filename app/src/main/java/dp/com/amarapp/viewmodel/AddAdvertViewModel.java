package dp.com.amarapp.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import dp.com.amarapp.BR;
import dp.com.amarapp.R;
import dp.com.amarapp.model.pojo.City;
import dp.com.amarapp.model.request.CreateAdvertRequest;
import dp.com.amarapp.model.response.Country;
import dp.com.amarapp.network.ApiClient;
import dp.com.amarapp.network.EndPoints;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.utils.CustomUtils;
import dp.com.amarapp.utils.NetWorkConnection;
import dp.com.amarapp.utils.ValidationUtils;
import dp.com.amarapp.view.callback.BaseInterface;
import dp.com.amarapp.view.callback.TaskMonitor;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AddAdvertViewModel extends BaseObservable implements TaskMonitor{
    private Context context;
    private BaseInterface callback;
    private CreateAdvertRequest request;
    private String token="Bearer ";
    public ObservableField<String> address;
    public ObservableField<String> description;
    public ObservableField<String> countryName;
    public ObservableField<String> cityName;
    Country country;
    City city;
    public ObservableField<String>duration;
    private Bitmap selectedImageBitmap;
    private Bitmap picBitmap;
    FirebaseStorage storage;
    boolean cityFlag;
    StorageReference storageReference;
    Uri selectedImageUri = null;

    public AddAdvertViewModel(Context context, BaseInterface callback) {
        this.context = context;
        this.callback = callback;
        initVariables();
    }

    public void initVariables(){
        duration=new ObservableField<>();
        address=new ObservableField<>();
        description=new ObservableField<>();
        countryName =new ObservableField<>();
        cityName =new ObservableField<>();
        token+=CustomUtils.getInstance().getSaveUserObject(context).getToken();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        request=new CreateAdvertRequest();
        cityFlag=false;
    }

    public void publishAction(View view){
        System.out.println("token is in advert  : "+token );
        checkEmptyData();
    }
    public void checkEmptyData() {
        if (ValidationUtils.isEmpty(address.get()) ||
                ValidationUtils.isEmpty(description.get()) ||
                ValidationUtils.isEmpty(duration.get())||
                country.getId() <= 0 || city.getId() <=0) {
            callback.updateUi(ConfigurationFile.Constants.FILL_ALL_DATA_ERROR);
            return;
        }else{
            request.setTitle(address.get());
            request.setContent(description.get());
            request.setCityId(city.getId());
        }
            publish();
    }

    public void publish(){
        if(NetWorkConnection.isConnectingToInternet(context)){
            CustomUtils.getInstance().showProgressDialog((Activity)context);
            ApiClient.getClient().create(EndPoints.class).createAdvert(ConfigurationFile.Constants.API_KEY,
                    ConfigurationFile.Constants.CONTENT_TYPE,ConfigurationFile.Constants.CONTENT_TYPE,token,request)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(advertResponseResponse -> {
                        CustomUtils.getInstance().cancelDialog();
                        System.out.println("code advert is : "+advertResponseResponse.code());
                        System.out.println("code message "+advertResponseResponse.message());
                        System.out.println("token is :"+token);
                        if(advertResponseResponse.code()==ConfigurationFile.Constants.SUCCESS_CODE){
                            callback.updateUi(ConfigurationFile.Constants.SUCCESS_CODE);
                        }else if(advertResponseResponse.code()==ConfigurationFile.Constants.SERVER_ERROR){
                            callback.updateUi(ConfigurationFile.Constants.SERVER_ERROR);
                        }else{
                            callback.updateUi(ConfigurationFile.Constants.INVALED_DATA_CODE);
                        }

                    }, throwable -> {
                        CustomUtils.getInstance().cancelDialog();
                        System.out.println("ERROR adver : "+throwable.getMessage());
                    });
        }else {
            callback.updateUi(ConfigurationFile.Constants.NO_INTERNET_CONNECTION_CODE);
        }
    }

    public void uploadImage(View view){
        callback.updateUi(ConfigurationFile.Constants.GETIMAGE);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (data!=null) {
            if (requestCode == ConfigurationFile.Constants.PICK_IMAGE_REQUEST) {
                selectedImageUri = data.getData();
                CustomUtils.getInstance().showProgressDialog(context);
                CustomUtils.getInstance().uploadFireBasePic(storageReference, selectedImageUri, this);
                convertImageToBase64(data.getData());
            } else if (requestCode == ConfigurationFile.IntentConstants.REQUEST_CODE_COUNTRY) {
                country = (Country) data.getSerializableExtra(ConfigurationFile.IntentConstants.COUNTRY_DATA);
                setCountryName();
            } else if (requestCode == ConfigurationFile.IntentConstants.REQUEST_CODE_CITY) {
                city = (City) data.getSerializableExtra(ConfigurationFile.IntentConstants.CITY_DATA);
                setCityName();
            }
        }
    }

    public void convertImageToBase64(Uri ImageUri) {
        try {
            final InputStream imageStream = context.getContentResolver().openInputStream(ImageUri);
            selectedImageBitmap = BitmapFactory.decodeStream(imageStream);
            String RealPicturePath = CustomUtils.getInstance().uriToFilename(ImageUri,context);
            selectedImageBitmap = CustomUtils.getInstance().modifyOrientation(selectedImageBitmap, RealPicturePath);
            setPicBitmap(selectedImageBitmap);
            // encodedImage = CustomUtils.getInstance().encodeImage(selectedImageBitmap);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void setPicBitmap(Bitmap picBitmap) {
        this.picBitmap = picBitmap;
        notifyPropertyChanged(BR.picBitmap);
    }

    @Bindable
    public Bitmap getPicBitmap() {
        return picBitmap;
    }
    @Override
    public void taskCompleted(String photoUrl) {
        request.setImage_link(photoUrl);
        CustomUtils.getInstance().cancelDialog();
    }
    public void getCountries(View view){
        cityFlag=true;
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

    public void setCountryName(){
        countryName.set(country.getName());
    }
    public void setCityName(){
        cityName.set(city.getName());
    }

    public void dialog(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View alertLayout = View.inflate(context, R.layout.duration_layout, null);
        builder.setView(alertLayout);
        builder.setCancelable(true);
        AlertDialog dialog=builder.create();
        alertLayout.findViewById(R.id.tv_day).setOnClickListener(v -> {
            duration.set("يوم");
            request.setDuration("DAY");
            dialog.dismiss();
            System.out.println("Duration is : "+request.getDuration());
        });
        alertLayout.findViewById(R.id.tv_week).setOnClickListener(v -> {
            duration.set("أسبوع");
            request.setDuration("WEEK");
            dialog.dismiss();
            System.out.println("Duration is : "+request.getDuration());
        });
        alertLayout.findViewById(R.id.tv_month).setOnClickListener(v -> {
            duration.set("شهر");
            request.setDuration("MONTH");
            dialog.dismiss();
            System.out.println("Duration is : "+request.getDuration());
        });
        alertLayout.findViewById(R.id.tv_six_months).setOnClickListener(v -> {
            duration.set("ستة أشهر");
            request.setDuration("SIXMONTHES");
            dialog.dismiss();
            System.out.println("Duration is : "+request.getDuration());
        });
        alertLayout.findViewById(R.id.tv_year).setOnClickListener(v -> {
            duration.set("سنة");
            request.setDuration("YEAR");
            dialog.dismiss();
            System.out.println("Duration is : "+request.getDuration());
        });
        dialog.show();

    }
}
