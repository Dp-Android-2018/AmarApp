package dp.com.amarapp.model.request;

import com.google.gson.annotations.SerializedName;

public class ChangePasswordRequest {

    @SerializedName("old_password")
    private String oldPassword;

    @SerializedName("password")
    private String newPassword;

    @SerializedName("password_confirmation")
    private String confNewPassword;

    public ChangePasswordRequest(String oldPassword, String newPassword, String confNewPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.confNewPassword = confNewPassword;
    }
}
