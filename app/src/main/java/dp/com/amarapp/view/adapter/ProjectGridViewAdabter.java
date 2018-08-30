package dp.com.amarapp.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import dp.com.amarapp.R;
//import dp.com.amarapp.databinding.ProjectGridItemBinding;
import dp.com.amarapp.model.pojo.CompanyProject;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.view.activity.DetailedProjectActivity;

public class ProjectGridViewAdabter extends BaseAdapter{
    private ObservableList<CompanyProject> projects;
    Context context;

    public ProjectGridViewAdabter(ObservableList<CompanyProject> projects) {
        this.projects = projects;
    }


    @Override
    public int getCount() {
        System.out.println("Countat :"+projects.size());
        if(projects!=null)
            return projects.size();
        else
            return 0;
    }

    @Override
    public Object getItem(int position) {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        context=parent.getContext();
        System.out.println("Size in adapter 2"+projects.size());
       View v= View.inflate(context,R.layout.project_grid_item,null);
        ImageView imageView=v.findViewById(R.id.image);
        TextView title=v.findViewById(R.id.title);
        System.out.println("im age url : "+projects.get(position).getImages().get(0));
        System.out.println("project title: "+projects.get(position).getName());
        Picasso.with(context).load(projects.get(position).getImages().get(0)).
                placeholder(parent.getContext().getResources().getDrawable(R.mipmap.logo)).into(imageView);
        title.setText(projects.get(position).getName());
        v.setOnClickListener(v1 -> detailedProject(projects.get(position)));
        return v;
    }

    public void detailedProject(CompanyProject project){
        System.out.println("projet name in intent on click : "+project.getName());
        Intent intent=new Intent(context, DetailedProjectActivity.class);
        intent.putExtra(ConfigurationFile.IntentConstants.PROJECTINFO,project);
        context.startActivity(intent);
    }


}
