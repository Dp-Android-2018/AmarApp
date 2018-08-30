package dp.com.amarapp.model.request;

import com.google.gson.annotations.SerializedName;

public class CodeRequest {

    @SerializedName("code")
    private String code;

    public CodeRequest(String code) {
        this.code = code;
    }
}
