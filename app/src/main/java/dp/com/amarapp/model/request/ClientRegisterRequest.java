package dp.com.amarapp.model.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by DELL on 15/07/2018.
 */

public class ClientRegisterRequest {

    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    @SerializedName("password_confirmation")
    private String passConf;

    @SerializedName("phone")
    private String phone;
    @SerializedName("device_token")
    private String deviceToken;

    @SerializedName("city_id")
    private int cityId;

    @SerializedName("country_id")
    private int countryId;

    public ClientRegisterRequest(String name, String email, String password, String pass_conf, String phone, int cityId, int countryId) {
        this.name=name;
        this.email = email;
        this.password = password;
        this.passConf = pass_conf;
        this.phone = phone;
        this.cityId = cityId;
        this.countryId = countryId;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }
}
