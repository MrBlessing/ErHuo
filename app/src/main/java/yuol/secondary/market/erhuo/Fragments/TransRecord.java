package yuol.secondary.market.erhuo.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import yuol.secondary.market.erhuo.Adapter.TransRecordAdapter;
import yuol.secondary.market.erhuo.R;
import yuol.secondary.market.erhuo.Utils.ActivityCollector;
import yuol.secondary.market.erhuo.bean.TransRecordBean;


public class TransRecord extends Fragment {

    private static String PARAM ="param";
    private static List<TransRecordBean.DataBean> data;
    private Context context;
    private View view;

    public static TransRecord newInstance(ArrayList<TransRecordBean.DataBean> data) {
        TransRecord fragment = new TransRecord();
        //将参数传入到Bundle中保存起来
        Bundle args = new Bundle();
        args.putSerializable(PARAM,data);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_trans_record, container, false);
        context = ActivityCollector.currentActivity();
        setRecycler();
        return view;
    }

    private void setRecycler() {
        RecyclerView recycler = view.findViewById(R.id.fragment_trans_record_recycler);
        LinearLayoutManager manager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
        recycler.setLayoutManager(manager);
        recycler.setAdapter(new TransRecordAdapter(data));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            data = (List<TransRecordBean.DataBean>) getArguments().getSerializable(PARAM);
        }
    }


}
