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
import android.view.View;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Observable;

import dp.com.amarapp.BR;
import dp.com.amarapp.model.pojo.CategoriesContent;
import dp.com.amarapp.model.pojo.LoginResponseContent;
import dp.com.amarapp.model.pojo.Specialization;
import dp.com.amarapp.model.request.CheckPhoneRequest;
import dp.com.amarapp.model.request.CompanyRegisterRequest;
import dp.com.amarapp.model.response.CompanyRegisterResponse;
import dp.com.amarapp.network.ApiClient;
import dp.com.amarapp.network.EndPoints;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.utils.CustomUtils;
import dp.com.amarapp.utils.NetWorkConnection;
import dp.com.amarapp.utils.SharedPrefrenceUtils;
import dp.com.amarapp.utils.ValidationUtils;
import dp.com.amarapp.view.activity.ActivationActivity;
import dp.com.amarapp.view.activity.ContainerActivity;
import dp.com.amarapp.view.callback.BaseInterface;
import dp.com.amarapp.view.callback.TaskMonitor;
import dp.com.amarapp.view.fragment.CompanyProfileFragment;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

/**
 * Created by DELL on 24/07/2018.
 */

public class CompanyRegisterViewModelStep2 extends BaseObservable implements TaskMonitor {

    public ObservableField<String>phoneNumber;
    public ObservableField<String>webSite;
    public ObservableField<String>type;
    public ObservableField<Integer>spicalization;
    public ObservableField<String>description;
    private Context context;
    private BaseInterface callback;
    private CompanyRegisterRequest companyRegisterRequest;
    private CheckPhoneRequest checkPhoneRequest;
    private Bitmap selectedImageBitmap;
    private Bitmap picBitmap;
    FirebaseStorage storage;
    StorageReference storageReference;
    Uri selectedImageUri = null;
    SharedPrefrenceUtils pref;
    public ObservableField<String> categoryName;
    public ObservableField<String> specializationName;
    private boolean specializationFlag;
    public Specialization specialization;
    public CategoriesContent categoriesContent;
    public CompanyRegisterViewModelStep2(Context context, BaseInterface callback) {
        this.context = context;
        this.callback = callback;
        companyRegisterRequest=(CompanyRegisterRequest) ((Activity)context).getIntent().getSerializableExtra(ConfigurationFile.SharedPrefConstants.PREF_REGISTER_OBJECT);
        initializeVariables();
    }
    public void initializeVariables(){
        phoneNumber=new ObservableField<>();
        webSite=new ObservableField<>();
        type=new ObservableField<>();
        spicalization=new ObservableField<>();
        description=new ObservableField<>();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        categoryName=new ObservableField<>();
        specializationName=new ObservableField<>();
    }

    public boolean checkEmptyData() {
        spicalization.set(1);

        if (ValidationUtils.isEmpty(phoneNumber.get()) ||
                ValidationUtils.isEmpty(webSite.get()) ||
                ValidationUtils.isEmpty(categoryName.get()) ||
                ValidationUtils.isEmpty(specializationName.get()) ||
                ValidationUtils.isEmpty(description.get())) {
            callback.updateUi(ConfigurationFile.Constants.FILL_ALL_DATA_ERROR);
            return false;
        }else {
            if (!ValidationUtils.isPhone(phoneNumber.get())) {
                callback.updateUi(ConfigurationFile.Constants.INVALED_PHONE);
                return false;
            } else {
                companyRegisterRequest.setPhone(phoneNumber.get());
                companyRegisterRequest.setWebsiteUrl(webSite.get());
                companyRegisterRequest.setSpecializationId(specialization.getId());
                companyRegisterRequest.setDescription(description.get());
                checkPhoneRequest = new CheckPhoneRequest(phoneNumber.get());
                checkEsistPhone();
                return true;
            }
        }
        }

    public void uploadImage(View view){
        callback.updateUi(ConfigurationFile.Constants.GETIMAGE);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data){

        if (data!=null) {
            if (requestCode==ConfigurationFile.Constants.PICK_IMAGE_REQUEST) {
                selectedImageUri = data.getData();
                CustomUtils.getInstance().showProgressDialog(context);
                CustomUtils.getInstance().uploadFireBasePic(storageReference, selectedImageUri, this);
                convertImageToBase64(data.getData());

            }else if (requestCode==ConfigurationFile.IntentConstants.REQUEST_CODE_CATEGORY)
            {
                specializationFlag=true;
                categoriesContent=(CategoriesContent)data.getSerializableExtra(ConfigurationFile.IntentConstants.CATEGORY_DATA);
                setCategoryName();
            }
            else if (requestCode==ConfigurationFile.IntentConstants.REQUEST_CODE_SPECIALIZATION)
            {
                specialization=(Specialization)data.getSerializableExtra(ConfigurationFile.IntentConstants.SPECIALIZATION_DATA);
                setSpecializationName();
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
        companyRegisterRequest.setLogoUrl(photoUrl);
        CustomUtils.getInstance().cancelDialog();
    }

    public void registerAction(View view){
            checkEmptyData();
    }
    public void checkEsistPhone(){
        if(NetWorkConnection.isConnectingToInternet(context)){
            CustomUtils.getInstance().showProgressDialog((Activity)context);
            ApiClient.getClient().create(EndPoints.class).checkPhone(ConfigurationFile.Constants.API_KEY,
                    ConfigurationFile.Constants.CONTENT_TYPE,ConfigurationFile.Constants.CONTENT_TYPE,checkPhoneRequest)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(integerResponse -> {
                        CustomUtils.getInstance().cancelDialog();
                        System.out.println("register exist phone code is : "+integerResponse.code());
                        if(integerResponse.code()==ConfigurationFile.Constants.SUCCESS_CODE_second){
                            if(integerResponse.body()==0){
                                register();
                            }else{

                                callback.updateUi(ConfigurationFile.Constants.EXISET_PHONE_CODE);
                            }
                        }

                    }, throwable -> {
                        CustomUtils.getInstance().cancelDialog();
                        System.out.println("register Exiset phone Ex:"+throwable.getMessage());

                    });
        }else{
            callback.updateUi(ConfigurationFile.Constants.NO_INTERNET_CONNECTION_CODE);
        }
    }

    public void register(){
        if(NetWorkConnection.isConnectingToInternet(context)){
            CustomUtils.getInstance().showProgressDialog((Activity)context);
            ApiClient.getClient().create(EndPoints.class).companyRegister(ConfigurationFile.Constants.API_KEY,
                    ConfigurationFile.Constants.CONTENT_TYPE,ConfigurationFile.Constants.CONTENT_TYPE,companyRegisterRequest)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(companyRegisterResponseResponse -> {
                        CustomUtils.getInstance().cancelDialog();
                        System.out.println("register CODE Register 2:"+companyRegisterResponseResponse.code());
                        switch (companyRegisterResponseResponse.code()){
                            case (ConfigurationFile.Constants.SUCCESS_CODE):
                            {
                                saveDataToPrefs(companyRegisterResponseResponse.body().getCompanyRegisterResponseContent());
                                Intent intent=new Intent(context,ActivationActivity.class);
                                intent.putExtra(companyRegisterRequest.getName(),ConfigurationFile.IntentConstants.USER_NAME);
                                (context).startActivity(intent);
                                ((Activity)context).finishAffinity();
                                break;
                            }
                            case (ConfigurationFile.Constants.INVALED_DATA_CODE):
                            {
                                callback.updateUi(ConfigurationFile.Constants.INVALED_DATA_CODE);
                                break;
                            }
                            case (ConfigurationFile.Constants.SERVER_ERROR):
                                callback.updateUi(ConfigurationFile.Constants.SERVER_ERROR);
                                break;
                        }

                    }, throwable -> System.out.println("register error "+throwable.getMessage()));


        }else {
            callback.updateUi(ConfigurationFile.Constants.NO_INTERNET_CONNECTION_CODE);
        }

    }

    public void saveDataToPrefs(LoginResponseContent data){
        pref=new SharedPrefrenceUtils(context);
        pref.saveObjectToSharedPreferences(ConfigurationFile.SharedPrefConstants.PREF_AMAR_CLIENT_OBJ_DATA,data);
    }

    public void getSpecialization(View view) {
        if (specializationFlag)
            callback.updateUi(ConfigurationFile.Constants.MOVE_TO_SPECIALIZATION_ACT);
        else
            callback.updateUi(ConfigurationFile.Constants.SELECT_GATEGORY);
    }

    public void getCategories(View view){

        callback.updateUi(ConfigurationFile.Constants.MOVE_TO_CATEGORY_ACT);
    }


    public void setCategoryName(){
        categoryName.set(categoriesContent.getName());
    }
    public void setSpecializationName(){
        specializationName.set(specialization.getName());
    }
}
