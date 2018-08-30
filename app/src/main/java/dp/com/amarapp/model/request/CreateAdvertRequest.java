package dp.com.amarapp.model.request;

import android.content.Intent;

import com.google.gson.annotations.SerializedName;

/**
 * Created by DELL on 17/07/2018.
 */

public class CreateAdvertRequest {

    @SerializedName("title")
    private String title;

    @SerializedName("content")
    private String content;

    @SerializedName("city_id")
    private int cityId;

    @SerializedName("duration")
    private String duration;

    @SerializedName("image")
    private String image_link;


    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setImage_link(String image_link) {
        this.image_link = image_link;
    }

    public String getDuration() {
        return duration;
    }
}
