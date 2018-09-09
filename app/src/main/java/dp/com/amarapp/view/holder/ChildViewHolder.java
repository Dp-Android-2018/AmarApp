package dp.com.amarapp.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import dp.com.amarapp.R;
import dp.com.amarapp.model.pojo.WorkDay;

public class ChildViewHolder extends com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder {
    private TextView mfrom,mTo,nFrom,nTo;
    public ChildViewHolder(View itemView) {
        super(itemView);
        mfrom = (TextView) itemView.findViewById(R.id.mfrom);
        mTo = (TextView) itemView.findViewById(R.id.mto);
        nFrom = (TextView) itemView.findViewById(R.id.nfrom);
        nTo = (TextView) itemView.findViewById(R.id.nto);
    }

    public void setData(WorkDay workDay){
        if (workDay.getShift().equals("morning")){
            mfrom.setText(workDay.getFrom());
            mTo.setText(workDay.getTo());
        }else {
            nFrom.setText(workDay.getFrom());
            nTo.setText(workDay.getTo());
        }
    }
}
