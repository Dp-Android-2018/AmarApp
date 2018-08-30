package dp.com.amarapp.viewmodel;

import android.app.Activity;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.View;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import dp.com.amarapp.BR;
import dp.com.amarapp.model.request.AddProjectRequest;
import dp.com.amarapp.model.request.UpdateMetaDataRequest;
import dp.com.amarapp.model.response.CompanyLoginResponse;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.utils.CustomUtils;
import dp.com.amarapp.utils.UpdateMetadata;
import dp.com.amarapp.utils.ValidationUtils;
import dp.com.amarapp.view.callback.BaseInterface;
import dp.com.amarapp.view.callback.TaskMonitor;

import static android.app.Activity.RESULT_OK;

public class CompanyProfileViewModel_4 extends BaseObservable implements TaskMonitor{
    private Activity activity;
    BaseInterface callback;
    private CompanyLoginResponse company;
    private UpdateMetaDataRequest request;
    FirebaseStorage storage;
    StorageReference storageReference;
    Uri selectedImageUri = null;
    public ObservableList<String> images;
    private Bitmap picBitmap;
    private Bitmap selectedImageBitmap;

    public CompanyProfileViewModel_4(Activity activity,BaseInterface callback) {
        this.activity = activity;
        this.callback=callback;
        company=CustomUtils.getInstance().getSaveUserObject(activity);
        initVariables();
    }
    public void initVariables() {
        images = new ObservableArrayList<>();
        if(company.getMetaData().getImages()!=null)
            images.addAll(company.getMetaData().getImages());
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        request=new UpdateMetaDataRequest();
    }

    public void getImage(View view) {
        callback.updateUi(ConfigurationFile.Constants.GETIMAGE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            selectedImageUri = data.getData();
            CustomUtils.getInstance().showProgressDialog(activity);
            CustomUtils.getInstance().uploadFireBasePic(storageReference, selectedImageUri, this);
            convertImageToBase64(data.getData());
        }
    }

    public void convertImageToBase64(Uri ImageUri) {
        try {
            final InputStream imageStream = activity.getContentResolver().openInputStream(ImageUri);
            selectedImageBitmap = BitmapFactory.decodeStream(imageStream);
            String RealPicturePath = CustomUtils.getInstance().uriToFilename(ImageUri, activity);
            selectedImageBitmap = CustomUtils.getInstance().modifyOrientation(selectedImageBitmap, RealPicturePath);
            setPicBitmap(selectedImageBitmap);
            // encodedImage = CustomUtils.getInstance().encodeImage(selectedImageBitmap);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void taskCompleted(String photoUrl) {
        images.add(photoUrl);
        CustomUtils.getInstance().cancelDialog();
        System.out.println("Size of the links :" + images.size());
    }

    public void setPicBitmap(Bitmap picBitmap) {
        this.picBitmap = picBitmap;
        notifyPropertyChanged(BR.picBitmap);
    }

    @Bindable
    public Bitmap getPicBitmap() {
        return picBitmap;
    }

    public void save(View view){
        request.setSpecializationId(1);
        System.out.println("cityId ");
        request.setCityId(company.getCity().getId());
        request.setCountryId(company.getCountry().getId());
        request.setImgsUrl(images);
        UpdateMetadata updat=new UpdateMetadata(request,activity,"Bearer "+company.getToken(),
                ConfigurationFile.FragmentID.FRAGMENT4);
        updat.call();
    }
}
