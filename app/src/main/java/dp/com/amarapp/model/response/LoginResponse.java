package dp.com.amarapp.model.response;

import com.google.gson.annotations.SerializedName;

import dp.com.amarapp.model.pojo.LoginResponseContent;

public class LoginResponse{
    @SerializedName("data")
    private CompanyLoginResponse loginResponseContent;

    public LoginResponseContent getLoginResponseContent() {
        return loginResponseContent;
    }
}

