package dp.com.amarapp.viewmodel;


import android.app.Fragment;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ImageView;

import dp.com.amarapp.R;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.utils.CustomUtils;
import dp.com.amarapp.view.callback.BaseInterface;

public class CompanyProfileViewModel  {
    private Context context;
    private BaseInterface callback;

    public CompanyProfileViewModel(Context context,BaseInterface callback) {
        this.context = context;
        this.callback=callback;
    }

    public void onClick(View v){

        switch (v.getId()){

            case R.id.img_1:
            {
                callback.updateUi(ConfigurationFile.FragmentID.FRAGMENT1);
                break;
            }
            case R.id.img_2:
            {
                callback.updateUi(ConfigurationFile.FragmentID.FRAGMENT2);
                break;
            }
            case R.id.img_3:
            {
                callback.updateUi(ConfigurationFile.FragmentID.FRAGMENT3);
                break;
            }
            case R.id.img_4:
            {
                callback.updateUi(ConfigurationFile.FragmentID.FRAGMENT4);
                break;
            }
            case R.id.img_5:
            {
                callback.updateUi(ConfigurationFile.FragmentID.FRAGMENT5);
                break;
            }
            case R.id.img_6:
            {
                callback.updateUi(ConfigurationFile.FragmentID.FRAGMENT6);
                break;
            }
            case R.id.img_7:
            {
                callback.updateUi(ConfigurationFile.FragmentID.FRAGMENT7);
                break;
            }

        }
    }

}
