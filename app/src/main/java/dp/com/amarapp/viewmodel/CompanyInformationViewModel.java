package dp.com.amarapp.viewmodel;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import dp.com.amarapp.model.response.CompanyLoginResponse;
import dp.com.amarapp.utils.ConfigurationFile;
public class CompanyInformationViewModel {
    private Context context;
    CompanyLoginResponse companyInfo;

    public CompanyInformationViewModel(Context context) {
        this.context = context;
        companyInfo = (CompanyLoginResponse) ((Activity) context).getIntent().getSerializableExtra(ConfigurationFile.IntentConstants.COMPANYITEMINFO);
        //System.out.println("social size  "+companyInfo.getMetaData().getSocial().size());
       // System.out.println("2:  "+companyInfo.getMetaData().getSocial().get(1));
       // System.out.println("3:  "+companyInfo.getMetaData().getSocial().get(2));
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
}
