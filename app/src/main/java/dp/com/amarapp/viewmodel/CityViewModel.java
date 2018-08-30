package dp.com.amarapp.viewmodel;

import android.app.Activity;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import dp.com.amarapp.Application.MyApplication;
import dp.com.amarapp.model.pojo.City;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.view.callback.BaseInterface;

public class CityViewModel  {

    Context context;
    private BaseInterface callback;

    public CityViewModel(Context context,BaseInterface callback) {
        this.context = context;
        this.callback=callback;
    }
}
