package dp.com.amarapp.model.pojo;

import com.google.gson.annotations.SerializedName;

public class SocialNetwork {

    @SerializedName("facebook")
    private String facebook;

    @SerializedName("twitter")
    private String twitter;

    @SerializedName("instagram")
    private String instagram;

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }
}
