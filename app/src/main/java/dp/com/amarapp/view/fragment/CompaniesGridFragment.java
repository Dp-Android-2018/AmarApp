package dp.com.amarapp.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import dp.com.amarapp.R;
import dp.com.amarapp.databinding.ActivityCompaniesGridBinding;
import dp.com.amarapp.viewmodel.CompaniesGridViewModel;

public class CompaniesGridFragment extends Fragment {
    private CompaniesGridViewModel gridViewModel;
    private ActivityCompaniesGridBinding gridBinding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        gridViewModel=new CompaniesGridViewModel(getActivity());
        gridBinding= DataBindingUtil.inflate(inflater,R.layout.activity_companies_grid,container,false);
        gridBinding.setCompaniesViewModel(gridViewModel);
        View view=gridBinding.getRoot();
        Toolbar toolbar=((AppCompatActivity)getActivity()).findViewById(R.id.toolbar);
        ImageView ivFilter=toolbar.findViewById(R.id.iv_filter);
        ivFilter.setVisibility(View.GONE);
        return view;
    }
}
