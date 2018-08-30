package dp.com.amarapp.viewmodel;

import android.content.Context;
import android.view.View;

import java.util.Observable;

import dp.com.amarapp.model.request.LoginRequest;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.view.callback.BaseInterface;

/**
 * Created by DELL on 25/07/2018.
 */

public class MemberShipViewModel extends Observable {

    private Context context;
    private BaseInterface callback;

    public MemberShipViewModel(Context context, BaseInterface callback) {
        this.context = context;
        this.callback = callback;
    }


    public void client(View view){
        callback.updateUi(ConfigurationFile.Constants.CLIENT_REGISTRATION);

    }
    public void company(View view){
        callback.updateUi(ConfigurationFile.Constants.COMPANY_REGISTRATION);

    }
    public void home(View view){
        callback.updateUi(ConfigurationFile.Constants.MOVE_TO_HOME_ACTIVITY);

    }

}
