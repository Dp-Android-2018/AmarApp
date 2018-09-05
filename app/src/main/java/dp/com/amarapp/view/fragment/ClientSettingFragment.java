package dp.com.amarapp.view.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import dp.com.amarapp.R;
import dp.com.amarapp.databinding.ActivityClientSettingsBinding;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.view.activity.LoginActivity;
import dp.com.amarapp.view.callback.BaseInterface;
import dp.com.amarapp.viewmodel.ClientSettingsViewModel;

public class ClientSettingFragment extends Fragment implements BaseInterface{
    private ClientSettingsViewModel clientSettingsViewModel;
    private ActivityClientSettingsBinding clientSettingsBinding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        clientSettingsViewModel=new ClientSettingsViewModel(getActivity(),this);
        clientSettingsBinding= DataBindingUtil.inflate(inflater,R.layout.activity_client_settings,container,false);
        clientSettingsBinding.setClientSettingsViewModel(clientSettingsViewModel);
        View view=clientSettingsBinding.getRoot();
        Toolbar toolbar=((AppCompatActivity)getActivity()).findViewById(R.id.toolbar);
        ImageView ivFilter=toolbar.findViewById(R.id.iv_filter);
        ivFilter.setVisibility(View.GONE);
        return view;
    }

    @Override
    public void updateUi(int code) {
        if(code== ConfigurationFile.Constants.LOGOUT){
            getActivity().finishAffinity();
            Intent intent=new Intent(getActivity(),LoginActivity.class);
            getActivity().startActivity(intent);
        }
    }
}
