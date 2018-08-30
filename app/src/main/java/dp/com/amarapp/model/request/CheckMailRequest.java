package dp.com.amarapp.model.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by DELL on 24/07/2018.
 */

public class CheckMailRequest {

    @SerializedName("email")
    private String email;

    public CheckMailRequest(String email) {
        this.email = email;
    }
}
