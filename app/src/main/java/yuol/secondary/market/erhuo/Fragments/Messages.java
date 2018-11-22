package yuol.secondary.market.erhuo.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import yuol.secondary.market.erhuo.Adapter.GoodsAdapter;
import yuol.secondary.market.erhuo.Adapter.MessageAdapter;
import yuol.secondary.market.erhuo.R;
import yuol.secondary.market.erhuo.Utils.ActivityCollector;
import yuol.secondary.market.erhuo.bean.MessageBean;
import yuol.secondary.market.erhuo.bean.Personal;


public class Messages extends Fragment {

    private static String PARAM ="param";
    private static List<MessageBean.DataBean> data;
    private Context context;

    public static Messages newInstance(ArrayList<MessageBean.DataBean> data) {
        Messages fragment = new Messages();
        //将参数传入到Bundle中保存起来
        Bundle args = new Bundle();
        args.putSerializable(PARAM,data);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_messages, container, false);
        context = ActivityCollector.currentActivity();
        //设置列表
        RecyclerView recyclerView = view.findViewById(R.id.fragment_messages_recycler);
        MessageAdapter adapter = new MessageAdapter(data);
        LinearLayoutManager manager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            data = (List<MessageBean.DataBean>) getArguments().getSerializable(PARAM);
        }
    }

}

