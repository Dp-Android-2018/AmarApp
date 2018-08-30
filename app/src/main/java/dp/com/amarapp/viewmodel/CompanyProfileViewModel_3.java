package dp.com.amarapp.viewmodel;

import android.app.Activity;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
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
import dp.com.amarapp.model.request.UpdateMetaDataRequest;
import dp.com.amarapp.model.response.CompanyLoginResponse;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.utils.CustomUtils;
import dp.com.amarapp.utils.UpdateMetadata;
import dp.com.amarapp.utils.ValidationUtils;
import dp.com.amarapp.view.callback.BaseInterface;
import dp.com.amarapp.view.callback.TaskMonitor;

import static android.app.Activity.RESULT_OK;

public class CompanyProfileViewModel_3 extends BaseObservable implements TaskMonitor{
    private Activity activity;
    private BaseInterface callback;
    private Bitmap selectedImageBitmap;
    private Bitmap picBitmap;
    private UpdateMetaDataRequest request;
    private CompanyLoginResponse company;
    FirebaseStorage storage;
    StorageReference storageReference;
    Uri selectedImageUri = null;

    public CompanyProfileViewModel_3(Activity activity,BaseInterface callback) {
        this.activity = activity;
        this.callback=callback;
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        request=new UpdateMetaDataRequest();
        company=CustomUtils.getInstance().getSaveUserObject(activity);
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
    public String getImage(){
        return company.getMetaData().getLicense_image();
    }
    @Override
    public void taskCompleted(String photoUrl) {
        request.setLicenseImageUrl(photoUrl);
        CustomUtils.getInstance().cancelDialog();
    }
    public void save(View view){
        request.setSpecializationId(1);
        request.setCityId(company.getCity().getId());
        request.setCountryId(company.getCountry().getId());
        UpdateMetadata updat=new UpdateMetadata(request,activity,"Bearer "+company.getToken(),
                ConfigurationFile.FragmentID.FRAGMENT3);
        updat.call();
    }
}
