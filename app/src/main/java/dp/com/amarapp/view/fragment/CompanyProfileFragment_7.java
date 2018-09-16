package dp.com.amarapp.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dp.com.amarapp.R;
import dp.com.amarapp.databinding.FragmentCompanyProfile7Binding;
import dp.com.amarapp.model.pojo.WorkDay;
import dp.com.amarapp.model.request.UpdateWorkDaysRequest;
import dp.com.amarapp.model.response.DefaultResponse;
import dp.com.amarapp.network.ApiClient;
import dp.com.amarapp.network.EndPoints;
import dp.com.amarapp.utils.ConfigurationFile;
import dp.com.amarapp.utils.CustomUtils;
import dp.com.amarapp.utils.NetWorkConnection;
import dp.com.amarapp.view.adapter.SetWorkingDaysAdapter;
import dp.com.amarapp.viewmodel.CompanyProfileViewModel_7;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class CompanyProfileFragment_7 extends Fragment {
    private CompanyProfileViewModel_7 profileViewModel_7;
    private FragmentCompanyProfile7Binding profile7Binding;
    private List<WorkDay>workingDays=new ArrayList<>();
    HashMap<String,WorkDay>map=new HashMap<>();
    private  View v=null;
    String token="Bearer ";
    UpdateWorkDaysRequest request;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        profile7Binding= DataBindingUtil.inflate(inflater, R.layout.fragment_company_profile_7,container,false);
        profile7Binding.setProfileViewModel7(profileViewModel_7);
         v=profile7Binding.getRoot();
        profileViewModel_7=new CompanyProfileViewModel_7(getActivity(),profile7Binding.recyclerView);
        profile7Binding.btSave.setOnClickListener(new View.OnClickListener() {
                                                      @Override
                                                      public void onClick(View v) {
                                                          saveData();
                                                      }
                                                  });
                initAdapter();
        return v;
    }

    public void initAdapter(){
        request=new UpdateWorkDaysRequest();
        token+= CustomUtils.getInstance().getSaveUserObject(getContext()).getToken();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        RecyclerView.ItemAnimator animator = profile7Binding.recyclerView.getItemAnimator();
        if (animator instanceof DefaultItemAnimator) {
            ((DefaultItemAnimator) animator).setSupportsChangeAnimations(false);
        }
        profile7Binding.recyclerView.setLayoutManager(layoutManager);
    }

    public void saveData(){
       SetWorkingDaysAdapter adapter=(SetWorkingDaysAdapter) profile7Binding.recyclerView.getAdapter();
        for (int i=0;i<adapter.getChildViewHolder().size();i++){
            if (adapter.getChildViewHolder().get(i).getMorningShift()!=null){
            String key1=adapter.getChildViewHolder().get(i).getMorningShift().getDay()+"_"+adapter.getChildViewHolder().get(i).getMorningShift().getShift();
            map.put(key1,adapter.getChildViewHolder().get(i).getMorningShift());
            }if (adapter.getChildViewHolder().get(i).getNightShift()!=null) {
                String key2 = adapter.getChildViewHolder().get(i).getNightShift().getDay() + "_" + adapter.getChildViewHolder().get(i).getNightShift().getShift();
                map.put(key2, adapter.getChildViewHolder().get(i).getNightShift());
            }
        }
        for (Map.Entry<String,WorkDay> entry : map.entrySet())
        {
            System.out.println("Working Day Item :"+entry.getValue().getDay()+" "+entry.getValue().getShift()+" "+entry.getValue().getFrom()+" "+entry.getValue().getTo());
            workingDays.add(entry.getValue());
        }
        System.out.println("size of work day request :"+workingDays.size());
        request.setDays(workingDays);

        if (NetWorkConnection.isConnectingToInternet(getContext())) {
            CustomUtils.getInstance().showProgressDialog(getActivity());
            ApiClient.getClient().create(EndPoints.class).updateWorkDays(ConfigurationFile.Constants.API_KEY,
                    ConfigurationFile.Constants.CONTENT_TYPE, ConfigurationFile.Constants.CONTENT_TYPE, token, request)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Response<DefaultResponse>>() {
                        @Override
                        public void accept(Response<DefaultResponse> defaultResponseResponse) throws Exception {
                            CustomUtils.getInstance().cancelDialog();
                            System.out.println("Code is : " + defaultResponseResponse.code());
                            if (defaultResponseResponse.code() == ConfigurationFile.Constants.SUCCESS_CODE_second) {
                                Snackbar.make(profile7Binding.rlParent, "تم التعديل بنجاح", Snackbar.LENGTH_LONG).show();
                            }
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            CustomUtils.getInstance().cancelDialog();
                            System.out.println("Error : " + throwable.getMessage());

                        }
                    });
        }else {
            Snackbar.make(profile7Binding.rlParent,R.string.no_internet_connection, Snackbar.LENGTH_LONG).show();

        }

    }


}
