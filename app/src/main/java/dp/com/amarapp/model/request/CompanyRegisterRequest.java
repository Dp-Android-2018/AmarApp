package dp.com.amarapp.model.request;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by DELL on 24/07/2018.
 */

public class CompanyRegisterRequest implements Serializable {

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

    @SerializedName("description")
    private String description;

    @SerializedName("city_id")
    private int city;

    @SerializedName("specialization_id")
    private int specializationId;

    @SerializedName("logo")
    private String logoUrl;

    @SerializedName("website")
    private String websiteUrl;

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPassConf(String passConf) {
        this.passConf = passConf;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCityId(int cityId) {
        this.city = cityId;
    }

    public void setSpecializationId(int specializationId) {
        this.specializationId = specializationId;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPassConf() {
        return passConf;
    }

    public String getPhone() {
        return phone;
    }

    public String getDescription() {
        return description;
    }

    public int getCity() {
        return city;
    }

    public int getSpecializationId() {
        return specializationId;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }
}
