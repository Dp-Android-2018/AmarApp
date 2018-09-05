package dp.com.amarapp.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableInt;
import android.databinding.ObservableList;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import dp.com.amarapp.R;
import dp.com.amarapp.model.pojo.CompanyComments;
import dp.com.amarapp.model.request.CommentRequest;
import dp.com.amarapp.model.response.CompanyCommentsResponse;
import dp.com.amarapp.model.response.CompanyLoginResponse;
import dp.com.amarapp.model.response.DefaultResponse;
import dp.com.amarapp.network.ApiClient;
import dp.com.amarapp.network.EndPoints;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.utils.CustomUtils;
import dp.com.amarapp.utils.NetWorkConnection;
import dp.com.amarapp.view.activity.CompanyInformationActivity;
import dp.com.amarapp.view.activity.CompanyProjectsActivity;
import dp.com.amarapp.view.activity.CompanyWorkDaysActivity;
import dp.com.amarapp.view.callback.BaseInterface;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class CompanyDetailedViewModel extends Observable{
    private Context context;
    private CompanyLoginResponse companyInfo;
    private String token="Bearer ";
    public ObservableList<CompanyComments> commentsList;
    private BaseInterface callback;
    public ObservableInt visability;
    public ObservableList<String> images;
    AlertDialog dialog;
    public CompanyDetailedViewModel(Context context,BaseInterface callback) {
        this.context = context;
        this.callback=callback;
        companyInfo=(CompanyLoginResponse)((Activity)context).getIntent().getSerializableExtra(ConfigurationFile.IntentConstants.COMPANYITEMINFO);
        token+= CustomUtils.getInstance().getSaveUserObject(context).getToken();
        getCommentsContent();
        commentsList=new ObservableArrayList<>();
        visability=new ObservableInt();
        if(CustomUtils.getInstance().getSaveUserObject(context).getRole().equals("company"))
            visability.set(View.GONE);
        images=new ObservableArrayList<>();
        setImages();
    }

    public String getTitle(){
        if (companyInfo.getName()!=null) {
            System.out.println("Responses Size Name Item detailed :" + companyInfo.getName());
            return companyInfo.getName();
        }else return "";
    }
    public String getType(){
        if (companyInfo.getCategory().getName()!=null) {
            return companyInfo.getCategory().getName();
        }else return "";
    }
    public String getCity(){
        if (companyInfo.getCity()!=null) {
            System.out.println("Responses Size City Item :" + companyInfo.getCity().getName());
            return companyInfo.getCity().getName();
        }else
            return "";
    }
    public String getWatchs()
    {
        System.out.println("Responses Size Views Item :"+companyInfo.getViews());
        return String.valueOf(companyInfo.getViews());
    }
    public String getComments(){
        return String.valueOf(companyInfo.getCommentsCount());
    }
    public String getDescription(){
        if(companyInfo.getDescription()!=null) {
            return companyInfo.getDescription();
        }else return "";
    }
    public String getRate(){

        return String.valueOf(companyInfo.getRate());
    }
    public String  getPics(){
        return String.valueOf(companyInfo.getMetaData().getImages()!=null?companyInfo.getMetaData().getImages().size():0);
    }
    public void setImages() {
        if (companyInfo.getMetaData().getImages() != null)
             images.addAll(companyInfo.getMetaData().getImages());
    }


    public void companyInformation(View view){
        Intent intent=new Intent(context, CompanyInformationActivity.class);
        intent.putExtra(ConfigurationFile.IntentConstants.COMPANYITEMINFO,companyInfo);
        context.startActivity(intent);
    }
    public void companyProjects(View view){
        Intent i=new Intent(context, CompanyProjectsActivity.class);
        i.putExtra(ConfigurationFile.IntentConstants.COMPANYID,companyInfo.getId());
        i.putExtra(ConfigurationFile.IntentConstants.COMPANYTOKEN,companyInfo.getToken());
        context.startActivity(i);
    }

    public void companyWorkDays(View view){
        //System.out.println("work days size on detailed view Model :"+companyInfo.getWorkDays().size());
        Intent i=new Intent(context,CompanyWorkDaysActivity.class);
        i.putExtra(ConfigurationFile.IntentConstants.COMPANYITEMINFO,companyInfo);
        context.startActivity(i);
    }

    public void getCommentsContent(){
        if(NetWorkConnection.isConnectingToInternet(context)){
            ApiClient.getClient().create(EndPoints.class).companyComment(ConfigurationFile.Constants.API_KEY,
                    ConfigurationFile.Constants.CONTENT_TYPE,ConfigurationFile.Constants.CONTENT_TYPE,token,companyInfo.getId())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(companyCommentsResponseResponse -> {
                        if(companyCommentsResponseResponse.code()==ConfigurationFile.Constants.SUCCESS_CODE_second){
                            commentsList.clear();
                            commentsList.addAll(companyCommentsResponseResponse.body().getComments());
                        }
                        System.out.println("comment data of comments : " +commentsList.size());
                        System.out.println("comment code is "+companyCommentsResponseResponse.code());
                        System.out.println("comment token :"+token);

                    }, throwable -> System.out.println("ERROR comments : "+throwable));
        }
    }

    public void writeComment(View view){
        if(CustomUtils.getInstance().getSaveUserObject(context).getRole().equals("client"))
            dialog();
        else
            callback.updateUi(ConfigurationFile.Constants.YOU_ARE_COMPANY);

    }


    public void dialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View alertLayout = View.inflate(context, R.layout.comment_alert_dialog,null);
        builder.setView(alertLayout);
        builder.setCancelable(true);
        EditText comment=alertLayout.findViewById(R.id.et_comment);
        RatingBar ratingBar=alertLayout.findViewById(R.id.rb_rate);
        Button addComment=alertLayout.findViewById(R.id.bt_comment);
        addComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCommentRequest(comment.getText().toString(),ratingBar.getRating());
            }
        });
        dialog=builder.create();
        dialog.show();
    }

    public void addCommentRequest(String comment,float rate){
        System.out.println("Comment : "+comment);
        System.out.println("Rate : "+rate);
        if(comment==null||comment.equals("")||rate==0){
            callback.updateUi(ConfigurationFile.Constants.FILL_ALL_DATA_ERROR);
        }
        CommentRequest commentRequest=new CommentRequest();
        commentRequest.setComment(comment);
        commentRequest.setRating(rate);
        commentRequest.setCompany_id(companyInfo.getId());
        if(NetWorkConnection.isConnectingToInternet(context)){
            CustomUtils.getInstance().showProgressDialog(context);
            ApiClient.getClient().create(EndPoints.class).createComment(ConfigurationFile.Constants.API_KEY,
                    ConfigurationFile.Constants.CONTENT_TYPE,ConfigurationFile.Constants.CONTENT_TYPE,token,commentRequest)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Response<DefaultResponse>>() {
                        @Override
                        public void accept(Response<DefaultResponse> defaultResponseResponse) throws Exception {
                            CustomUtils.getInstance().cancelDialog();
                            System.out.println("Code in add comment : "+defaultResponseResponse.code());
                            if(defaultResponseResponse.code()==ConfigurationFile.Constants.SUCCESS_CODE){
                                callback.updateUi(ConfigurationFile.Constants.SUCCESS_CODE);
                                dialog.dismiss();
                                getCommentsContent();
                            }else if(defaultResponseResponse.code()==ConfigurationFile.Constants.ALREADY_ACTIVATED){
                                callback.updateUi(ConfigurationFile.Constants.ALREADY_ACTIVATED);
                                dialog.dismiss();
                            }
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            System.out.println("ERROR AD COmment "+throwable.getMessage());

                        }
                    });
        }else {
            callback.updateUi(ConfigurationFile.Constants.NO_INTERNET_CONNECTION_CODE);
        }
    }

}
