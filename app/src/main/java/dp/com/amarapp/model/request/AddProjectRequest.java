package dp.com.amarapp.model.request;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AddProjectRequest {
    @SerializedName("name")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("images")
    private List<String> images;

    public AddProjectRequest(String title, String description, List<String> images) {
        this.title = title;
        this.description = description;
        this.images = images;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
