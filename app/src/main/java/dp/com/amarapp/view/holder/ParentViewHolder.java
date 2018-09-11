package dp.com.amarapp.view.holder;

import android.view.View;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import dp.com.amarapp.R;

public class ParentViewHolder extends GroupViewHolder {

    private TextView tvDay;
    private TextView tvP;
    public ParentViewHolder(View itemView) {
        super(itemView);
        tvDay=(TextView) itemView.findViewById(R.id.tv_title);
        tvP=itemView.findViewById(R.id.tv_minus);
    }


    public void setDayTitle(ExpandableGroup genre) {
       tvDay.setText(genre.getTitle());
    }

    @Override
    public void expand() {
        super.expand();
        tvP.setText("-");
    }

    @Override
    public void collapse() {
        super.collapse();
        tvP.setText("+");
    }
}


