package dp.com.amarapp.viewmodel;

import android.app.Activity;
import android.content.Context;
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
import dp.com.amarapp.BR;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import dp.com.amarapp.model.request.AddProjectRequest;
import dp.com.amarapp.model.response.DefaultResponse;
import dp.com.amarapp.network.ApiClient;
import dp.com.amarapp.network.EndPoints;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.utils.CustomUtils;
import dp.com.amarapp.utils.NetWorkConnection;
import dp.com.amarapp.view.callback.BaseInterface;
import dp.com.amarapp.view.callback.TaskMonitor;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class AddProjectViewModel extends BaseObservable implements TaskMonitor {
    private Context context;
    BaseInterface callback;
    private String token = "Bearer ";
    AddProjectRequest request;
    FirebaseStorage storage;
    StorageReference storageReference;
    Uri selectedImageUri = null;
    public ObservableField<String> title;
    public ObservableField<String> description;
    public ObservableList<String> images;
    private Bitmap picBitmap;
    private Bitmap selectedImageBitmap;

    public AddProjectViewModel(Context context, BaseInterface callback) {
        this.context = context;
        this.callback = callback;
        initVariables();
    }

    public void initVariables() {
        token += CustomUtils.getInstance().getSaveUserObject(context).getToken();
        images = new ObservableArrayList<>();
        description = new ObservableField<>();
        title = new ObservableField<>();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
    }


    public void getImage(View view) {
        callback.updateUi(ConfigurationFile.Constants.GETIMAGE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            selectedImageUri = data.getData();
            CustomUtils.getInstance().showProgressDialog(context);
            CustomUtils.getInstance().uploadFireBasePic(storageReference, selectedImageUri, this);
            convertImageToBase64(data.getData());
        }
    }


    public void convertImageToBase64(Uri ImageUri) {
        try {
            final InputStream imageStream = context.getContentResolver().openInputStream(ImageUri);
            selectedImageBitmap = BitmapFactory.decodeStream(imageStream);
            String RealPicturePath = CustomUtils.getInstance().uriToFilename(ImageUri, context);
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

    public void addProject() {
        if (NetWorkConnection.isConnectingToInternet(context)) {
            CustomUtils.getInstance().showProgressDialog(context);
            ApiClient.getClient().create(EndPoints.class).addProject(ConfigurationFile.Constants.API_KEY,
                    ConfigurationFile.Constants.CONTENT_TYPE, ConfigurationFile.Constants.CONTENT_TYPE, token, request)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Response<DefaultResponse>>() {
                        @Override
                        public void accept(Response<DefaultResponse> defaultResponseResponse) throws Exception {
                            CustomUtils.getInstance().cancelDialog();
                            System.out.println("Code is :" + defaultResponseResponse.code());
                            System.out.println("Token is : " + token);
                            if (defaultResponseResponse.code() == ConfigurationFile.Constants.SUCCESS_CODE) {
                                callback.updateUi(ConfigurationFile.Constants.SUCCESS_CODE);
                            }

                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            CustomUtils.getInstance().cancelDialog();
                            System.out.println("ERROR is : " + throwable.getMessage());
                        }
                    });

        } else {
            callback.updateUi(ConfigurationFile.Constants.NO_INTERNET_CONNECTION_CODE);
        }

    }

    public void save(View view) {
        request = new AddProjectRequest(title.get(), description.get(), images);
        addProject();
        callback.updateUi(ConfigurationFile.Constants.FINISH_ADD_PROJECT_ACTIVITY);
    }
}
