package yuol.secondary.market.erhuo.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import yuol.secondary.market.erhuo.R;
import yuol.secondary.market.erhuo.Utils.ActivityCollector;
import yuol.secondary.market.erhuo.Utils.FileUtils;
import yuol.secondary.market.erhuo.Utils.KeyValueUtil;
import yuol.secondary.market.erhuo.Utils.LogUtil;
import yuol.secondary.market.erhuo.Utils.NetworkUtils;
import yuol.secondary.market.erhuo.Utils.Popup;
import yuol.secondary.market.erhuo.bean.GoodsInfo_brief;
import yuol.secondary.market.erhuo.bean.Personal;


public class PersonalInfo extends Fragment {

    private static String PARAM ="param";
    private static Personal.DataBean data;
    private Context context;
    private View view;
    private EditText nick;
    private EditText sign;
    private EditText birth;
    private EditText sex;
    private EditText place;
    private EditText intre;
    private Button submit;
    private static final String TAG = "PersonalInfo";

    public static PersonalInfo newInstance(Personal.DataBean data) {
        PersonalInfo fragment = new PersonalInfo();
        //将参数传入到Bundle中保存起来
        Bundle args = new Bundle();
        args.putSerializable(PARAM,data);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_personal_info, container, false);
        context = ActivityCollector.currentActivity();
        findView();
        setEvent();
        return view;
    }

    private void setEvent() {
        showData();
        submitData();
    }

    private void submitData() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //开始加载
                Popup.processPopupWindow(view);
                //获取用户所要的信息
                String url = NetworkUtils.PERSONAL_INFO+"?sure=确认发布&name="+nick.getText()+"&sex="+sex.getText()+"&sign="+sign.getText()+"&birth= &intro="+intre.getText()+"&where="+place.getText();
                //保存昵称
                FileUtils.saveByPreference(KeyValueUtil.USER_NAME,nick.getText().toString());
                NetworkUtils.requestWithCookie(url,  new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        try{
                            ActivityCollector.currentActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {//取消加载
                                    Popup.easyPopup.dismiss();
                                    Toast.makeText(context, "修改失败", Toast.LENGTH_SHORT).show(); }
                                });
                            }catch (Exception e1){
                                e1.printStackTrace();
                            }
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            try{
                                LogUtil.d(TAG,response.body().string());
                                ActivityCollector.currentActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        //取消加载
                                        Popup.easyPopup.dismiss();
                                        Toast.makeText(context, "修改成功", Toast.LENGTH_SHORT).show();
                                        ActivityCollector.currentActivity().finish();
                                    }
                                });
                            }catch (Exception e1){
                                e1.printStackTrace();
                            }
                        }
                    });
                }
        });
    }

    private void showData() {
        nick.setText(data.getNick());
        sign.setText(data.getSign());
        birth.setText(data.getBirth());
        sex.setText(data.getSex());
        place.setText(data.getPlace());
        intre.setText(data.getIntro());
    }

    private void findView() {
        nick = view.findViewById(R.id.fragment_personal_info_nick);
        sign = view.findViewById(R.id.fragment_personal_info_sign);
        birth = view.findViewById(R.id.fragment_personal_info_birth);
        sex = view.findViewById(R.id.fragment_personal_info_sex);
        place = view.findViewById(R.id.fragment_personal_info_position);
        intre = view.findViewById(R.id.fragment_personal_info_intr);
        submit = view.findViewById(R.id.fragment_personal_info_submit);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            data = (Personal.DataBean) getArguments().getSerializable(PARAM);
        }
    }
}
