package dp.com.amarapp.viewmodel;

import android.app.Activity;
import android.content.Context;

import dp.com.amarapp.model.pojo.AdvertContent;
import dp.com.amarapp.utils.ConfigurationFile;

public class DetailedAdvertViewModel {
    private AdvertContent advert;
    private Context context;

    public DetailedAdvertViewModel(Context context) {
        this.context = context;
        advert=(AdvertContent)((Activity)context).getIntent().
                getSerializableExtra(ConfigurationFile.IntentConstants.ADVERTINFO);
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
}
