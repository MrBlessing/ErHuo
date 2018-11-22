package yuol.secondary.market.erhuo.Fragments;

import android.content.Context;
import android.os.Bundle;
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
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import yuol.secondary.market.erhuo.Adapter.SortRecyclerAdapter;
import yuol.secondary.market.erhuo.R;
import yuol.secondary.market.erhuo.Utils.ActivityCollector;
import yuol.secondary.market.erhuo.Utils.InputMethod;
import yuol.secondary.market.erhuo.Utils.LogUtil;
import yuol.secondary.market.erhuo.Utils.Popup;


public class ReleaseFragment extends Fragment {
    private View view;
    private RelativeLayout price;
    private RelativeLayout sort;
    private RelativeLayout number;
    private RelativeLayout contact;
    private RelativeLayout position;
    private TextView showPrice;
    private TextView showSort;
    private TextView showCondition;
    private TextView showContact;
    private TextView showPosition;
    private Context context ;
    private static final String TAG = "ReleaseFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = ActivityCollector.currentActivity();
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
        contact = view.findViewById(R.id.fragment_release_contact);
        showContact = view.findViewById(R.id.fragment_release_price_showContact);
        position = view.findViewById(R.id.fragment_release_position);
        showPosition = view.findViewById(R.id.fragment_release_position_showPosition);
    }

    private void setEvent() {
        setClickEvent();
    }

    private void setClickEvent() {
        setPrice();
        setSort();
        setCondition();
        setContact();
        setPosition();
    }

    private void setPosition() {
        //加载弹窗布局
        final View view_position = LayoutInflater.from(context).inflate(R.layout.popup_release_position,null);
        position.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Popup.bigPopupWindow(view,view_position,Gravity.BOTTOM);
            }
        });

        //设置弹窗内部视图的点击事件
        final Button submit = view_position.findViewById(R.id.popup_release_position_submit);
        final EditText input = view_position.findViewById(R.id.popup_release__position_inputPosition);
        final RadioGroup group = view_position.findViewById(R.id.popup_release_position_about);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(input.getText())){
                    String text = "";
                    switch (group.getCheckedRadioButtonId()){
                        case R.id.popup_release_position_about_east :
                            text = "东校区/";
                            break;
                        case R.id.popup_release_position_about_west :
                            text = "西校区/";
                            break;
                        case R.id.popup_release_position_about_wuhan :
                            text = "武汉校区/";
                            break;
                    }
                    String show = text +input.getText();
                    showPosition.setText(show);
                    //关闭弹窗
                    Popup.easyPopup.dismiss();
                    //隐藏输入法
                    InputMethod.hideInputMethod(input,context);
                }else {
                    Popup.hintPopupWindow(ReleaseFragment.this.view,  "请输入完整内容",Popup.HINT_TIME);
                }
            }
        });
    }

    private void setContact() {

        //加载弹窗布局
        final View view_contact = LayoutInflater.from(context).inflate(R.layout.popup_release_contact,null);
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Popup.bigPopupWindow(view,view_contact,Gravity.BOTTOM);
            }
        });

        //设置弹窗内部视图的点击事件
        Button submit = view_contact.findViewById(R.id.popup_release_contact_submit);
        final EditText input = view_contact.findViewById(R.id.popup_release_contact_inputContact);
        final RadioGroup group = view_contact.findViewById(R.id.popup_release_contact_way);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(input.getText())){
                    showContact.setText(input.getText());
                    Popup.easyPopup.dismiss();
                }else {
                    Popup.hintPopupWindow(ReleaseFragment.this.view,  "请输入完整内容",Popup.HINT_TIME);
                }
            }
        });
    }

    private void setCondition() {
        //加载弹窗布局
        final View view_number = LayoutInflater.from(context).inflate(R.layout.popup_release_condition,null);

        number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Popup.bigPopupWindow(view,view_number,Gravity.BOTTOM);
            }
        });

        //设置按钮的点击事件
        Button submit = view_number.findViewById(R.id.popup_release_condition_submit);
        final EditText inputNumber = view_number.findViewById(R.id.popup_release_condition_inputNumber);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(inputNumber.getText())){
                    Popup.easyPopup.dismiss();
                    showCondition.setText(inputNumber.getText()+"成新");
                }else {
                    Popup.hintPopupWindow(ReleaseFragment.this.view,  "请输入完整内容",Popup.HINT_TIME);
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
        data.add("代步工具");data.add("家用电器");data.add("考研考证");data.add("学姐学长笔记");data.add("鞋服配饰");data.add("特长爱好");data.add("运动健身");data.add("二手书籍");data.add("电子数码");data.add("其他商品");
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
                    Popup.hintPopupWindow(ReleaseFragment.this.view,"请输入完整内容",Popup.HINT_TIME);
                }
            }
        });
    }
}
