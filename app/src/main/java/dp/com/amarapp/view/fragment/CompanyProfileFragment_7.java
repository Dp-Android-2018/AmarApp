package dp.com.amarapp.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dp.com.amarapp.R;
import dp.com.amarapp.databinding.FragmentCompanyProfile7Binding;
import dp.com.amarapp.viewmodel.CompanyProfileViewModel_7;

public class CompanyProfileFragment_7 extends Fragment {
    private CompanyProfileViewModel_7 profileViewModel_7;
    private FragmentCompanyProfile7Binding profile7Binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        profileViewModel_7=new CompanyProfileViewModel_7(getActivity());
        profile7Binding= DataBindingUtil.inflate(inflater, R.layout.fragment_company_profile_7,container,false);
        profile7Binding.setProfileViewModel7(profileViewModel_7);
        View v=profile7Binding.getRoot();
        return v;
    }
}
