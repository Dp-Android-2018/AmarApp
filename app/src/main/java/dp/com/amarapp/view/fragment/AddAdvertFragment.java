package dp.com.amarapp.view.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dp.com.amarapp.R;
import dp.com.amarapp.databinding.ActivityAddAdvertBinding;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.view.activity.CityActivity;
import dp.com.amarapp.view.activity.ContainerActivity;
import dp.com.amarapp.view.activity.CountryActivity;
import dp.com.amarapp.view.callback.BaseInterface;
import dp.com.amarapp.viewmodel.AddAdvertViewModel;

public class AddAdvertFragment extends Fragment implements BaseInterface{
    private AddAdvertViewModel addAdvertViewModel;
    private ActivityAddAdvertBinding addAdvertBinding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        addAdvertViewModel=new AddAdvertViewModel(getActivity(),this);
        addAdvertBinding=DataBindingUtil.inflate(inflater, R.layout.activity_add_advert,container,false);
        addAdvertBinding.setAddAdvertViewModel(addAdvertViewModel);
        View view=addAdvertBinding.getRoot();
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        addAdvertViewModel.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    public void updateUi(int code) {
        switch (code){
            case (ConfigurationFile.Constants.NO_INTERNET_CONNECTION_CODE):
            {
                Snackbar.make(addAdvertBinding.llParent,R.string.no_internet_connection,Snackbar.LENGTH_LONG).show();
                break;
            }
            case (ConfigurationFile.Constants.FILL_ALL_DATA_ERROR):
            {
                Snackbar.make(addAdvertBinding.llParent,R.string.msg_fill_data,Snackbar.LENGTH_LONG).show();
                break;
            }
            case (ConfigurationFile.Constants.SERVER_ERROR):
            {
                Snackbar.make(addAdvertBinding.llParent,R.string.server_error,Snackbar.LENGTH_LONG).show();
                break;
            }
            case (ConfigurationFile.Constants.INVALED_DATA_CODE):
            {
                Snackbar.make(addAdvertBinding.llParent,R.string.invaled_data,Snackbar.LENGTH_LONG).show();
                break;
            }
            case (ConfigurationFile.Constants.SUCCESS_CODE):
            {
                Snackbar.make(addAdvertBinding.llParent, R.string.wait_accept,Snackbar.LENGTH_LONG).show();
                new Handler().postDelayed(() -> {
                    Intent i=new Intent(getActivity(),ContainerActivity.class);
                    startActivity(i);
                    ActivityCompat.finishAffinity(getActivity());
                },2000);
                break;
            }
            case ConfigurationFile.Constants.GETIMAGE:
            {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickPhoto.setType("image/*"); //allows any image file type. Change * to specific extension to limit it
                //pickPhoto.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                startActivityForResult(pickPhoto , ConfigurationFile.Constants.PICK_IMAGE_REQUEST);
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
            }case ConfigurationFile.Constants.SELECT_COUNTRY:
            {
                Snackbar.make(addAdvertBinding.llParent,R.string.select_country,Snackbar.LENGTH_LONG).show();
                break;
            }
        }
    }
}
