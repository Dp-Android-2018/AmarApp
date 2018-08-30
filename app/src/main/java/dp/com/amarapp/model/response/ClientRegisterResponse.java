package dp.com.amarapp.model.response;

import com.google.gson.annotations.SerializedName;

import dp.com.amarapp.model.pojo.LoginResponseContent;

/**
 * Created by DELL on 14/07/2018.
 */

public class ClientRegisterResponse{

    @SerializedName("data")
    private LoginResponseContent response;

    @SerializedName("message")
    private String message;

    public String getMessage() {
        return message;
    }

    public LoginResponseContent getResponse() {
        return response;
    }
}
