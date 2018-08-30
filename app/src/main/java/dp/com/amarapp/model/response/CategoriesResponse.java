package dp.com.amarapp.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import dp.com.amarapp.model.pojo.CategoriesContent;

public class CategoriesResponse {

    @SerializedName("data")
    List<CategoriesContent> categories;

    public List<CategoriesContent> getCategories() {
        return categories;
    }
}

