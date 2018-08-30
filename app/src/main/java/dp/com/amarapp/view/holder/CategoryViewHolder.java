package dp.com.amarapp.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import dp.com.amarapp.databinding.CategoryListItemBinding;
import dp.com.amarapp.model.pojo.CategoriesContent;
import dp.com.amarapp.model.pojo.Category;
import dp.com.amarapp.view.callback.CategoryCallback;
import dp.com.amarapp.viewmodel.ItemCategoryViewModel;

public class CategoryViewHolder extends RecyclerView.ViewHolder {
    private CategoryListItemBinding binding;
    private CategoryCallback categoryCallback;

    public CategoryViewHolder(CategoryListItemBinding binding, CategoryCallback categoryCallback) {
        super(binding.rlParent);
        this.binding = binding;
        this.categoryCallback = categoryCallback;
    }

    public void bindItemCategory(CategoriesContent categoriesContent){
        if(binding.getItemCategoryViewModel()==null)
            binding.setItemCategoryViewModel(new ItemCategoryViewModel(categoriesContent,categoryCallback));

        else
            binding.getItemCategoryViewModel().setCategoriesContent(categoriesContent);

        System.out.println("Category View Holder : "+categoriesContent.getName());
    }
}
