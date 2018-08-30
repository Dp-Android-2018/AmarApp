package dp.com.amarapp.view.fragment;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dp.com.amarapp.R;
import dp.com.amarapp.databinding.FragmentCompanyProfile1Binding;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.view.activity.CityActivity;
import dp.com.amarapp.view.activity.CountryActivity;
import dp.com.amarapp.view.callback.BaseInterface;
import dp.com.amarapp.viewmodel.CompanyProfileViewModel_1;

public class CompanyProfileFragment_1 extends Fragment implements BaseInterface{
    private CompanyProfileViewModel_1 profileViewModel_1;
    private FragmentCompanyProfile1Binding profile1Binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
       profileViewModel_1=new CompanyProfileViewModel_1(getActivity(),this);
       profile1Binding=DataBindingUtil.inflate(inflater, R.layout.fragment_company_profile_1,container,false);
       profile1Binding.setProfileViewModel1(profileViewModel_1);
       View v=profile1Binding.getRoot();
       return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if(data!=null)
            profileViewModel_1.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    public void updateUi(int code) {
        switch (code){
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
            case (ConfigurationFile.Constants.SELECT_COUNTRY):
            {
                Snackbar.make(profile1Binding.llParent, R.string.select_country,Snackbar.LENGTH_LONG).show();
                break;
            }
        }

    }
}
