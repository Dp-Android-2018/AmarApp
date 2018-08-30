package dp.com.amarapp.viewmodel;

import android.content.Context;

public class ImageListItemViewModel {

    public String imageLink;
    Context context;

    public ImageListItemViewModel(Context context,String imageLink) {
        this.imageLink = imageLink;
        this.context=context;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

}
