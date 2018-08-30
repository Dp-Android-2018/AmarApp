package dp.com.amarapp.viewmodel;

import android.content.Context;
import android.databinding.ObservableFloat;

import dp.com.amarapp.model.pojo.AdvertContent;
import dp.com.amarapp.model.pojo.CompanyComments;

public class CommentItemViewModel {
    private CompanyComments comment;
    private Context context;
    public ObservableFloat rate;
    public CommentItemViewModel(Context context,CompanyComments comment) {
        this.comment = comment;
        rate=new ObservableFloat();
        setRate();
    }
    public void setComment(CompanyComments comment) {
        this.comment = comment;
    }

    public String getClient(){
        return comment.getClient().getName();
    }
    public String getComment(){
        return comment.getComment();
    }
    public void setRate(){
        rate.set(comment.getRating());
    }
}
