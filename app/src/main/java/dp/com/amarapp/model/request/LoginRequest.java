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


    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
