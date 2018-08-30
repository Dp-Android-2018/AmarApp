package dp.com.amarapp.model.response;

import com.google.gson.annotations.SerializedName;

public class DefaultResponse {
    @SerializedName("message")
    private String message;

    public String getMessage() {
        return message;
    }
}
