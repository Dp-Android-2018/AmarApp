package dp.com.amarapp.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import dp.com.amarapp.model.pojo.AdvertContent;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.view.activity.AdvertDetailActivity;

public class ItemAdvertViewModel {
    private Context context;
    private AdvertContent advert;
    public ItemAdvertViewModel(Context context,AdvertContent advert) {
        this.context = context;
        this.advert=advert;
    }

    public void setAdvert(AdvertContent advert) {
        this.advert = advert;
    }

    public String getTittle(){
        return advert.getTitle();
    }
    public String getContent(){
        return advert.getContent();
    }
    public String getImage(){
        return advert.getImage();
    }

    public void detailedAdvert(View view){
        Intent intent=new Intent(context, AdvertDetailActivity.class);
        intent.putExtra(ConfigurationFile.IntentConstants.ADVERTINFO,advert);
        context.startActivity(intent);


    }
}
