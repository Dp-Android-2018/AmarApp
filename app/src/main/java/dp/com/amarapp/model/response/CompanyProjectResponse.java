package dp.com.amarapp.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import dp.com.amarapp.model.pojo.CompanyProject;

public class CompanyProjectResponse {

    @SerializedName("data")
    private List<CompanyProject> response;

    public List<CompanyProject> getResponse() {
        return response;
    }
}
