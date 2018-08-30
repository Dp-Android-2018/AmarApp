package dp.com.amarapp.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dp.com.amarapp.R;
import dp.com.amarapp.databinding.FragmentCompanyProfile5Binding;
import dp.com.amarapp.viewmodel.CompanyProfileViewModel_5;

public class CompanyProfileFragment_5 extends Fragment {
    private CompanyProfileViewModel_5 profileViewModel_5;
    private FragmentCompanyProfile5Binding profile5Binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        profileViewModel_5=new CompanyProfileViewModel_5(getActivity());
        profile5Binding= DataBindingUtil.inflate(inflater, R.layout.fragment_company_profile_5,container,false);
        profile5Binding.setProfileViewModel5(profileViewModel_5);
        View v=profile5Binding.getRoot();
        return v;
    }
}
