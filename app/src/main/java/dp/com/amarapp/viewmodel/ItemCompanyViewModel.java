package dp.com.amarapp.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import java.util.Observable;

import dp.com.amarapp.model.response.CompanyLoginResponse;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.utils.CustomUtils;
import dp.com.amarapp.view.activity.CompanyDetailedActivity;

public class ItemCompanyViewModel extends Observable {

    private CompanyLoginResponse companyItem;
    private Context context;

    public ItemCompanyViewModel(CompanyLoginResponse companyItem, Context context) {
        this.companyItem = companyItem;
        this.context = context;
    }

    public void setCompanyItem(CompanyLoginResponse companyItem){
        this.companyItem=companyItem;
    }

    public String getTitle(){
        if (companyItem.getName()!=null) {
           // System.out.println("Responses Size Name Item :" + companyItem.getName());
            return companyItem.getName();
        }else return "";
    }
    public String getType(){
        if (companyItem.getCategory().getName()!=null) {
            return companyItem.getCategory().getName();
        }else return "";
    }
    public String getImage(){
        return companyItem.getMetaData().getLogo();
    }
    public String getCity(){
        if (companyItem.getCity()!=null) {
           // System.out.println("Responses Size City Item :" + companyItem.getCity().getName());
            return companyItem.getCity().getName();
        }else return "";
    }
    public String getWatchs()
    {
        //System.out.println("Responses Size Views Item :"+companyItem.getViews());
        return String.valueOf(companyItem.getViews());
    }
    public String getComments(){
        return String.valueOf(companyItem.getCommentsCount());
    }
    public String getDescription(){
        if(companyItem.getDescription()!=null) {
            return companyItem.getDescription();
        }else return "";
    }
    public String getRate(){
                return String.valueOf(companyItem.getRate());
    }
    public void companyDetail(View view){
        if(CustomUtils.getInstance().getSaveUserObject(context)!=null) {
            Intent intent = new Intent(context, CompanyDetailedActivity.class);
            intent.putExtra(ConfigurationFile.IntentConstants.COMPANYITEMINFO, companyItem);
            context.startActivity(intent);
        }else{
            CustomUtils.getInstance().alertDialog((Activity)context);
        }
    }



}
