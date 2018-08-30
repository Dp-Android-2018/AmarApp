package dp.com.amarapp.viewmodel;

import android.app.Activity;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import dp.com.amarapp.BR;
import dp.com.amarapp.R;
import dp.com.amarapp.model.pojo.Specialization;
import dp.com.amarapp.model.request.UpdateMetaDataRequest;
import dp.com.amarapp.model.response.CompanyLoginResponse;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.utils.CustomUtils;
import dp.com.amarapp.utils.UpdateMetadata;
import dp.com.amarapp.utils.ValidationUtils;
import dp.com.amarapp.view.callback.BaseInterface;
import dp.com.amarapp.view.callback.TaskMonitor;

import static android.app.Activity.RESULT_OK;

public class CompanyProfileViewModel_2 extends BaseObservable implements TaskMonitor{
    private Activity activity;
    private CompanyLoginResponse company;
    public ObservableField<String> website;
    public ObservableField<String> specialization;
    public ObservableField<String> description;
    private UpdateMetaDataRequest request;
    private Bitmap selectedImageBitmap;
    private Bitmap picBitmap;
    FirebaseStorage storage;
    StorageReference storageReference;
    Uri selectedImageUri = null;
    BaseInterface callback;


    public CompanyProfileViewModel_2(Activity activity,BaseInterface callback) {
        this.activity = activity;
        this.callback=callback;
        company=CustomUtils.getInstance().getSaveUserObject(activity);
        initVariables();
    }

    public void initVariables(){
        request=new UpdateMetaDataRequest();
        website=new ObservableField<>();
        if (company.getMetaData()!=null)
        website.set(company.getMetaData().getWebsite()!=null?company.getMetaData().getWebsite():"");
        specialization=new ObservableField<>();
        specialization.set(company.getSpecialization()!=null?company.getSpecialization().getName():"");
        description=new ObservableField<>();
        description.set(company.getDescription()!=null?company.getDescription():"");
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
    }

    public String getPhone(){
        if(company.getPhone()!=null)
        return company.getPhone();
        else
            return "";
    }


    public String getType(){
        if (company.getCategory()!=null)
            return company.getCategory().getName();
        else
            return "";
    }
    public String getImage(){
        System.out.println("LOgo is :"+company.getMetaData().getLogo());
        return company.getMetaData().getLogo();
    }

    public void uploadImage(View view){
        callback.updateUi(ConfigurationFile.Constants.GETIMAGE);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode == RESULT_OK) {
            selectedImageUri = data.getData();
            CustomUtils.getInstance().showProgressDialog(activity);
            CustomUtils.getInstance().uploadFireBasePic(storageReference, selectedImageUri, this);
            convertImageToBase64(data.getData());
        }

    }

    public void save(View view){
        if(!ValidationUtils.isEmpty(website.get())){
            request.setWebsite(website.get());
        }
        if(!ValidationUtils.isEmpty(description.get())){
            request.setDescription(description.get());
        }
        request.setSpecializationId(1);
        System.out.println("cityId ");
        request.setCityId(company.getCity().getId());
        request.setCountryId(company.getCountry().getId());

        UpdateMetadata updat=new UpdateMetadata(request,activity,"Bearer "+company.getToken(),
                ConfigurationFile.FragmentID.FRAGMENT2);
        updat.call();
    }

    public void convertImageToBase64(Uri ImageUri) {
        try {
            final InputStream imageStream = activity.getContentResolver().openInputStream(ImageUri);
            selectedImageBitmap = BitmapFactory.decodeStream(imageStream);
            String RealPicturePath = CustomUtils.getInstance().uriToFilename(ImageUri,activity);
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
        request.setLogoUrl(photoUrl);
        CustomUtils.getInstance().cancelDialog();
    }
}
