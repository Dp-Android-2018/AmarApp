package dp.com.amarapp.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableInt;
import android.view.View;

import dp.com.amarapp.utils.ConfigurationFile;

public class ToolbarViewModel extends BaseObservable {

    public ObservableInt imageVisibility;
    public ObservableInt backimageVisibility=new ObservableInt(View.GONE);;


    private Context context;
    public ToolbarViewModel(Context context, int checker) {
        this.context = context;
        imageVisibility = new ObservableInt(View.GONE);
        switch (checker) {
            case ConfigurationFile.Constants.BACK_IMAGE_VISIBILITY_CODE: {
                backimageVisibility.set(View.VISIBLE);
                imageVisibility.set(View.GONE);
                break;
            }
            case ConfigurationFile.Constants.BACK_IMAGE_UNVISIBILITY_CODE: {
                backimageVisibility.set(View.GONE);
                break;
            }
        }
    }


    public void handleBackAction(View view){
        ((Activity)context).finish();
    }






}

