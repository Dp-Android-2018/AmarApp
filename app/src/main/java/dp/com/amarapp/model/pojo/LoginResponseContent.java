package dp.com.amarapp.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import dp.com.amarapp.model.response.Country;

/**
 * Created by DELL on 22/07/2018.
 */
public class LoginResponseContent implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    @SerializedName("phone")
    private String phone;

    @SerializedName("role")
    private String role;

    @SerializedName("country")
    private Country country;

    @SerializedName("city")
    private City city;

    @SerializedName("status")
    private String status;

    @SerializedName("confirmed_phone")
    private boolean confirmedPhone;

    @SerializedName("token")
    private String token;

    @SerializedName("device_token")
    private String deviceToken;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setConfirmedPhone(boolean confirmedPhone) {
        this.confirmedPhone = confirmedPhone;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getRole() {
        return role;
    }

    public Country getCountry() {
        return country;
    }

    public City getCity() {
        return city;
    }

    public String getStatus() {
        return status;
    }

    public boolean isConfirmedPhone() {
        return confirmedPhone;
    }

    public String getToken() {
        return token;
    }

    public String getDeviceToken() {
        return deviceToken;
    }
}
