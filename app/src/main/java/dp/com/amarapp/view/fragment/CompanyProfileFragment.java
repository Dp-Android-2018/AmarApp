package dp.com.amarapp.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import dp.com.amarapp.R;
import dp.com.amarapp.databinding.ActivityCompanyProfileBinding;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.view.callback.BaseInterface;
import dp.com.amarapp.viewmodel.CompanyProfileViewModel;

public class CompanyProfileFragment extends Fragment implements BaseInterface{
    CompanyProfileViewModel profileViewModel;
    ActivityCompanyProfileBinding profileBinding;
    View view=null;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        profileViewModel=new CompanyProfileViewModel(getActivity(),this);
        profileBinding= DataBindingUtil.inflate(inflater, R.layout.activity_company_profile,container,false);
        profileBinding.setProfileViewModel(profileViewModel);
        view=profileBinding.getRoot();
        updateUi(ConfigurationFile.FragmentID.FRAGMENT1);
        return view;
    }

    @Override
    public void updateUi(int code) {
        switch (code){
            case (ConfigurationFile.FragmentID.FRAGMENT1):
            {
                Fragment fragment=new CompanyProfileFragment_1();
                navigationFragments(fragment);
                view.findViewById(R.id.img_1).setVisibility(View.GONE);
                view.findViewById(R.id.img_1_clicked).setVisibility(View.VISIBLE);
                view.findViewById(R.id.img_2_clicked).setVisibility(View.GONE);
                view.findViewById(R.id.img_3_clicked).setVisibility(View.GONE);
                view.findViewById(R.id.img_4_clicked).setVisibility(View.GONE);
                view.findViewById(R.id.img_5_clicked).setVisibility(View.GONE);
                view.findViewById(R.id.img_6_clicked).setVisibility(View.GONE);
                view.findViewById(R.id.img_7_clicked).setVisibility(View.GONE);
                view.findViewById(R.id.img_2).setVisibility(View.VISIBLE);
                view.findViewById(R.id.img_3).setVisibility(View.VISIBLE);
                view.findViewById(R.id.img_4).setVisibility(View.VISIBLE);
                view.findViewById(R.id.img_5).setVisibility(View.VISIBLE);
                view.findViewById(R.id.img_6).setVisibility(View.VISIBLE);
                view.findViewById(R.id.img_7).setVisibility(View.VISIBLE);

                break;
            }
            case (ConfigurationFile.FragmentID.FRAGMENT2):
            {
                Fragment fragment=new CompanyProfileFragment_2();
                navigationFragments(fragment);
                view.findViewById(R.id.img_2).setVisibility(View.GONE);
                view.findViewById(R.id.img_2_clicked).setVisibility(View.VISIBLE);
                view.findViewById(R.id.img_1_clicked).setVisibility(View.GONE);
                view.findViewById(R.id.img_3_clicked).setVisibility(View.GONE);
                view.findViewById(R.id.img_4_clicked).setVisibility(View.GONE);
                view.findViewById(R.id.img_5_clicked).setVisibility(View.GONE);
                view.findViewById(R.id.img_6_clicked).setVisibility(View.GONE);
                view.findViewById(R.id.img_7_clicked).setVisibility(View.GONE);
                view.findViewById(R.id.img_1).setVisibility(View.VISIBLE);
                view.findViewById(R.id.img_3).setVisibility(View.VISIBLE);
                view.findViewById(R.id.img_4).setVisibility(View.VISIBLE);
                view.findViewById(R.id.img_5).setVisibility(View.VISIBLE);
                view.findViewById(R.id.img_6).setVisibility(View.VISIBLE);
                view.findViewById(R.id.img_7).setVisibility(View.VISIBLE);
                break;
            }
            case (ConfigurationFile.FragmentID.FRAGMENT3):
            {
                Fragment fragment=new CompanyProfileFragment_3();
                navigationFragments(fragment);
                view.findViewById(R.id.img_3).setVisibility(View.GONE);
                view.findViewById(R.id.img_3_clicked).setVisibility(View.VISIBLE);
                view.findViewById(R.id.img_2_clicked).setVisibility(View.GONE);
                view.findViewById(R.id.img_1_clicked).setVisibility(View.GONE);
                view.findViewById(R.id.img_4_clicked).setVisibility(View.GONE);
                view.findViewById(R.id.img_5_clicked).setVisibility(View.GONE);
                view.findViewById(R.id.img_6_clicked).setVisibility(View.GONE);
                view.findViewById(R.id.img_7_clicked).setVisibility(View.GONE);
                view.findViewById(R.id.img_2).setVisibility(View.VISIBLE);
                view.findViewById(R.id.img_1).setVisibility(View.VISIBLE);
                view.findViewById(R.id.img_4).setVisibility(View.VISIBLE);
                view.findViewById(R.id.img_5).setVisibility(View.VISIBLE);
                view.findViewById(R.id.img_6).setVisibility(View.VISIBLE);
                view.findViewById(R.id.img_7).setVisibility(View.VISIBLE);
                break;
            }
            case (ConfigurationFile.FragmentID.FRAGMENT4):
            {
                Fragment fragment=new CompanyProfileFragment_4();
                navigationFragments(fragment);
                view.findViewById(R.id.img_4).setVisibility(View.GONE);
                view.findViewById(R.id.img_4_clicked).setVisibility(View.VISIBLE);
                view.findViewById(R.id.img_2_clicked).setVisibility(View.GONE);
                view.findViewById(R.id.img_3_clicked).setVisibility(View.GONE);
                view.findViewById(R.id.img_1_clicked).setVisibility(View.GONE);
                view.findViewById(R.id.img_5_clicked).setVisibility(View.GONE);
                view.findViewById(R.id.img_6_clicked).setVisibility(View.GONE);
                view.findViewById(R.id.img_7_clicked).setVisibility(View.GONE);
                view.findViewById(R.id.img_2).setVisibility(View.VISIBLE);
                view.findViewById(R.id.img_3).setVisibility(View.VISIBLE);
                view.findViewById(R.id.img_1).setVisibility(View.VISIBLE);
                view.findViewById(R.id.img_5).setVisibility(View.VISIBLE);
                view.findViewById(R.id.img_6).setVisibility(View.VISIBLE);
                view.findViewById(R.id.img_7).setVisibility(View.VISIBLE);
                break;
            }
            case (ConfigurationFile.FragmentID.FRAGMENT5):
            {
                Fragment fragment=new CompanyProfileFragment_5();
                navigationFragments(fragment);
                view.findViewById(R.id.img_5).setVisibility(View.GONE);
                view.findViewById(R.id.img_5_clicked).setVisibility(View.VISIBLE);
                view.findViewById(R.id.img_2_clicked).setVisibility(View.GONE);
                view.findViewById(R.id.img_3_clicked).setVisibility(View.GONE);
                view.findViewById(R.id.img_4_clicked).setVisibility(View.GONE);
                view.findViewById(R.id.img_1_clicked).setVisibility(View.GONE);
                view.findViewById(R.id.img_6_clicked).setVisibility(View.GONE);
                view.findViewById(R.id.img_7_clicked).setVisibility(View.GONE);
                view.findViewById(R.id.img_2).setVisibility(View.VISIBLE);
                view.findViewById(R.id.img_3).setVisibility(View.VISIBLE);
                view.findViewById(R.id.img_4).setVisibility(View.VISIBLE);
                view.findViewById(R.id.img_1).setVisibility(View.VISIBLE);
                view.findViewById(R.id.img_6).setVisibility(View.VISIBLE);
                view.findViewById(R.id.img_7).setVisibility(View.VISIBLE);
                break;
            }
            case (ConfigurationFile.FragmentID.FRAGMENT6):
            {
                Fragment fragment=new CompanyProfileFragment_6();
                navigationFragments(fragment);
                view.findViewById(R.id.img_6).setVisibility(View.GONE);
                view.findViewById(R.id.img_6_clicked).setVisibility(View.VISIBLE);
                view.findViewById(R.id.img_2_clicked).setVisibility(View.GONE);
                view.findViewById(R.id.img_3_clicked).setVisibility(View.GONE);
                view.findViewById(R.id.img_4_clicked).setVisibility(View.GONE);
                view.findViewById(R.id.img_5_clicked).setVisibility(View.GONE);
                view.findViewById(R.id.img_1_clicked).setVisibility(View.GONE);
                view.findViewById(R.id.img_7_clicked).setVisibility(View.GONE);
                view.findViewById(R.id.img_2).setVisibility(View.VISIBLE);
                view.findViewById(R.id.img_3).setVisibility(View.VISIBLE);
                view.findViewById(R.id.img_4).setVisibility(View.VISIBLE);
                view.findViewById(R.id.img_5).setVisibility(View.VISIBLE);
                view.findViewById(R.id.img_1).setVisibility(View.VISIBLE);
                view.findViewById(R.id.img_7).setVisibility(View.VISIBLE);
                break;
            }
            case (ConfigurationFile.FragmentID.FRAGMENT7):
            {
                Fragment fragment=new CompanyProfileFragment_7();
                navigationFragments(fragment);
                view.findViewById(R.id.img_7).setVisibility(View.GONE);
                view.findViewById(R.id.img_7_clicked).setVisibility(View.VISIBLE);
                view.findViewById(R.id.img_2_clicked).setVisibility(View.GONE);
                view.findViewById(R.id.img_3_clicked).setVisibility(View.GONE);
                view.findViewById(R.id.img_4_clicked).setVisibility(View.GONE);
                view.findViewById(R.id.img_5_clicked).setVisibility(View.GONE);
                view.findViewById(R.id.img_6_clicked).setVisibility(View.GONE);
                view.findViewById(R.id.img_1_clicked).setVisibility(View.GONE);
                view.findViewById(R.id.img_2).setVisibility(View.VISIBLE);
                view.findViewById(R.id.img_3).setVisibility(View.VISIBLE);
                view.findViewById(R.id.img_4).setVisibility(View.VISIBLE);
                view.findViewById(R.id.img_5).setVisibility(View.VISIBLE);
                view.findViewById(R.id.img_6).setVisibility(View.VISIBLE);
                view.findViewById(R.id.img_1).setVisibility(View.VISIBLE);
                break;
            }
        }
    }

    public void navigationFragments(Fragment fragment){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.popBackStack();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.profile_frame,fragment);
        fragmentTransaction.commit();
    }
}
