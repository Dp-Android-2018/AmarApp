package dp.com.amarapp.viewmodel;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.ObservableInt;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Toast;

import dp.com.amarapp.model.response.CompanyLoginResponse;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.view.callback.BaseInterface;

public class CompanyInformationViewModel {
    private Context context;
    CompanyLoginResponse companyInfo;
    BaseInterface callback;
    public ObservableInt facClick;
    public ObservableInt twitterClick;
    public ObservableInt instaClick;
    public CompanyInformationViewModel(Context context,BaseInterface callback) {
        this.context = context;
        this.callback=callback;
        companyInfo = (CompanyLoginResponse) ((Activity) context).getIntent().getSerializableExtra(ConfigurationFile.IntentConstants.COMPANYITEMINFO);
        //System.out.println("social size  "+companyInfo.getMetaData().getSocial().size());
       // System.out.println("2:  "+companyInfo.getMetaData().getSocial().get(1));
       // System.out.println("3:  "+companyInfo.getMetaData().getSocial().get(2));
        initVariables();
    }
    public void initVariables(){
        facClick=new ObservableInt(View.GONE);
        twitterClick=new ObservableInt(View.GONE);
        instaClick=new ObservableInt(View.GONE);
        if (companyInfo.getMetaData().getSocial()!=null) {
            if (companyInfo.getMetaData().getSocial().getFacebook() != null) {
                System.out.println("facebook : "+companyInfo.getMetaData().getSocial().getFacebook());
                facClick.set(View.VISIBLE);
            }
            if (companyInfo.getMetaData().getSocial().getTwitter() != null) {
                System.out.println("twitter : "+companyInfo.getMetaData().getSocial().getTwitter());

                twitterClick.set(View.VISIBLE);
            }
            if (companyInfo.getMetaData().getSocial().getInstagram() != null) {
                System.out.println("insta : "+companyInfo.getMetaData().getSocial().getInstagram());

                instaClick.set(View.VISIBLE);
            }
        }

    }

    public String getDescribtion() {
        if (companyInfo.getDescription() != null)
            return companyInfo.getDescription();
        else
            return "No description till now :(";
    }

    public String getPhone() {
        if (companyInfo.getPhone() != null)
            return companyInfo.getPhone();
        else
            return "No phone:(";
    }

    public String getMail() {
        if (companyInfo.getEmail() != null)
            return companyInfo.getEmail();
        else
            return "NO EMAIL:(";
    }

    public String getWebsite() {
        if (companyInfo.getMetaData().getWebsite() != null)
            return companyInfo.getMetaData().getWebsite();
        else
            return "No WEBsite:(";
    }

    public void call(View view) {
        Intent i = new Intent(Intent.ACTION_CALL);
        i.setData(Uri.parse("tel:" + companyInfo.getPhone()));
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        context.startActivity(i);
    }
    public void mail(View view){
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{companyInfo.getEmail()});
        try {
            context.startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(context, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }
    public void website(View view){
        if(companyInfo.getMetaData().getWebsite()!=null){
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(companyInfo.getMetaData().getWebsite()));
            context.startActivity(browserIntent);
        }
    }
    public void faceBook(View view){
        openWebPage(companyInfo.getMetaData().getSocial().getFacebook());
//        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse());
//        (context).startActivity(browserIntent);
    }
    public void twitter(View view){
        openWebPage(companyInfo.getMetaData().getSocial().getTwitter());
//        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse());
//        context.startActivity(browserIntent);
    }

    public void instagram(View view){
        openWebPage(companyInfo.getMetaData().getSocial().getInstagram());
//        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse());
//        context.startActivity(browserIntent);
    }

    public void openWebPage(String url) {

        Uri webpage = Uri.parse(url);

        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            webpage = Uri.parse("http://" + url);
        }

        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }
}
