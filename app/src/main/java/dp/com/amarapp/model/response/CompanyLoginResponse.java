package dp.com.amarapp.model.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import dp.com.amarapp.model.pojo.Category;
import dp.com.amarapp.model.pojo.LoginResponseContent;
import dp.com.amarapp.model.pojo.MetaData;
import dp.com.amarapp.model.pojo.Specialization;
import dp.com.amarapp.model.pojo.WorkDay;

/**
 * Created by DELL on 25/07/2018.
 */

public class CompanyLoginResponse extends LoginResponseContent implements Serializable {

    public CompanyLoginResponse() {
        super();
    }

    @SerializedName("meta_data")
    private MetaData metaData;

    @SerializedName("description")
    private String description;

    public void setDescription(String description) {
        this.description = description;
    }

    @SerializedName("category")
    private Category category;

    @SerializedName("specialization")
    private Specialization specialization;

    @SerializedName("average_rating")
    private float rate;

    @SerializedName("comments_count")
    private int commentsCount;

    @SerializedName("views")
    private int views;

    @SerializedName("work_days")
    private List<WorkDay> workDays;

    public List<WorkDay> getWorkDays() {
        return workDays;
    }

    public void setWorkDays(List<WorkDay> workDays) {
        this.workDays = workDays;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public Category getCategory() {
        return category;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public float getRate() {
        return rate;
    }



    public int getCommentsCount() {
        return commentsCount;
    }

    public int getViews() {
        return views;
    }

    public String getDescription() {
        return description;
    }
}
