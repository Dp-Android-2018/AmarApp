package dp.com.amarapp.view.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import dp.com.amarapp.R;
import dp.com.amarapp.utils.CustomUtils;
import dp.com.amarapp.utils.NetWorkConnection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(() -> {
            if(NetWorkConnection.isConnectingToInternet(getApplicationContext())) {

                if (CustomUtils.getInstance().getSaveUserObject(getApplicationContext()) != null&&
                        CustomUtils.getInstance().getSaveUserObject(getApplicationContext()).getStatus().equals("true")) {
                    Intent i = new Intent(MainActivity.this, ContainerActivity.class);
                    startActivity(i);
                }else if(CustomUtils.getInstance().getSaveUserObject(getApplicationContext()) != null&&
                        CustomUtils.getInstance().getSaveUserObject(getApplicationContext()).getStatus().equals("false")){
                    Intent intent=new Intent(MainActivity.this,ActivationActivity.class);
                    startActivity(intent);

                } else {
                    Intent i = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(i);
                }
                finish();
            }else{
                Snackbar.make((findViewById(R.id.rl_parent)),R.string.no_internet_connection,Snackbar.LENGTH_LONG).show();
            }
        },3000);
    }
}
