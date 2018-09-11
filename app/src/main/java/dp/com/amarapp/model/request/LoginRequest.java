package dp.com.amarapp.model.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by DELL on 14/07/2018.
 */

public class LoginRequest {
    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    @SerializedName("device_token")
    private String deviceToken;


    public LoginRequest(String email, String password,String deviceToken) {
        this.email = email;
        this.password = password;
        this.deviceToken=deviceToken;
        System.out.println("Login Request Token :"+deviceToken);
    }

}
