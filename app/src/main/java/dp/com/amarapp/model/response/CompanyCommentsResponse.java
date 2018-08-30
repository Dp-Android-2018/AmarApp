package dp.com.amarapp.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import dp.com.amarapp.model.pojo.CompanyComments;

public class CompanyCommentsResponse {
    @SerializedName("data")
    private List<CompanyComments> comments;

    public List<CompanyComments> getComments() {
        return comments;
    }
}
