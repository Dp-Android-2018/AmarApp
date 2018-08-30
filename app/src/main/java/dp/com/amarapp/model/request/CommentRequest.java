package dp.com.amarapp.model.request;

import com.google.gson.annotations.SerializedName;

public class CommentRequest {
    @SerializedName("company_id")
    private int company_id;

    @SerializedName("comment")
    private String comment;

    @SerializedName("rating")
    private float rating;

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getCompany_id() {
        return company_id;
    }

    public String getComment() {
        return comment;
    }

    public float getRating() {
        return rating;
    }
}
