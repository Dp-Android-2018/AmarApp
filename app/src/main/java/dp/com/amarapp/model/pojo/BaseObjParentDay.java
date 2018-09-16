package dp.com.amarapp.model.pojo;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class BaseObjParentDay extends ExpandableGroup<FullTimeWorkDay> {
    private int iconResId;
    public BaseObjParentDay(String title, List<FullTimeWorkDay> items) {
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

