package dp.com.amarapp.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CountryResponse {

    @SerializedName("data")
    private List<Country> countries;

    public List<Country> getCountries() {
        return countries;
    }
}
