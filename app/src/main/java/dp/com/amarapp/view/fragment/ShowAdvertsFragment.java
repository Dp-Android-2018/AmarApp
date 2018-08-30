package dp.com.amarapp.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dp.com.amarapp.R;
import dp.com.amarapp.databinding.ActivityShowAdvertsBinding;
import dp.com.amarapp.view.callback.BaseInterface;
import dp.com.amarapp.viewmodel.ShowAdvertsViewModel;

public class ShowAdvertsFragment extends Fragment implements BaseInterface{

    ShowAdvertsViewModel advertsViewModel;
    ActivityShowAdvertsBinding advertsBinding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        advertsViewModel=new ShowAdvertsViewModel(getActivity(),this);
        advertsBinding=DataBindingUtil.inflate(inflater, R.layout.activity_show_adverts,container,false);
        advertsBinding.setShowAdvertViewModel(advertsViewModel);
        advertsBinding.rvAdverts.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        View view=advertsBinding.getRoot();
        return view;
    }

    @Override
    public void updateUi(int code) {

    }
}
