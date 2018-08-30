package dp.com.amarapp.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import dp.com.amarapp.utils.ConfigurationFile;

public class ToolbarViewModel extends BaseObservable {

    public ObservableInt imageVisibility;
    public ObservableInt backimageVisibility=new ObservableInt(View.GONE);;


    private Context context;
    public ToolbarViewModel(Context context, int checker) {
        this.context=context;
        imageVisibility=new ObservableInt(View.GONE);
        if (checker==ConfigurationFile.Constants.BACK_IMAGE_VISIBILITY_CODE)
                backimageVisibility.set(View.VISIBLE);
        else if (checker==ConfigurationFile.Constants.BACK_IMAGE_UNVISIBILITY_CODE)
            backimageVisibility.set(View.GONE);
    }


    public void handleBackAction(View view){
        ((Activity)context).finish();
    }






}

