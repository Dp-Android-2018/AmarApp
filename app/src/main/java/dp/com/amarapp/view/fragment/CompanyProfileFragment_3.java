package dp.com.amarapp.view.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dp.com.amarapp.R;
import dp.com.amarapp.databinding.FragmentCompanyProfile3Binding;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.view.callback.BaseInterface;
import dp.com.amarapp.viewmodel.CompanyProfileViewModel_3;

public class CompanyProfileFragment_3 extends Fragment implements BaseInterface {
    private CompanyProfileViewModel_3 profileViewModel_3;
    private FragmentCompanyProfile3Binding profile3Binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        profileViewModel_3=new CompanyProfileViewModel_3(getActivity(),this);
        profile3Binding= DataBindingUtil.inflate(inflater, R.layout.fragment_company_profile_3,container,false);
        profile3Binding.setProfileViewModel3(profileViewModel_3);
        View v=profile3Binding.getRoot();
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        profileViewModel_3.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    public void updateUi(int code) {
        switch (code){
            case ConfigurationFile.Constants.GETIMAGE:
            {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickPhoto.setType("image/*"); //allows any image file type. Change * to specific extension to limit it
               // pickPhoto.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                startActivityForResult(pickPhoto , ConfigurationFile.Constants.PICK_IMAGE_REQUEST);
                break;
            }
        }

    }
}
