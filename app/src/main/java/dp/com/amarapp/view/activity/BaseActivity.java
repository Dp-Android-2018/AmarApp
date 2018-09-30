package dp.com.amarapp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import dp.com.amarapp.Application.MyApplication;
import dp.com.amarapp.R;
import dp.com.amarapp.model.pojo.ConnectionReceiver;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by DELL on 27/08/2018.
 */

public class BaseActivity extends AppCompatActivity implements ConnectionReceiver.ConnectionReceiverListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.brownColor));
        }
        checkConnection();
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void checkConnection() {
        boolean isConnected = ConnectionReceiver.isConnected();
        if (!isConnected) {
            //show a No Internet Alert or Dialog
            Intent I = new Intent(BaseActivity.this, NoInternetConnectionActivity.class);
            startActivity(I);
            finish();
            finishAffinity();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        MyApplication.getInstance().setConnectionListener(this);
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        if (!isConnected) {

            //show a No Internet Alert or Dialog
            Intent I = new Intent(BaseActivity.this, NoInternetConnectionActivity.class);
            startActivity(I);
            finish();
            finishAffinity();

        } else {

            Intent I = new Intent(BaseActivity.this, ContainerActivity.class);
            startActivity(I);
            finish();
            finishAffinity();
            // dismiss the dialog or refresh the activity
        }
    }

}
