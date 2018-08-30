package dp.com.amarapp.model.response;

import com.google.gson.annotations.SerializedName;

import dp.com.amarapp.model.pojo.LoginResponseContent;

/**
 * Created by DELL on 24/07/2018.
 */

public class CompanyRegisterResponse{

    @SerializedName("data")
    private CompanyLoginResponse companyRegisterResponseContent;

    public LoginResponseContent getCompanyRegisterResponseContent() {
        return companyRegisterResponseContent;
    }
}

