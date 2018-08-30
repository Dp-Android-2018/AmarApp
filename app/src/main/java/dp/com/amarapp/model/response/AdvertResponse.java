package dp.com.amarapp.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import dp.com.amarapp.model.pojo.AdvertContent;

public class AdvertResponse {

    @SerializedName("data")
    private List<AdvertContent> advertContent;

    public List<AdvertContent> getAdvertContent() {
        return advertContent;
    }
}
