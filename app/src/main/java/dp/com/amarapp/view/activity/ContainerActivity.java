package dp.com.amarapp.view.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

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
import dp.com.amarapp.viewmodel.BottomBarViewModel;
import dp.com.amarapp.viewmodel.ContainerViewModel;
import dp.com.amarapp.viewmodel.ToolbarViewModel;

public class ContainerActivity extends BaseActivity implements BaseInterface{

    ContainerViewModel containerViewModel;
    ActivityContainerBinding containerBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
        updateUi(ConfigurationFile.FragmentID.HOME);
        setUpActionBar();
        drawer_layout();

    }

    public void setUpActionBar(){
        setSupportActionBar( containerBinding.toolbar.toolbar);
        containerBinding.toolbar.toolbar.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        containerBinding.toolbar.setViewmodel(new ToolbarViewModel(ContainerActivity.this, ConfigurationFile.Constants.BACK_IMAGE_UNVISIBILITY_CODE));}

    @Override
    public void updateUi(int code) {

        switch (code){
            case ConfigurationFile.Constants.CLOSE_MENU_DRAWER: {
                containerBinding.drawer.closeDrawers();
            }
            case ConfigurationFile.FragmentID.HOME:
            {
                containerBinding.drawer.closeDrawer(Gravity.END);
                //Drawer.closeDrawer(Gravity.END);
                findViewById(R.id.search).setVisibility(View.GONE);
                findViewById(R.id.search_clicked).setVisibility(View.VISIBLE);
                findViewById(R.id.add_advert).setVisibility(View.VISIBLE);
                findViewById(R.id.add_advert_clicked).setVisibility(View.GONE);
                findViewById(R.id.adverts).setVisibility(View.VISIBLE);
                findViewById(R.id.adverts_clicked).setVisibility(View.GONE);
                findViewById(R.id.iv_companies).setVisibility(View.VISIBLE);
                findViewById(R.id.companies_clicked).setVisibility(View.GONE);
                findViewById(R.id.iv_settings).setVisibility(View.VISIBLE);
                findViewById(R.id.settings_clicked).setVisibility(View.GONE);
                Fragment home=new HomeFragment();
                navigationFragments(home);
                break;
            }
            case ConfigurationFile.FragmentID.ADDADVERT:
            {
                findViewById(R.id.add_advert).setVisibility(View.GONE);
                findViewById(R.id.add_advert_clicked).setVisibility(View.VISIBLE);
                findViewById(R.id.search).setVisibility(View.VISIBLE);
                findViewById(R.id.search_clicked).setVisibility(View.GONE);
                findViewById(R.id.adverts).setVisibility(View.VISIBLE);
                findViewById(R.id.adverts_clicked).setVisibility(View.GONE);
                findViewById(R.id.iv_companies).setVisibility(View.VISIBLE);
                findViewById(R.id.companies_clicked).setVisibility(View.GONE);
                findViewById(R.id.iv_settings).setVisibility(View.VISIBLE);
                findViewById(R.id.settings_clicked).setVisibility(View.GONE);
                Fragment addAdvert=new AddAdvertFragment();
                navigationFragments(addAdvert);
                break;
            }
            case ConfigurationFile.FragmentID.ADVERTS:
            {
                findViewById(R.id.adverts).setVisibility(View.GONE);
                findViewById(R.id.adverts_clicked).setVisibility(View.VISIBLE);
                findViewById(R.id.search).setVisibility(View.VISIBLE);
                findViewById(R.id.search_clicked).setVisibility(View.GONE);
                findViewById(R.id.add_advert).setVisibility(View.VISIBLE);
                findViewById(R.id.add_advert_clicked).setVisibility(View.GONE);
                findViewById(R.id.iv_companies).setVisibility(View.VISIBLE);
                findViewById(R.id.companies_clicked).setVisibility(View.GONE);
                findViewById(R.id.iv_settings).setVisibility(View.VISIBLE);
                findViewById(R.id.settings_clicked).setVisibility(View.GONE);
                Fragment advert=new ShowAdvertsFragment();
                navigationFragments(advert);
                break;
            }
            case ConfigurationFile.FragmentID.COMPANIES:
            {
                findViewById(R.id.iv_companies).setVisibility(View.GONE);
                findViewById(R.id.companies_clicked).setVisibility(View.VISIBLE);
                findViewById(R.id.search).setVisibility(View.VISIBLE);
                findViewById(R.id.search_clicked).setVisibility(View.GONE);
                findViewById(R.id.add_advert).setVisibility(View.VISIBLE);
                findViewById(R.id.add_advert_clicked).setVisibility(View.GONE);
                findViewById(R.id.adverts).setVisibility(View.VISIBLE);
                findViewById(R.id.adverts_clicked).setVisibility(View.GONE);
                findViewById(R.id.search).setVisibility(View.VISIBLE);
                findViewById(R.id.search_clicked).setVisibility(View.GONE);
                findViewById(R.id.iv_settings).setVisibility(View.VISIBLE);
                findViewById(R.id.settings_clicked).setVisibility(View.GONE);
                Fragment companies=new CompaniesGridFragment();
                navigationFragments(companies);
                break;
            }
            case ConfigurationFile.FragmentID.SETTINGS:
            {
                Fragment setting;
                findViewById(R.id.search).setVisibility(View.VISIBLE);
                findViewById(R.id.search_clicked).setVisibility(View.GONE);
                findViewById(R.id.add_advert).setVisibility(View.VISIBLE);
                findViewById(R.id.add_advert_clicked).setVisibility(View.GONE);
                findViewById(R.id.adverts).setVisibility(View.VISIBLE);
                findViewById(R.id.adverts_clicked).setVisibility(View.GONE);
                findViewById(R.id.iv_companies).setVisibility(View.VISIBLE);
                findViewById(R.id.companies_clicked).setVisibility(View.GONE);
                findViewById(R.id.iv_settings).setVisibility(View.GONE);
                findViewById(R.id.settings_clicked).setVisibility(View.VISIBLE);
                if (CustomUtils.getInstance().getSaveUserObject(this)==null) {
                    Snackbar.make(containerBinding.drawer, "أنت غير مسجل", Snackbar.LENGTH_LONG).show();
                }else if (CustomUtils.getInstance().getSaveUserObject(this).getRole().
                            equals(ConfigurationFile.Constants.CLIENT)) {
                        setting = new ClientSettingFragment();
                        navigationFragments(setting);

                    } else if (CustomUtils.getInstance().getSaveUserObject(this).getRole().
                            equals(ConfigurationFile.Constants.COMPANY)) {
                        setting = new CompanyProfileFragment();
                        navigationFragments(setting);
                    }

                break;
            }
        }
    }

    public void initBinding(){
        containerViewModel=new ContainerViewModel(ContainerActivity.this,this);
        containerBinding= DataBindingUtil.setContentView(ContainerActivity.this, R.layout.activity_container);
        containerBinding.setContainerViewModel(containerViewModel);
         /*toolbar=(Toolbar) containerBinding.toolbar.toolbar;
          toolbar.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
          setSupportActionBar(toolbar);*/
        containerBinding.bottomBar.setViewModel(new BottomBarViewModel(ContainerActivity.this,this));
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
        containerBinding.drawer.addDrawerListener(mDrawerToggle);
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



}
