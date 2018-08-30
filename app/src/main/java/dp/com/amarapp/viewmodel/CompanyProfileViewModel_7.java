package dp.com.amarapp.viewmodel;

import android.app.Activity;
import android.databinding.Bindable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.view.View;

import java.util.Observable;

import dp.com.amarapp.R;

public class CompanyProfileViewModel_7 extends Observable {
    public ObservableInt sunVisibility;
    public ObservableInt monVisibility;
    public ObservableInt tusVisibility;
    public ObservableInt wenVisibility;
    public ObservableInt thusVisibility;
    public ObservableInt friVisibility;
    public ObservableInt satVisibility;
    public ObservableField<String> sunText;
    public ObservableField<String> monText;
    public ObservableField<String> tusText;
    public ObservableField<String> wenText;
    public ObservableField<String> thusText;
    public ObservableField<String> friText;
    public ObservableField<String> satText;
    Activity activity;

    public CompanyProfileViewModel_7(Activity activity) {
        this.activity = activity;
        initVariables();
    }

    public void initVariables(){
        //initialize visibility with gone
        sunVisibility=new ObservableInt(View.GONE);
        monVisibility=new ObservableInt(View.GONE);
        tusVisibility=new ObservableInt(View.GONE);
        wenVisibility=new ObservableInt(View.GONE);
        thusVisibility=new ObservableInt(View.GONE);
        friVisibility=new ObservableInt(View.GONE);
        satVisibility=new ObservableInt(View.GONE);
        //initialize text with +
        sunText=new ObservableField<>();
        sunText.set("+");
        monText=new ObservableField<>();
        monText.set("+");
        tusText=new ObservableField<>();
        tusText.set("+");
        wenText=new ObservableField<>();
        wenText.set("+");
        thusText=new ObservableField<>();
        thusText.set("+");
        friText=new ObservableField<>();
        friText.set("+");
        satText=new ObservableField<>();
        satText.set("+");
    }
}
