package dp.com.amarapp.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import java.util.List;

import dp.com.amarapp.model.pojo.CompanyProject;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.view.activity.DetailedProjectActivity;

public class GridItemProjectViewModel {
    private CompanyProject project;
    private Context context;

    public GridItemProjectViewModel(CompanyProject project, Context context) {
        this.project = project;
        this.context = context;
        System.out.println("project data in grid item view model:"+project.getName()+"\n"+project.getImages());
    }

    public void setProject(CompanyProject project){
        this.project=project;
    }

    public String getImage(){
        return project.getImages().get(0);
    }

    public String getName(){
        return project.getName();
    }

    public void detailedProject(View view){
        Intent intent=new Intent(context, DetailedProjectActivity.class);
        intent.putExtra(ConfigurationFile.IntentConstants.PROJECTINFO,project);
        context.startActivity(intent);
    }

}
