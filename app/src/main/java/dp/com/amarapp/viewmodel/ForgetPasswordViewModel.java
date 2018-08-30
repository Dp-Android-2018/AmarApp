package dp.com.amarapp.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;

import java.util.Observable;

import dp.com.amarapp.view.callback.BaseInterface;

public class ForgetPasswordViewModel extends Observable {
    public ObservableField<String> email;
    private Context context;
    private BaseInterface callback;

}
