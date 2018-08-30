package dp.com.amarapp.model.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by DELL on 17/07/2018.
 */

public class CompanyComments {

    @SerializedName("id")
    private int id;

    @SerializedName("client")
    private Client client;

    @SerializedName("comment")
    private String comment;

    @SerializedName("rating")
    private float rating;

    public void setId(int id) {
        this.id = id;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public String getComment() {
        return comment;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public class Client{

        @SerializedName("id")
        private int client_id;

        @SerializedName("name")
        private String name;

        public void setClient_id(int client_id) {
            this.client_id = client_id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getClient_id() {
            return client_id;
        }

        public String getName() {
            return name;
        }


    }
}
