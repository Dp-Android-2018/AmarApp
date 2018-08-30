package dp.com.amarapp.model.response;

import com.google.gson.annotations.SerializedName;

import dp.com.amarapp.model.pojo.AdvertContent;

public class CreateAdvertResponse {
    @SerializedName("data")
    private AdvertContent advert;

    public AdvertContent getAdvert() {
        return advert;
    }
}
