package dp.com.amarapp.view.holder;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import dp.com.amarapp.R;
import dp.com.amarapp.model.pojo.FullTimeWorkDay;
import dp.com.amarapp.model.pojo.WorkDay;
import dp.com.amarapp.utils.CustomUtils;
import dp.com.amarapp.view.callback.UpdateTimeListener;

public class SetChildViewHolder extends com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder {
    private TextView mfrom,mTo,nfrom,nTo;
    private WorkDay workDayMorning;
    private WorkDay workDayNight;
    private Activity activity;
    private String day;
    private String prevDay=null;
    public SetChildViewHolder(View itemView) {
        super(itemView);
        this.activity=activity;
        mfrom = itemView.findViewById(R.id.mfrom);
        mTo   = itemView.findViewById(R.id.mto);
        nfrom = itemView.findViewById(R.id.nfrom);
        nTo   = itemView.findViewById(R.id.nto);
        workDayMorning=new WorkDay();
        workDayNight=new WorkDay();

        mfrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomUtils.getInstance().showTimePickerDialog(itemView.getContext(), new UpdateTimeListener() {
                    @Override
                    public void onTimeSet(String selectedTime) {
                        mfrom.setText(selectedTime);
                        workDayMorning.setFrom(selectedTime);
                    }
                });
            }
        });

        mTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomUtils.getInstance().showTimePickerDialog(itemView.getContext(), new UpdateTimeListener() {
                    @Override
                    public void onTimeSet(String selectedTime) {
                        mTo.setText(selectedTime);
                        workDayMorning.setTo(selectedTime);
                    }
                });

            }
        });

        nfrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomUtils.getInstance().showTimePickerDialog(itemView.getContext(), new UpdateTimeListener() {
                    @Override
                    public void onTimeSet(String selectedTime) {
                        nfrom.setText(selectedTime);
                        workDayNight.setFrom(selectedTime);
                    }
                });

            }
        });

        nTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomUtils.getInstance().showTimePickerDialog(itemView.getContext(), new UpdateTimeListener() {
                    @Override
                    public void onTimeSet(String selectedTime) {
                        nTo.setText(selectedTime);
                        workDayNight.setTo(selectedTime);
                    }
                });
            }
        });

    }

    public void setData(FullTimeWorkDay workDay) {
        System.out.println("From Data Obj :"+ workDay.getMfrom() +" "+workDay.getmTo()
        +" "+workDay.getnFrom()+" "+workDay.getnTo());
        nfrom.setText(workDay.getnFrom());
        nTo.setText(workDay.getnTo());
        mfrom.setText(workDay.getMfrom());
        mTo.setText(workDay.getmTo());
        }

    public void setDay(String day){
                this.day=day;
                workDayMorning.setDay(day);
                workDayMorning.setShift("morning");
                workDayNight.setDay(day);
                workDayNight.setShift("night");
        }
    public WorkDay getMorningShift(){
        if (mfrom.equals("00:00")&&mTo.equals("00:00"))
            return null;
        return workDayMorning;
    }

    public WorkDay getNightShift(){
        if (nTo.equals("00:00")&&nfrom.equals("00:00"))
            return null;
        return workDayNight;
    }
}
