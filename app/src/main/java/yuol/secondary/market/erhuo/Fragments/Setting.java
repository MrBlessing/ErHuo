package yuol.secondary.market.erhuo.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import yuol.secondary.market.erhuo.R;
import yuol.secondary.market.erhuo.Utils.ActivityCollector;
import yuol.secondary.market.erhuo.Utils.FileUtils;
import yuol.secondary.market.erhuo.Utils.KeyValueUtil;
import yuol.secondary.market.erhuo.Utils.Popup;


public class Setting extends Fragment {

    private View view;
    private Context context;
    private LinearLayout logout ;//注销
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_setting, container, false);
        context = ActivityCollector.currentActivity();
        findView();
        setEvent();
        return view;
    }

    private void setEvent() {
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //将cookie置为空
                String cookie =PreferenceManager.getDefaultSharedPreferences(context).getString(KeyValueUtil.COOKIE,null);
                if(!TextUtils.isEmpty(cookie)){
                    FileUtils.saveByPreference(KeyValueUtil.COOKIE,"");
                    Popup.hintPopupWindow(view,"注销成功",Popup.HINT_TIME);
                }else{
                    Popup.hintPopupWindow(view,"请先登陆！",Popup.HINT_TIME);
                }

            }
        });
    }

    private void findView() {
        logout = view.findViewById(R.id.setting_logout);
    }


}
