package yuol.secondary.market.erhuo.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import yuol.secondary.market.erhuo.R;
import yuol.secondary.market.erhuo.Utils.ActivityCollector;
import yuol.secondary.market.erhuo.Utils.GoodsList;
import yuol.secondary.market.erhuo.bean.GoodsInfo;

public class Find extends Fragment {

    private static String PARAM = "param";//保存外部传来的参数的标签
    private ArrayList<GoodsInfo.DataBean> data;

    public static Find newInstance(ArrayList<GoodsInfo.DataBean> data) {
        Find fragment = new Find();
        //将参数传入到Bundle中保存起来
        Bundle args = new Bundle();
        args.putSerializable(PARAM,data);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Context context = ActivityCollector.currentActivity();
        View view = inflater.inflate(R.layout.fragment_find, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.fragment_find_recycler);
        GoodsList.setGoodsList(context, recyclerView,data);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //接收数据
        if (getArguments() != null) {
            data = (ArrayList<GoodsInfo.DataBean>) getArguments().getSerializable(PARAM);
        }

    }
}
