package dp.com.amarapp.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import dp.com.amarapp.R;
import dp.com.amarapp.databinding.ActivityAddProjectBinding;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.view.callback.BaseInterface;
import dp.com.amarapp.viewmodel.AddProjectViewModel;
import dp.com.amarapp.viewmodel.ToolbarViewModel;

public class AddProjectActivity extends BaseActivity implements BaseInterface{
    private AddProjectViewModel projectViewModel;
    private ActivityAddProjectBinding projectBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
        setUpActionBar();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        projectViewModel.onActivityResult(requestCode,resultCode,data);
    }

    public void initBinding(){
        projectViewModel=new AddProjectViewModel(AddProjectActivity.this,this);
        projectBinding= DataBindingUtil.setContentView(AddProjectActivity.this, R.layout.activity_add_project);
        projectBinding.setAddProjectViewModel(projectViewModel);
        projectBinding.rvImages.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
    }

    @Override
    public void updateUi(int code) {
        switch (code) {
            case ConfigurationFile.Constants.NO_INTERNET_CONNECTION_CODE: {
                Snackbar.make(projectBinding.llParent, R.string.no_internet_connection, Snackbar.LENGTH_LONG).show();
                break;
            }
            case ConfigurationFile.Constants.SUCCESS_CODE: {
                Snackbar.make(projectBinding.llParent,"تمت الإضافة بنجاح",Snackbar.LENGTH_LONG).show();
                break;
            }
            case ConfigurationFile.Constants.GETIMAGE:
            {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    pickPhoto.setType("image/*"); //allows any image file type. Change * to specific extension to limit it
                   // pickPhoto.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                    startActivityForResult(pickPhoto , ConfigurationFile.Constants.PICK_IMAGE_REQUEST);
                    break;
            }
            case ConfigurationFile.Constants.FINISH_ADD_PROJECT_ACTIVITY:
            {

                finish();
                break;
            }
        }

    }

    public void setUpActionBar(){
        setSupportActionBar( projectBinding.toolbar.toolbar);
        projectBinding.toolbar.toolbar.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        projectBinding.toolbar.setViewmodel(new ToolbarViewModel(AddProjectActivity.this, ConfigurationFile.Constants.BACK_IMAGE_VISIBILITY_CODE));
    }
}

