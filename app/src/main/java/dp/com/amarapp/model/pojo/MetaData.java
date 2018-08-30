package dp.com.amarapp.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DELL on 18/07/2018.
 */

public class MetaData implements Serializable{

    @SerializedName("phone")
    private String phone;

    @SerializedName("website")
    private String website;

    @SerializedName("images")
    private List<String> images;

    @SerializedName("social_networks")
    private List<String> social;

    @SerializedName("logo")
    private String logo;

    @SerializedName("license_image")
    private String license_image;

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public void setSocial(List<String> social) {
        this.social = social;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setLicense_image(String license_image) {
        this.license_image = license_image;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }

    public List<String> getImages() {
        return images;
    }

    public List<String> getSocial() {
        return social;
    }

    public String getLogo() {
        return logo;
    }

    public String getLicense_image() {
        return license_image;
    }
}
