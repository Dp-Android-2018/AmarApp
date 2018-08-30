package dp.com.amarapp.view.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dp.com.amarapp.R;
import dp.com.amarapp.databinding.FragmentCompanyProfile6Binding;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.view.activity.AddProjectActivity;
import dp.com.amarapp.view.callback.BaseInterface;
import dp.com.amarapp.viewmodel.CompanyProfileViewModel_6;

public class CompanyProfileFragment_6 extends Fragment implements BaseInterface {
    private CompanyProfileViewModel_6 profileViewModel_6;
    private FragmentCompanyProfile6Binding profile6Binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        profileViewModel_6=new CompanyProfileViewModel_6(getActivity(),this);
        profile6Binding= DataBindingUtil.inflate(inflater, R.layout.fragment_company_profile_6,container,false);
        profile6Binding.setProfileViewModel6(profileViewModel_6);
        View v=profile6Binding.getRoot();
        return v;
    }

    @Override
    public void updateUi(int code) {
        switch (code){
            case (ConfigurationFile.Constants.NO_INTERNET_CONNECTION_CODE):
            {
                Snackbar.make(profile6Binding.rlParent, R.string.no_internet_connection,Snackbar.LENGTH_LONG).show();
                break;
            }
            case (777):
            {
                Snackbar.make(profile6Binding.rlParent,"no data here",Snackbar.LENGTH_LONG).show();
                break;
            }
            case ConfigurationFile.Constants.ADDPROJECT :
            {
                Intent intent=new Intent(getContext(),AddProjectActivity.class);
                startActivity(intent);
            }
        }
    }
}
