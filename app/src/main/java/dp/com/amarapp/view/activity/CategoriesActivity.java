package dp.com.amarapp.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import dp.com.amarapp.R;
import dp.com.amarapp.databinding.ActivityCategoriesBinding;
import dp.com.amarapp.model.pojo.CategoriesContent;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.view.adapter.CategoryAdapter;
import dp.com.amarapp.view.callback.BaseInterface;
import dp.com.amarapp.view.callback.CategoryCallback;
import dp.com.amarapp.viewmodel.CategoriesViewModel;
import dp.com.amarapp.viewmodel.ToolbarViewModel;

public class CategoriesActivity extends BaseActivity implements BaseInterface,CategoryCallback{
    private CategoriesViewModel categoriesViewModel;
    private ActivityCategoriesBinding categoriesBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
        setUpActionBar();
    }

    public void initBinding(){
        categoriesViewModel=new CategoriesViewModel(CategoriesActivity.this,this);
        categoriesBinding= DataBindingUtil.setContentView(CategoriesActivity.this, R.layout.activity_categories);
        categoriesBinding.setCategoriesViewModel(categoriesViewModel);
        categoriesBinding.rvCategories.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
    }

    public void setUpActionBar(){
        setSupportActionBar( categoriesBinding.toolbar.toolbar);
        categoriesBinding.toolbar.toolbar.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        categoriesBinding.toolbar.setViewmodel(new ToolbarViewModel(CategoriesActivity.this, ConfigurationFile.Constants.BACK_IMAGE_VISIBILITY_CODE));}

    @Override
    public void updateUi(int code) {
        if(code==ConfigurationFile.Constants.CATEGORY_ADAPTER)
        {
            System.out.println("size Category in adapter : "+categoriesViewModel.categoriesContentList.size());
            CategoryAdapter adapter=new CategoryAdapter(categoriesViewModel.categoriesContentList,this);
            categoriesBinding.rvCategories.setAdapter(adapter);
        }

    }

    @Override
    public void getCategory(CategoriesContent categoriesContent) {
        Intent i=getIntent();
        i.putExtra(ConfigurationFile.IntentConstants.CATEGORY_DATA,categoriesContent);
        setResult(ConfigurationFile.IntentConstants.REQUEST_CODE_CATEGORY,i);
        finish();
    }
}
