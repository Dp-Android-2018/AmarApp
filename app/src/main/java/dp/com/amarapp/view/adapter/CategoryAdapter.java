package dp.com.amarapp.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import dp.com.amarapp.R;
import dp.com.amarapp.databinding.CategoryListItemBinding;
import dp.com.amarapp.model.pojo.CategoriesContent;
import dp.com.amarapp.view.callback.CategoryCallback;
import dp.com.amarapp.view.holder.CategoryViewHolder;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {
    private List<CategoriesContent> categoriesContentList;
    private CategoryCallback categoryCallback;

    public CategoryAdapter(List<CategoriesContent> categoriesContentList, CategoryCallback categoryCallback) {
        this.categoriesContentList = categoriesContentList;
        this.categoryCallback = categoryCallback;
        System.out.println("size Category adpater 2 :" +categoriesContentList.size());
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CategoryListItemBinding binding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.category_list_item,parent,false);
        return new CategoryViewHolder(binding,categoryCallback);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.bindItemCategory(categoriesContentList.get(position));

    }

    @Override
    public int getItemCount() {
        return categoriesContentList.size();
    }
}
