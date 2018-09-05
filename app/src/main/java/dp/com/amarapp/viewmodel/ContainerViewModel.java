package dp.com.amarapp.viewmodel;

import android.content.Context;
import android.support.design.widget.NavigationView;

import dp.com.amarapp.R;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.utils.CustomUtils;
import dp.com.amarapp.view.callback.BaseInterface;

public class ContainerViewModel {
    private Context context;
    private BaseInterface callback;
    public ContainerViewModel(Context context, BaseInterface callback) {
        this.context = context;
        this.callback = callback;
    }


    public NavigationView.OnNavigationItemSelectedListener handleNavigation(){

        return item -> {
            callback.updateUi(ConfigurationFile.Constants.CLOSE_MENU_DRAWER);
            switch (item.getItemId()){
                case R.id.main:
                {
                    callback.updateUi(ConfigurationFile.FragmentID.FRAGMENT1);
                    break;
                }
                case R.id.rate:
                {
                    CustomUtils.getInstance().playStore(context);
                    break;
                }
                case R.id.share:
                {
                    CustomUtils.getInstance().shareApp(context);
                    break;
                }
                case R.id.who_we_are:
                {

                    break;
                }
                case R.id.logout:
                {
                    CustomUtils.getInstance().clearSharedPref(context);
                    callback.updateUi(ConfigurationFile.Constants.LOGOUT);
                    break;
                }
            }
            return true;
        };
    }

}
