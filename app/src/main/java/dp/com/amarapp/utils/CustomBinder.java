package dp.com.amarapp.utils;


import android.databinding.BindingAdapter;
import android.databinding.ObservableList;
import android.graphics.Bitmap;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.RecyclerView;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import dp.com.amarapp.R;
import dp.com.amarapp.model.pojo.AdvertContent;
import dp.com.amarapp.model.pojo.CompanyComments;
import dp.com.amarapp.model.pojo.CompanyProject;
import dp.com.amarapp.model.pojo.BaseObjParentDay;
import dp.com.amarapp.model.response.CompanyLoginResponse;
import dp.com.amarapp.view.adapter.AddProjectAdapter;
import dp.com.amarapp.view.adapter.AdvertsAdapter;
import dp.com.amarapp.view.adapter.CommentsAdapter;
import dp.com.amarapp.view.adapter.CompaniesGridAdapter;
import dp.com.amarapp.view.adapter.ProjectGridViewAdabter;
import dp.com.amarapp.view.adapter.SearchCompanyAdapter;
import dp.com.amarapp.view.adapter.SetWorkingDaysAdapter;
import dp.com.amarapp.view.adapter.SliderAdapter;


/**
 * Created by DELL on 20/03/2018.
 */

public class CustomBinder {

    @BindingAdapter({"bind:companyInfo"})
    public static void setRecyclerCompanies(RecyclerView view, ObservableList<CompanyLoginResponse> searchResponse) {

        SearchCompanyAdapter adapter=new SearchCompanyAdapter(searchResponse);
        view.setAdapter(adapter);
    }
    @BindingAdapter({"bind:gridcompanies"})
    public static void setGridCompanies(GridView view, ObservableList<CompanyLoginResponse> companies){

        CompaniesGridAdapter adapter=new CompaniesGridAdapter(companies);
        view.setAdapter(adapter);
        System.out.println("Companies grid Size binder: "+companies.size());
    }
    @BindingAdapter({"bind:projectsinfo"})
    public static void setGridProjects(GridView view, ObservableList<CompanyProject> projects) {

        ProjectGridViewAdabter adapter=new ProjectGridViewAdabter(projects);
        if (projects!=null)
            System.out.println("size of list in custom binder"+projects.size());
        else
            System.out.println("project is null in custom binder :(");
        view.setAdapter(adapter);
    }
    @BindingAdapter({"bind:advertinfo"})
    public static void setRecyclerAdverts(RecyclerView view,ObservableList<AdvertContent> adverts) {

        AdvertsAdapter adapter=new AdvertsAdapter(adverts);
        if (adverts!=null)
            System.out.println("size of list in custom binder"+adverts.size());
        else
            System.out.println("project is null in custom binder :(");
        view.setAdapter(adapter);
    }

    @BindingAdapter({"bind:comments"})
    public static void setRecyclerComments(RecyclerView view,ObservableList<CompanyComments> comments) {

        CommentsAdapter adapter=new CommentsAdapter(comments);
        if (comments!=null)
            System.out.println("size of comment list in custom binder "+comments.size());
        else
            System.out.println("project is null in custom binder :(");
        view.setAdapter(adapter);
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void setImageUrl(ImageView imageView, String url){
        if (url!=null && !url.equals(""))
        Picasso.with(imageView.getContext()).load(url).placeholder(R.mipmap.logo).into(imageView);
    }

    @BindingAdapter({"bind:bitmap"})
    public static void setbitmap(ImageView imageView, Bitmap bitmap){
        if (bitmap!=null)
            imageView.setImageBitmap(bitmap);
    }
    @BindingAdapter({"bind:addprojectrecycler"})
    public static void setAddprojectAdapter(RecyclerView view,ObservableList<String>imageLinks) {
        AddProjectAdapter adapter=new AddProjectAdapter(imageLinks);
        if (imageLinks!=null)
            System.out.println("size of list in custom binder"+imageLinks.size());
        else
            System.out.println("project is null in custom binder :(");
        view.setAdapter(adapter);
    }

    @BindingAdapter({"bind:slider"})
    public static void setSliderAdapter(RecyclerView view,ObservableList<String>imageLinks) {
        SliderAdapter adapter=new SliderAdapter(imageLinks);
        if (imageLinks!=null)
            System.out.println("size of list in custom binder"+imageLinks.size());
        else
            System.out.println("project is null in custom binder :(");
        view.setAdapter(adapter);
    }

    @BindingAdapter({"bind:workday"})
    public static void workDayAdapter(RecyclerView view, ArrayList<BaseObjParentDay> parentDays) {
        SetWorkingDaysAdapter adapter=new SetWorkingDaysAdapter(parentDays);
        view.setAdapter(adapter);
    }


    @BindingAdapter({"bind:navigationItem"})
    public static void navigationEvent(NavigationView navigationView, NavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener) {
        navigationView.setNavigationItemSelectedListener(onNavigationItemSelectedListener);
    }

    @BindingAdapter({"bind:gridListener"})
    public static void ongridListener(GridView view,GridView.OnScrollListener listener){
        view.setOnScrollListener(listener);
    }

    @BindingAdapter({"bind:recyclerListener"})
    public static void onrecyclerListener(RecyclerView view,RecyclerView.OnScrollListener listener){
        view.addOnScrollListener(listener);
    }



  /*  @BindingAdapter("bind:navigationItem")
    public static void navigationEvent(NavigationView navigationView, NavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener) {
        navigationView.setNavigationItemSelectedListener(onNavigationItemSelectedListener);
    }


    @BindingAdapter("bind:toolbarBackground")
    public static void setToolBarBackground(android.support.v7.widget.Toolbar toolBarBackground,int color) {
        toolBarBackground.setBackgroundColor(MyApplication.getAppContext().getResources().getColor(color));
    }


    @BindingAdapter("bind:imageConv")
    public static void setImageConv(ImageView imageView, String url) {
        try {

            Picasso.with(MyApplication.getAppContext()).load(url).fit().into(imageView);
        }catch (IllegalArgumentException ex){ex.printStackTrace();}
    }

    @BindingAdapter("bind:imageBitmap")
    public static void setImageBitmap(ImageView imageView, Bitmap bitmap){
        imageView.setImageBitmap(bitmap);
    }


    @BindingAdapter("bind:circleImageBitmap")
    public static void setcircleImageBitmap(CircleImageView imageView, Bitmap bitmap){
        imageView.setImageBitmap(bitmap);
    }


    @BindingAdapter("bind:conversationDetails")
    public static void setRecyclerConversationDetails(RecyclerView view, ObservableList<Message> list) {
        System.out.println("Adapter Data Fire:"+list.size());
        ConversationDetailAdapter adapter=new ConversationDetailAdapter(list);
        view.setAdapter(adapter);
        view.scrollToPosition(list.size()-1);
    }

    @BindingAdapter("bind:conversationhistory")
    public static void setRecyclerConversation(RecyclerView view,ObservableList<ConversationHistory> list) {
        System.out.println("Adapter Data Fire:"+list.size());
        MyConversationAdapter myConversationAdapter=new MyConversationAdapter(list);
        view.setAdapter(myConversationAdapter);
    }

    @BindingAdapter("bind:errorText")
    public static void setErrorMessage(TextInputLayout view, String errorMessage) {
        view.setError(errorMessage);
    }


    @BindingAdapter("bind:animation")
    public static void onAnimationEnd(LottieAnimationView animationView, Animator.AnimatorListener animatorListener) {
        animationView.addAnimatorListener(animatorListener);
    }


    @BindingAdapter("bind:ontouch")
    public static void onTouch(LottieAnimationView animationView, View.OnTouchListener touchListener) {
        animationView.setOnTouchListener(touchListener);
    }



    @BindingAdapter("bind:setprogress")
    public static void progress(LottieAnimationView animationView,float progress) {
        if (progress==0.3f) {
            System.out.println("Played Lottie 1");
            animationView.setMaxProgress(progress);
            animationView.playAnimation();

        }else if (progress == 1.0f) {
            System.out.println("Played Lottie 2");
            animationView.setMaxProgress(1.0f);
            animationView.resumeAnimation();

        }
   //    animationView.playAnimation();
     //  animationView.setMaxProgress(progress);



    }


    @BindingAdapter("bind:picUrl")
    public static void setImageConv(CircleImageView imageView, String url) {
        Picasso.with(MyApplication.getAppContext()).load(url).fit().into(imageView);
    }*/

}
