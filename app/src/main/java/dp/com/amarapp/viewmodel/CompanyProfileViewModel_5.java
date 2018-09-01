package dp.com.amarapp.viewmodel;

import android.app.Activity;
import android.databinding.ObservableField;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dp.com.amarapp.R;
import dp.com.amarapp.model.request.UpdateMetaDataRequest;
import dp.com.amarapp.model.response.CompanyLoginResponse;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.utils.CustomUtils;
import dp.com.amarapp.utils.UpdateMetadata;
import dp.com.amarapp.utils.ValidationUtils;

public class CompanyProfileViewModel_5 {
    Activity activity;
    private CompanyLoginResponse company;
    public ObservableField<String> facebook;
    public ObservableField<String> twitter;
    public ObservableField<String> instgram;
    private List<String> social;
    UpdateMetaDataRequest request;

    public CompanyProfileViewModel_5(Activity activity) {
        this.activity = activity;
        company= CustomUtils.getInstance().getSaveUserObject(activity);
        initVariables();
    }

    public void initVariables(){
        request=new UpdateMetaDataRequest();
        social=new ArrayList<>();
        facebook=new ObservableField<>();
        facebook.set(company.getMetaData().getSocial()!=null?company.getMetaData().getSocial().get(0):"");
        twitter=new ObservableField<>();
        twitter.set(company.getMetaData().getSocial()!=null?company.getMetaData().getSocial().get(1):"");
        instgram=new ObservableField<>();
        instgram.set(company.getMetaData().getSocial()!=null?company.getMetaData().getSocial().get(2):"");
    }

    public void setRequest(){
        if(ValidationUtils.isEmpty(facebook.get()))
            facebook.set("");
        if (ValidationUtils.isEmpty(twitter.get()))
            twitter.set("");
        if (ValidationUtils.isEmpty(instgram.get()))
            instgram.set("");

        social.add(facebook.get());
        social.add(twitter.get());
        social.add(instgram.get());
        request.setSocial(social);
        request.setSpecializationId(company.getSpecialization().getId());
        request.setCountryId(company.getCountry().getId());
        request.setCityId(company.getCity().getId());
    }

    public void save(View view){
        setRequest();
        UpdateMetadata updat=new UpdateMetadata(request,activity,"Bearer "+company.getToken(),
                ConfigurationFile.FragmentID.FRAGMENT5);
        updat.call();
    }

}