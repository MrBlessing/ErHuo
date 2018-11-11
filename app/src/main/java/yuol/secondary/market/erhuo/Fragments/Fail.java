package yuol.secondary.market.erhuo.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import yuol.secondary.market.erhuo.R;
import yuol.secondary.market.erhuo.Utils.LogUtil;
import yuol.secondary.market.erhuo.bean.GoodsInfo;


public class Fail extends Fragment {
    private static final String PARAM = "Image";
    private int resource;

    public static Fail newInstance(int resource) {
        Fail fragment = new Fail();
        //将参数传入到Bundle中保存起来
        Bundle args = new Bundle();
        args.putInt(PARAM,resource);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fail, container, false);
        //加载对应的图片
        ImageView imageView = view.findViewById(R.id.fail_image);
        imageView.setImageResource(resource);
        return view;
    }


    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //接收数据
        if (getArguments() != null) {
            resource = getArguments().getInt(PARAM);
        }
    }

}
