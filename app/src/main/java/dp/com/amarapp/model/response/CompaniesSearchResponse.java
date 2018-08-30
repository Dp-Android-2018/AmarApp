package dp.com.amarapp.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import dp.com.amarapp.model.pojo.Links;

public class CompaniesSearchResponse {

    @SerializedName("data")
    private ArrayList<CompanyLoginResponse> searchResponses;


    @SerializedName("links")
    private Links links;
    public ArrayList<CompanyLoginResponse> getSearchResponses() {
        return searchResponses;
    }

    public Links getLinks() {
        return links;
    }
}
