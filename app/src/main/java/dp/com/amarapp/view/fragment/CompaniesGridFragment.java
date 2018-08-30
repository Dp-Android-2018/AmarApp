package dp.com.amarapp.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        return view;
    }
}
