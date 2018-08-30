package dp.com.amarapp.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CategoriesContent implements Serializable{
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("specializations")
    private List<Specialization> specializations;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecializations(List<Specialization> specializations) {
        this.specializations = specializations;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Specialization> getSpecializations() {
        return specializations;
    }
}
