package dp.com.amarapp.viewmodel;

import android.content.Context;
import android.view.View;

import dp.com.amarapp.R;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.view.callback.BaseInterface;

public class BottomBarViewModel{
    private Context context;
    private BaseInterface callback;
    public BottomBarViewModel(Context context,BaseInterface callback){
        this.context = context;
        this.callback=callback;
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.add_advert:
            {
                callback.updateUi(ConfigurationFile.FragmentID.ADDADVERT);
                break;
            }
            case R.id.adverts:
            {
                callback.updateUi(ConfigurationFile.FragmentID.ADVERTS);
                break;
            }
            case R.id.search:
            {
                callback.updateUi(ConfigurationFile.FragmentID.HOME);
                break;
            }
            case R.id.iv_companies:
            {
                callback.updateUi(ConfigurationFile.FragmentID.COMPANIES);
                break;
            }
            case R.id.iv_settings:
            {
                callback.updateUi(ConfigurationFile.FragmentID.SETTINGS);
                break;
            }

        }
    }

}
