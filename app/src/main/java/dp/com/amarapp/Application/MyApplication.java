package dp.com.amarapp.Application;

import android.app.Application;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import dp.com.amarapp.R;
import dp.com.amarapp.model.pojo.City;
import dp.com.amarapp.model.pojo.ConnectionReceiver;
import dp.com.amarapp.model.pojo.Specialization;
import dp.com.amarapp.model.pojo.WorkDay;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class MyApplication extends Application {

    private static List<City> cities=new ArrayList<>();
    private static List<Specialization> specializations=new ArrayList<>();
    private List<WorkDay>workDayList=new ArrayList<>();
    private static MyApplication mInstance;
    @Override
    public FileOutputStream openFileOutput(String name, int mode) throws FileNotFoundException {
        return super.openFileOutput(name, mode);
    }

    public static void setCities(List<City> cities) {
        MyApplication.cities = cities;
    }

    public static List<City> getCities() {
        return cities;
    }

    public static List<Specialization> getSpecializations() {
        return specializations;
    }

    public static void setSpecializations(List<Specialization> specializations) {
        MyApplication.specializations = specializations;
    }

    public List<WorkDay> getWorkDayList() {
        return workDayList;
    }

    public void setWorkDayList(List<WorkDay> workDayList) {
        this.workDayList = workDayList;
    }

    public void onCreate(){
        super.onCreate();
        mInstance=this;
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("font/Arabic_Font.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }
    public void setConnectionListener(ConnectionReceiver.ConnectionReceiverListener listener) {
        ConnectionReceiver.connectionReceiverListener = listener;
    }

}
