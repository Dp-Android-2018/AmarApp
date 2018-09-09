package dp.com.amarapp.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import dp.com.amarapp.R;

public class ParentViewHolder extends GroupViewHolder {

    private TextView tvDay;
    public ParentViewHolder(View itemView) {
        super(itemView);
        tvDay=(TextView) itemView.findViewById(R.id.tv_title);
    }


    public void setDayTitle(ExpandableGroup genre) {
       tvDay.setText(genre.getTitle());
    }
}
