package dp.com.amarapp.model.pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class FullTimeWorkDay implements Parcelable{
    private String mfrom;
    private String mTo;
    private String nFrom;
    private String nTo;


    public FullTimeWorkDay() {
    }

    public FullTimeWorkDay(String mfrom, String mTo, String nFrom, String nTo) {
        this.mfrom = mfrom;
        this.mTo = mTo;
        this.nFrom = nFrom;
        this.nTo = nTo;
    }

    public FullTimeWorkDay(Parcel in) {
        mfrom = in.readString();
        mTo = in.readString();
        nFrom = in.readString();
        nTo = in.readString();
    }

    public static final Creator<FullTimeWorkDay> CREATOR = new Creator<FullTimeWorkDay>() {
        @Override
        public FullTimeWorkDay createFromParcel(Parcel in) {
            return new FullTimeWorkDay(in);
        }

        @Override
        public FullTimeWorkDay[] newArray(int size) {
            return new FullTimeWorkDay[size];
        }
    };

    public String getMfrom() {
        return mfrom;
    }

    public void setMfrom(String mfrom) {
        this.mfrom = mfrom;
    }

    public String getmTo() {
        return mTo;
    }

    public void setmTo(String mTo) {
        this.mTo = mTo;
    }

    public String getnFrom() {
        return nFrom;
    }

    public void setnFrom(String nFrom) {
        this.nFrom = nFrom;
    }

    public String getnTo() {
        return nTo;
    }

    public void setnTo(String nTo) {
        this.nTo = nTo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mfrom);
        dest.writeString(mTo);
        dest.writeString(nFrom);
        dest.writeString(nTo);
    }
}
