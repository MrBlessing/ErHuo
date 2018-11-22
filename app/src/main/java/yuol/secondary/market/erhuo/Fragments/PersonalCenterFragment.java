package yuol.secondary.market.erhuo.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import yuol.secondary.market.erhuo.Login;
import yuol.secondary.market.erhuo.R;
import yuol.secondary.market.erhuo.Template;
import yuol.secondary.market.erhuo.Utils.ActivityCollector;
import yuol.secondary.market.erhuo.Utils.KeyValueUtil;
import yuol.secondary.market.erhuo.Utils.NetworkUtils;
import yuol.secondary.market.erhuo.Utils.Popup;

public class PersonalCenterFragment extends Fragment {
    private View view;
    private Context context;
    private LinearLayout transRecord;//交易信息入口
    private FrameLayout login;//登陆入口
    private LinearLayout personalInfo;//个人信息入口
    private TextView name;//用户名
    private LinearLayout setting;
    private LinearLayout message;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = ActivityCollector.currentActivity();
        view=inflater.inflate(R.layout.fragment_personal_center, container, false);
        findView();
        setEvent();
        return view;
    }

    private void findView() {
        login = view.findViewById(R.id.personal_center_login);
        transRecord = view.findViewById(R.id.fragment_personal_center_transRecord);
        personalInfo = view.findViewById(R.id.fragment_personal_center_personalInfo);
        name = view.findViewById(R.id.fragment_personal_center_personal_name);
        setting = view.findViewById(R.id.item_fragment_personal_center_middle_setting);
        message = view.findViewById(R.id.fragment_personal_center_message);
    }

    private void setEvent() {
        //设置登陆事件
        loginEvent();
        //个人资料的入口
        showPersonalInfo();
        //设置的入口
        settingEvent();
        //交易记录的入口
        transRecordEvent();
        //系统消息的入口
        messageEvent();
    }

    private void messageEvent() {
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cookie = PreferenceManager.getDefaultSharedPreferences(context).getString(KeyValueUtil.COOKIE,null);
                if(!TextUtils.isEmpty(cookie)){
                    changeActivity("系统消息",KeyValueUtil.TEMPLATE_MESSAGE);
                }else{
                    Popup.hintPopupWindow(view,"请先登录！",Popup.HINT_TIME);
                }
            }
        });
    }

    private void transRecordEvent() {
        transRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cookie = PreferenceManager.getDefaultSharedPreferences(context).getString(KeyValueUtil.COOKIE,null);
                if(!TextUtils.isEmpty(cookie)){
                    changeActivity("交易记录",KeyValueUtil.TEMPLATE_TRANSRECORD);
                }else{
                    Popup.hintPopupWindow(view,"请先登录！",Popup.HINT_TIME);
                }
            }
        });
    }

    private void settingEvent() {
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity("设置",KeyValueUtil.TEMPLATE_SETTING);
            }
        });
    }

    private void showPersonalInfo() {
            personalInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //通过cookie检测是否登陆
                    String cookie = PreferenceManager.getDefaultSharedPreferences(context).getString(KeyValueUtil.COOKIE,null);
                    if(!TextUtils.isEmpty(cookie)){
                        changeActivity("个人资料",KeyValueUtil.TEMPLATE_PERSONAL_INFO);
                    }else{
                        Popup.hintPopupWindow(view,"请先登录！",Popup.HINT_TIME);
                    }
                }
            });



    }

    //切换页面
    private void changeActivity(String title , String tag){
        Intent intent = new Intent(context, Template.class);
        intent.putExtra(KeyValueUtil.TEMPLATE_TITLE,title);
        intent.putExtra(KeyValueUtil.TEMPLATE_TAG,tag);
        startActivity(intent);
    }

    //根据登陆状态来设置点击事件
    private void loginEvent() {
        //如果cookie不为空，那么就说明已经登陆
        String cookie = PreferenceManager.getDefaultSharedPreferences(context).getString(KeyValueUtil.COOKIE,null);
        if(!TextUtils.isEmpty(cookie)){
            afterLogin();
        }else{
            beforeLogin();
        }
    }
    //登陆之前
    private void beforeLogin() {
        name.setText("未登陆");
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Login.class);
                startActivityForResult(intent,0);
            }
        });
    }
    //登陆之后
    private void afterLogin() {
        //先不管有没有网，把cookie里面的用户名展现出来
        String user = PreferenceManager.getDefaultSharedPreferences(context).getString(KeyValueUtil.USER_NAME,null);
        name.setText(user);
        //设置空的点击事件
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Popup.hintPopupWindow(view,"已登录",Popup.HINT_TIME);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        //每次前台后台发生变化，就检查一下登陆状态
        loginEvent();
    }
}
