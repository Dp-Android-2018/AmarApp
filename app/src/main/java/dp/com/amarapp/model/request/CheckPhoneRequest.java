package dp.com.amarapp.model.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by DELL on 24/07/2018.
 */

public class CheckPhoneRequest {

    @SerializedName("phone")
    private String phone;

    public CheckPhoneRequest(String phone) {
        this.phone = phone;
    }
}
