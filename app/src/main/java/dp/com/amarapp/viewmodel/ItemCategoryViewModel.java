package dp.com.amarapp.viewmodel;

import android.view.View;

import dp.com.amarapp.Application.MyApplication;
import dp.com.amarapp.model.pojo.CategoriesContent;
import dp.com.amarapp.view.callback.CategoryCallback;

public class ItemCategoryViewModel {
    private CategoriesContent categoriesContent;
    private CategoryCallback categoryCallback;

    public ItemCategoryViewModel(CategoriesContent categoriesContent, CategoryCallback categoryCallback) {
        this.categoriesContent = categoriesContent;
        this.categoryCallback = categoryCallback;
        System.out.println("Category name view Model :"+categoriesContent.getName());
    }

    public void setCategoriesContent(CategoriesContent categoriesContent) {
        this.categoriesContent = categoriesContent;
    }

    public String getName(){
        return categoriesContent.getName();
    }

    public void onItemClick(View v){
        categoryCallback.getCategory(categoriesContent);
        MyApplication.setSpecializations(categoriesContent.getSpecializations());

    }
}
