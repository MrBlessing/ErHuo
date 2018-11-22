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
import yuol.secondary.market.erhuo.Utils.LogUtil;
import yuol.secondary.market.erhuo.Utils.RecyclerUtils;
import yuol.secondary.market.erhuo.bean.GoodsInfo;
import yuol.secondary.market.erhuo.bean.GoodsInfo_brief;

public class GoodsList extends Fragment {

    private static String PARAM = "param";//保存外部传来的参数的标签
    private  ArrayList<GoodsInfo_brief.DataBean.GoodsBean> data;

    public static GoodsList newInstance(ArrayList<GoodsInfo_brief.DataBean.GoodsBean> data) {
        GoodsList fragment = new GoodsList();
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
        View view = inflater.inflate(R.layout.fragment_goods_list, container, false);
        //设置列表
        RecyclerView recyclerView = view.findViewById(R.id.fragment_find_recycler);
        RecyclerUtils.setGoodsList(context, recyclerView,data);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //接收数据
        if (getArguments() != null) {
            data = (ArrayList<GoodsInfo_brief.DataBean.GoodsBean>) getArguments().getSerializable(PARAM);
        }
    }
}
