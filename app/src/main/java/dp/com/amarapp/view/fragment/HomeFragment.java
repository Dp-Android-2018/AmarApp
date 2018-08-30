package dp.com.amarapp.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import dp.com.amarapp.Application.MyApplication;
import dp.com.amarapp.R;
import dp.com.amarapp.databinding.ActivityHomeBinding;
import dp.com.amarapp.model.pojo.City;
import dp.com.amarapp.model.pojo.Specialization;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.view.activity.CategoriesActivity;
import dp.com.amarapp.view.activity.CityActivity;
import dp.com.amarapp.view.activity.CountryActivity;
import dp.com.amarapp.view.activity.SpecializationActivity;
import dp.com.amarapp.view.callback.BaseInterface;
import dp.com.amarapp.viewmodel.HomeViewModel;

public class HomeFragment extends Fragment implements BaseInterface{

    private HomeViewModel homeViewModel;
    ActivityHomeBinding homeBinding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        homeViewModel=new HomeViewModel(getActivity(),this);
        homeBinding= DataBindingUtil.inflate(inflater,R.layout.activity_home,container,false);
        homeBinding.setHomeViewModel(homeViewModel);
        homeBinding.rvAdverts.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        View view=homeBinding.getRoot();
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
      //  super.onActivityResult(requestCode, resultCode, data);
        System.out.println("Activity Result :"+requestCode+" "+resultCode);
        if (data!=null)
        homeViewModel.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    public void updateUi(int code) {
        switch (code){
            case(ConfigurationFile.Constants.NO_INTERNET_CONNECTION_CODE):
            {
                Snackbar.make(homeBinding.llParent,R.string.no_internet_connection,Snackbar.LENGTH_LONG).show();
                break;
            }
            case (ConfigurationFile.Constants.SUCCESS_CODE_second):
            {
                Snackbar.make(homeBinding.llParent,"Success",Snackbar.LENGTH_LONG).show();
                break;
            }

            case ConfigurationFile.FragmentID.SEARCH:
            {
                Toolbar toolbar=((AppCompatActivity)getActivity()).findViewById(R.id.toolbar);
                ImageView ivFilter=toolbar.findViewById(R.id.iv_filter);
                ImageView ivBack=toolbar.findViewById(R.id.back_arrow);
                ivFilter.setVisibility(View.VISIBLE);
                ivBack.setVisibility(View.GONE);
                CompanySearchFragment searchFragment=new CompanySearchFragment(
                        homeViewModel.country!=null? homeViewModel.country.getId():0,
                        homeViewModel.city!=null?homeViewModel.city.getId():0,
                        homeViewModel.categoriesContent!=null?homeViewModel.categoriesContent.getId():0,
                        homeViewModel.specialization!=null?homeViewModel.specialization.getId():0);
                navigationFragments(searchFragment);
                break;
            }
            case ConfigurationFile.Constants.MOVE_TO_COUNTRY_ACT:
            {
                Intent i=new Intent(getActivity(), CountryActivity.class);
                startActivityForResult(i,ConfigurationFile.IntentConstants.REQUEST_CODE_COUNTRY);
                break;
            }
            case ConfigurationFile.Constants.MOVE_TO_CITY_ACT:
            {
                Intent i = new Intent(getActivity(),CityActivity.class);
                startActivityForResult(i, ConfigurationFile.IntentConstants.REQUEST_CODE_CITY);
                break;
            }
            case ConfigurationFile.Constants.MOVE_TO_CATEGORY_ACT:
            {
                Intent i = new Intent(getActivity(),CategoriesActivity.class);
                startActivityForResult(i, ConfigurationFile.IntentConstants.REQUEST_CODE_CATEGORY);
                break;
            }
            case (ConfigurationFile.Constants.SELECT_GATEGORY):
            {
                Snackbar.make(homeBinding.llParent, R.string.select_category,Snackbar.LENGTH_LONG).show();
                break;
            }
            case (ConfigurationFile.Constants.SELECT_COUNTRY):
            {
                Snackbar.make(homeBinding.llParent, R.string.select_country,Snackbar.LENGTH_LONG).show();
                break;
            }
            case ConfigurationFile.Constants.MOVE_TO_SPECIALIZATION_ACT:
            {
                Intent i = new Intent(getActivity(),SpecializationActivity.class);
                startActivityForResult(i, ConfigurationFile.IntentConstants.REQUEST_CODE_SPECIALIZATION);
                break;
            }




        }
    }

    public void navigationFragments(Fragment fragment){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.popBackStack();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame,fragment);
        fragmentTransaction.commit();
    }
}
