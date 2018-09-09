package dp.com.amarapp.model.pojo;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.ArrayList;
import java.util.List;

public class ParentDay extends ExpandableGroup<WorkDay> {
    private int iconResId;

    public ParentDay(String title, List<WorkDay> items) {
        super(title, items);
    }

    public int getIconResId() {
        return iconResId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParentDay)) return false;

        ParentDay genre = (ParentDay) o;

        return getIconResId() == genre.getIconResId();

    }

    @Override
    public int hashCode() {
        return getIconResId();
    }
}
