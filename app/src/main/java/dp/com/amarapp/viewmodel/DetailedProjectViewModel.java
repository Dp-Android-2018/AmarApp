package dp.com.amarapp.viewmodel;

import android.app.Activity;
import android.content.Context;

import dp.com.amarapp.model.pojo.CompanyProject;
import dp.com.amarapp.utils.ConfigurationFile;

public class DetailedProjectViewModel {
    CompanyProject project=new CompanyProject();
    Context context;

    public DetailedProjectViewModel(Context context) {
        this.context = context;
        project=(CompanyProject)((Activity)context).getIntent().getSerializableExtra(ConfigurationFile.IntentConstants.PROJECTINFO);
        System.out.println("projet name in detailed activity :"+project.getName());
    }

    public String getName(){
        if(project.getName()!=null)
        return project.getName();
        else
            return "";
    }

    public String getAddress(){
        if(project.getAddress()!=null)
            return project.getAddress();
        else
            return "";
    }

    public String getDescription(){
        if(project.getDescription()!=null)
            return project.getDescription();
        else
            return "";
    }
}
