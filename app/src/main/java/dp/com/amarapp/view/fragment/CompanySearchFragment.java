package dp.com.amarapp.view.fragment;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import dp.com.amarapp.R;
import dp.com.amarapp.databinding.ActivityCompanySearchBinding;
import dp.com.amarapp.view.callback.BaseInterface;
import dp.com.amarapp.viewmodel.CompanySearchViewModel;

public class CompanySearchFragment extends Fragment implements BaseInterface {
    private CompanySearchViewModel searchViewModel;
    private ActivityCompanySearchBinding searchBinding;
    private int countryId;
    private int cityId;
    private int categoryId;
    private int specializationId;
    private String sort;

    public CompanySearchFragment() {
    }

    @SuppressLint("ValidFragment")
    public CompanySearchFragment(int countryId, int cityId, int categoryId, int specializationId) {
        this.countryId = countryId;
        this.cityId = cityId;
        this.categoryId = categoryId;
        this.specializationId = specializationId;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        searchViewModel=new CompanySearchViewModel(getActivity(),this);
        searchBinding= DataBindingUtil.inflate(inflater,R.layout.activity_company_search,container,false);
        searchBinding.setCompanySearchViewModel(searchViewModel);
        searchBinding.rvCompanies.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        View v=searchBinding.getRoot();
        searchViewModel.setPrams(countryId,cityId,categoryId,specializationId);
        filter();
        return v;
    }

    @Override
    public void updateUi(int code) {

    }
    public void filter(){
        Toolbar toolbar=((AppCompatActivity)getActivity()).findViewById(R.id.toolbar);
        ImageView ivFilter=toolbar.findViewById(R.id.iv_filter);
        ivFilter.setOnClickListener(v -> dialog());
    }
    public void dialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.filter_layout, null);
        builder.setView(alertLayout);
        builder.setCancelable(true);
        AlertDialog dialog=builder.create();
        alertLayout.findViewById(R.id.tv_high_rate).setOnClickListener(v -> {
            sort="rating";
            searchViewModel.search(sort,null);
            dialog.dismiss();
        });
        alertLayout.findViewById(R.id.tv_low_rate).setOnClickListener(v -> {
            sort="-rating";
            searchViewModel.search(sort,null);
            dialog.dismiss();
        });
        alertLayout.findViewById(R.id.tv_new_company).setOnClickListener(v -> {
            sort="time";
            searchViewModel.search(sort,null);
            dialog.dismiss();
        });
        alertLayout.findViewById(R.id.tv_old_company).setOnClickListener(v -> {
            sort="-time";
            searchViewModel.search(sort,null);
            dialog.dismiss();
        });
        dialog.show();
    }
}
