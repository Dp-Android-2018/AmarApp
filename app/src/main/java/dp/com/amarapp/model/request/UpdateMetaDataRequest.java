package dp.com.amarapp.model.request;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UpdateMetaDataRequest {
    @SerializedName("website")
    private String website;

    @SerializedName("license_image")
    private String licenseImageUrl;

    @SerializedName("images")
    private List<String> imgsUrl;

    @SerializedName("logo")
    private String logoUrl;

    @SerializedName("social_networks")
    private List<String> social;

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("city_id")
    private int cityId;

    @SerializedName("country_id")
    private int countryId;

    @SerializedName("specialization_id")
    private int specializationId;

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setLicenseImageUrl(String licenseImageUrl) {
        this.licenseImageUrl = licenseImageUrl;
    }

    public void setImgsUrl(List<String> imgsUrl) {
        this.imgsUrl = imgsUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public void setSocial(List<String> social) {
        this.social = social;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public void setSpecializationId(int specializationId) {
        this.specializationId = specializationId;
    }


    public String getWebsite() {
        return website;
    }

    public String getLicenseImageUrl() {
        return licenseImageUrl;
    }

    public List<String> getImgsUrl() {
        return imgsUrl;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public List<String> getSocial() {
        return social;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getCityId() {
        return cityId;
    }

    public int getCountryId() {
        return countryId;
    }

    public int getSpecializationId() {
        return specializationId;
    }
}
