package dp.com.amarapp.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableList;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import dp.com.amarapp.R;
import dp.com.amarapp.model.response.CompanyLoginResponse;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.utils.CustomUtils;
import dp.com.amarapp.view.activity.CompanyDetailedActivity;

public class CompaniesGridAdapter extends BaseAdapter {
    private ObservableList<CompanyLoginResponse> companies;
    private Context context;

    public CompaniesGridAdapter(ObservableList<CompanyLoginResponse> companies) {
        this.companies = companies;
        System.out.println("Companies grid Size adapter: "+companies.size());
    }

    @Override
    public int getCount() {
        return companies.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        context=parent.getContext();
        View view=View.inflate(context, R.layout.company_grid_item,null);
        ImageView imageView=view.findViewById(R.id.image);
        TextView title=view.findViewById(R.id.company_title);
        TextView type=view.findViewById(R.id.company_type);
        TextView city=view.findViewById(R.id.company_city);
        TextView commentsNom=view.findViewById(R.id.tv_no_comment);
        TextView watchsNom=view.findViewById(R.id.tv_no_watch);
        TextView description=view.findViewById(R.id.description_company);
        TextView rate=view.findViewById(R.id.rate_value);
        if(companies.get(position).getMetaData().getImages()!=null) {
            Picasso.with(context).load(companies.get(position).getMetaData().getLogo())
                    .placeholder(R.drawable.ic_launcher_background).into(imageView);
        }
        if(companies.get(position).getName()!=null) {
            title.setText(companies.get(position).getName());
        }else{
            title.setText("No title");
        }
        if(companies.get(position).getCategory()!=null) {
            type.setText(companies.get(position).getCategory().getName());
        }else{
            type.setText("no type");
        }
        if(companies.get(position).getCity()!=null) {
            city.setText(companies.get(position).getCity().getName());
        }else{
            city.setText("no cityName");
        }
            commentsNom.setText(String.valueOf(companies.get(position).getCommentsCount()));
            watchsNom.setText(String.valueOf(companies.get(position).getViews()));
            if(companies.get(position).getDescription()!=null){
                description.setText(companies.get(position).getDescription());
            }else{
                description.setText("no description");
            }
            rate.setText(String.valueOf(companies.get(position).getRate()));
            view.setOnClickListener(v -> companyDetail(position));
        return view;
    }

    public void companyDetail(int postion){
        if(CustomUtils.getInstance().getSaveUserObject(context)!=null) {
            Intent intent = new Intent(context, CompanyDetailedActivity.class);
            intent.putExtra(ConfigurationFile.IntentConstants.COMPANYITEMINFO,companies.get(postion));
            context.startActivity(intent);
        }else{
            CustomUtils.getInstance().alertDialog(context);
        }
    }
}
