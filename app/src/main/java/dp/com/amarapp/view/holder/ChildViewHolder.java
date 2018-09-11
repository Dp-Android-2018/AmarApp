package dp.com.amarapp.view.holder;

import android.view.View;
import android.widget.TextView;

import dp.com.amarapp.R;
import dp.com.amarapp.model.pojo.WorkDay;

public class ChildViewHolder extends com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder {
    private TextView mfrom,mTo,shift;
    public ChildViewHolder(View itemView) {
        super(itemView);
        mfrom = itemView.findViewById(R.id.mfrom);
        mTo   = itemView.findViewById(R.id.mto);
        shift = itemView.findViewById(R.id.text);
    }

    public void setData(WorkDay workDay){
        if (workDay.getId()==1){
            shift.setText("دوام صباحى");
            mfrom.setText(workDay.getFrom());
            mTo.setText(workDay.getTo());
        }else if(workDay.getId()==2) {
            shift.setText("دوام مسائى");
            mfrom.setText(workDay.getFrom());
            mTo.setText(workDay.getTo());
        }
    }
}
