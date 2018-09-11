package dp.com.amarapp.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.View;

import com.roughike.bottombar.OnTabSelectListener;

import dp.com.amarapp.R;
import dp.com.amarapp.databinding.ActivityContainerBinding;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.utils.CustomUtils;
import dp.com.amarapp.view.callback.BaseInterface;
import dp.com.amarapp.view.fragment.AddAdvertFragment;
import dp.com.amarapp.view.fragment.ClientSettingFragment;
import dp.com.amarapp.view.fragment.CompaniesGridFragment;
import dp.com.amarapp.view.fragment.CompanyProfileFragment;
import dp.com.amarapp.view.fragment.HomeFragment;
import dp.com.amarapp.view.fragment.ShowAdvertsFragment;
import dp.com.amarapp.viewmodel.ContainerViewModel;
import dp.com.amarapp.viewmodel.ToolbarViewModel;

public class ContainerActivity extends BaseActivity implements BaseInterface{

    ContainerViewModel containerViewModel;
    ActivityContainerBinding containerBinding;
    boolean doubleBackToExitPressedOnce = false;
    Fragment home=new HomeFragment();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
        containerBinding.bottomBar.setDefaultTab(R.id.search);
        setUpActionBar();
        drawer_layout();
    }

    public void setUpActionBar(){
        setSupportActionBar( containerBinding.toolbar.toolbar);
        containerBinding.toolbar.toolbar.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        containerBinding.toolbar.setViewmodel(new ToolbarViewModel(ContainerActivity.this, ConfigurationFile.Constants.BACK_IMAGE_UNVISIBILITY_CODE));}

    @Override
    public void updateUi(int code) {

        switch (code) {
            case ConfigurationFile.Constants.CLOSE_MENU_DRAWER: {
                containerBinding.drawer.closeDrawers();
            }
            case ConfigurationFile.FragmentID.FRAGMENT1: {
                navigationFragments(home);
                containerBinding.bottomBar.setDefaultTab(R.id.search);
                break;
            }
            case ConfigurationFile.Constants.LOGOUT:{
                Intent intent=new Intent(this,LoginActivity.class);
                startActivity(intent);
                finishAffinity();
                break;
            }
        }
    }

    public void initBinding(){
        containerViewModel=new ContainerViewModel(ContainerActivity.this,this);
        containerBinding= DataBindingUtil.setContentView(ContainerActivity.this, R.layout.activity_container);
        containerBinding.setContainerViewModel(containerViewModel);
        bottombar();
         /*toolbar=(Toolbar) containerBinding.toolbar.toolbar;
          toolbar.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
          setSupportActionBar(toolbar);*/
        //containerBinding.bottomBar.setViewModel(new BottomBarViewModel(ContainerActivity.this,this));
    }

    public void navigationFragments(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame,fragment);
        fragmentTransaction.commit();
    }

    public void drawer_layout(){
        NavigationView navigationView=findViewById(R.id.navigation_view);

        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,containerBinding.drawer,containerBinding.toolbar.toolbar,R.string.drawer_open,R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        }; // Drawer Toggle Object Made
        mDrawerToggle.setDrawerSlideAnimationEnabled(true);
        mDrawerToggle.syncState();
        containerBinding.toolbar.toolbar.setNavigationOnClickListener(v -> {
            if (containerBinding.drawer.isDrawerOpen(Gravity.END)) {
                containerBinding.drawer.closeDrawer(Gravity.END);
            } else {
                containerBinding.drawer.openDrawer(Gravity.END);
            }
        });
        navigationView.getMenu().getItem(0).setChecked(true);
    }


    public void bottombar(){
        containerBinding.bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(int tabId) {
                switch(tabId){
                    case R.id.settings:{
                        if(CustomUtils.getInstance().getSaveUserObject(getApplicationContext())!=null) {
                            Fragment setting;
                            if (CustomUtils.getInstance().getSaveUserObject(getApplicationContext()).getRole().
                                    equals(ConfigurationFile.Constants.CLIENT)) {
                                setting = new ClientSettingFragment();
                                navigationFragments(setting);
                            } else if (CustomUtils.getInstance().getSaveUserObject(getApplicationContext()).getRole().
                                    equals(ConfigurationFile.Constants.COMPANY)) {
                                setting = new CompanyProfileFragment();
                                navigationFragments(setting);
                            }
                        }else{
                            Snackbar.make(containerBinding.drawer, "انت غير مسجل", Snackbar.LENGTH_LONG).show();

                        }
                        break;
                    }
                    case R.id.companies:{
                        Fragment companies=new CompaniesGridFragment();
                        navigationFragments(companies);
                        break;
                    }
                    case R.id.search:{
                        navigationFragments(home);
                        break;
                    }
                    case R.id.adverts:{
                        Fragment advert=new ShowAdvertsFragment();
                        navigationFragments(advert);
                        break;
                    }
                    case R.id.add_advert:{
                        if(CustomUtils.getInstance().getSaveUserObject(getApplicationContext())!=null&&
                                CustomUtils.getInstance().getSaveUserObject(getApplicationContext()).getRole().equals(ConfigurationFile.Constants.COMPANY)&&
                                CustomUtils.getInstance().getSaveUserObject(getApplicationContext()).getStatus().equals("true")) {
                            Fragment addAdvert = new AddAdvertFragment();
                            navigationFragments(addAdvert);
                        }else{
                            Snackbar.make(containerBinding.drawer, "إضافة إعلان غير متاحه لك", Snackbar.LENGTH_LONG).show();
                        }
                        break;
                    }
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(CustomUtils.getInstance().getSaveUserObject(getApplicationContext())!=null) {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Snackbar.make(containerBinding.drawer, "إضغط مره اخرى للخروج", Snackbar.LENGTH_LONG).show();
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }
    }
}
