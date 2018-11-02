package yuol.secondary.market.erhuo.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import yuol.secondary.market.erhuo.Adapter.SortRecyclerAdapter;
import yuol.secondary.market.erhuo.R;
import yuol.secondary.market.erhuo.Utils.ActivityCollector;
import yuol.secondary.market.erhuo.Utils.LogUtil;
import yuol.secondary.market.erhuo.Utils.NetworkUtils;
import yuol.secondary.market.erhuo.Utils.Popup;
import yuol.secondary.market.erhuo.bean.Category;


public class ReleaseFragment extends Fragment {
    private View view;
    private RelativeLayout price;
    private RelativeLayout sort;
    private RelativeLayout number;
    private TextView showPrice;
    private TextView showSort;
    private TextView showCondition;
    private Context context = ActivityCollector.currentActivity();
    private static final String TAG = "ReleaseFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_release, container, false);
        findView();
        setEvent();
        LogUtil.d(TAG,"create");
        return view;
    }

    private void findView() {
        price = view.findViewById(R.id.fragment_release_price);
        sort = view.findViewById(R.id.fragment_release_sort);
        number = view.findViewById(R.id.fragment_release_number);
        showPrice = view.findViewById(R.id.fragment_release_price_showPrice);
        showSort = view.findViewById(R.id.fragment_release_sort_showTag);
        showCondition = view.findViewById(R.id.fragment_release_number_showNumber);
    }

    private void setEvent() {
        setClickEvent();
    }

    private void setClickEvent() {
        setPrice();
        setSort();
        setCondition();
    }

    private void setCondition() {
        //加载库存输入布局
        final View view_number = LayoutInflater.from(context).inflate(R.layout.popup_release_condition,null);

        number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Popup.bigPopupWindow(view,view_number,Gravity.BOTTOM);
            }
        });

        //设置view_number内部按钮的点击事件
        Button submit = view_number.findViewById(R.id.popup_release_condition_submit);
        final EditText inputNumber = view_number.findViewById(R.id.popup_release_condition_inputNumber);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(inputNumber.getText())){
                    Popup.easyPopup.dismiss();
                    showCondition.setText(inputNumber.getText()+"成新");
                }else {
                    Popup.hintPopupWindow(ReleaseFragment.this.view, 1000, "请输入完整内容");
                }

            }
        });
    }

    private void setSort() {
        //加载分类列表
        final View view_sort = LayoutInflater.from(context).inflate(R.layout.popup_release_sort,null);
        RecyclerView recyclerView = view_sort.findViewById(R.id.popup_release_sort_recycler);

        //准备数据
        List<String> data = new ArrayList<>();
        String json = PreferenceManager.getDefaultSharedPreferences(context).getString("Json_category",null);
        if(json!=null){
            Category category = new Gson().fromJson(json,Category.class);
            List<Category.DataBean> dataBeans = category.getData();
            for(Category.DataBean temp : dataBeans){
                data.add(temp.getCatname());
            }
        }else{
            data.clear();
            data.add("网络连接失败，请打开网络重新尝试");
            //再次尝试
            NetworkUtils.loadJson("http://192.168.137.1/taoke/category.json","Json_category");
        }

        //设置RecyclerView
        LinearLayoutManager manager1 = new LinearLayoutManager(ActivityCollector.currentActivity());
        manager1.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager1);
        SortRecyclerAdapter adapter = new SortRecyclerAdapter(data);

        //实现适配器的回调接口
        adapter.setOnItemClickListener(new SortRecyclerAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View view, int position, String data) {
                showSort.setText(data);
                ActivityCollector.currentActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Popup.easyPopup.dismiss();
                    }
                });
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(context, "纠结什么呢.",Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapter);
        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Popup.hintPopupWindow(ReleaseFragment.this.view,view_sort);
            }
        });
    }

    private void setPrice() {
        //加载价格输入布局
        final View view_price = LayoutInflater.from(context).inflate(R.layout.popup_release_price,null);
        price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Popup.bigPopupWindow(view,view_price,Gravity.BOTTOM);
            }
        });

        //设置view_price内部控件事件
        Button submit = view_price.findViewById(R.id.popup_release_price_submit);
        final EditText inputPrice = view_price.findViewById(R.id.popup_release_price_inputPrice);
        final EditText inputFirstPrice = view_price.findViewById(R.id.popup_release_price_inputFirstPrice);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //将输入的信息输入到主页面
                if(!TextUtils.isEmpty(inputPrice.getText()) && !TextUtils.isEmpty(inputFirstPrice.getText())){
                    showPrice.setText("￥"+inputPrice.getText()+" / ￥"+inputFirstPrice.getText());
                    Popup.easyPopup.dismiss();
                }else {
                    //加载提示弹窗
                    Popup.hintPopupWindow(ReleaseFragment.this.view,1000,"请输入完整内容");
                }
            }
        });
    }
}
