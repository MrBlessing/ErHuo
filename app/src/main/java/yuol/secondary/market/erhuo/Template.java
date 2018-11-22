package yuol.secondary.market.erhuo;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import yuol.secondary.market.erhuo.Fragments.Fail;
import yuol.secondary.market.erhuo.Fragments.GoodsList;
import yuol.secondary.market.erhuo.Fragments.Messages;
import yuol.secondary.market.erhuo.Fragments.PersonalInfo;
import yuol.secondary.market.erhuo.Fragments.Setting;
import yuol.secondary.market.erhuo.Fragments.TransRecord;
import yuol.secondary.market.erhuo.Utils.ActivityCollector;
import yuol.secondary.market.erhuo.Utils.BasedActivity;
import yuol.secondary.market.erhuo.Utils.KeyValueUtil;
import yuol.secondary.market.erhuo.Utils.LogUtil;
import yuol.secondary.market.erhuo.Utils.NetworkUtils;
import yuol.secondary.market.erhuo.Utils.Popup;
import yuol.secondary.market.erhuo.bean.GoodsInfo_brief;
import yuol.secondary.market.erhuo.bean.MessageBean;
import yuol.secondary.market.erhuo.bean.Personal;
import yuol.secondary.market.erhuo.bean.TransRecordBean;

import static android.view.View.GONE;

public class Template extends BasedActivity {

    private RelativeLayout back;
    private TextView title;//状态栏标题
    private RelativeLayout loading;//加载控件
    private static final String TAG = "Template";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template);
        findView();
        setEvent();
    }

    private void setEvent() {
        setBackEvent();
        setTitle();
        changeFragment();
    }

    private void changeFragment() {
        //获取要加载的碎片
        Intent intent = getIntent();
        String tag = intent.getStringExtra(KeyValueUtil.TEMPLATE_TAG);

        switch (tag){
            //打开分类页面
            case KeyValueUtil.TEMPLATE_GOODS_LIST:
                String catName = intent.getStringExtra(KeyValueUtil.CAT_NAME);
                String url = NetworkUtils.CATEGORY_GOODS + catName;
                LogUtil.d(TAG,url);
                NetworkUtils.request(url, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        networkFail();
                    }
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String res = response.body().string();
                        if (!TextUtils.isEmpty(res)) {
                            LogUtil.d(TAG,res);
                            final GoodsInfo_brief goodsInfo = new Gson().fromJson(res, GoodsInfo_brief.class);
                            if(goodsInfo.getCode()==200){
                                //数据请求成功
                                Template.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try{

                                            //取消加载页面
                                            loading.setVisibility(GONE);
                                            getSupportFragmentManager().beginTransaction().replace(R.id.template_container, GoodsList.newInstance(goodsInfo.getData().getGoods())).commit();
                                        }catch (Exception e){
                                            //找不到原来的页面，抛出异常
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            }else {
                                //数据请求失败
                                Template.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        //取消加载页面
                                        loading.setVisibility(GONE);
                                        Template.this.getSupportFragmentManager().beginTransaction().replace(R.id.template_container, Fail.newInstance(R.drawable.find_fail)).commit();
                                    }
                                });
                            }
                        }

                    }

                });
                break;
                //打开个人信息页面
            case KeyValueUtil.TEMPLATE_PERSONAL_INFO :
                NetworkUtils.requestWithCookie(NetworkUtils.PERSONAL_INFO,new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                            networkFail();
                        }
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String res = response.body().string();
                            LogUtil.d(TAG,res);
                            if(!TextUtils.isEmpty(res)){
                                final Personal personal = new Gson().fromJson(res,Personal.class);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            //取消加载页面
                                            loading.setVisibility(GONE);
                                            getSupportFragmentManager().beginTransaction().replace(R.id.template_container,PersonalInfo.newInstance(personal.getData())).commit();
                                        }catch (Exception e){
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            }
                        }
                    });
                break;
            //启动设置页面
            case KeyValueUtil.TEMPLATE_SETTING :
                //取消加载页面
                loading.setVisibility(GONE);
                getSupportFragmentManager().beginTransaction().replace(R.id.template_container, new Setting()).commit();
                break;
            case KeyValueUtil.TEMPLATE_TRANSRECORD :
                //交易记录页面
                NetworkUtils.requestWithCookie(NetworkUtils.TRANS_RECORD, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        networkFail();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String res = response.body().string();
                        if(!TextUtils.isEmpty(res)){
                            LogUtil.d(TAG,res);
                            final TransRecordBean transRecordBean = new Gson().fromJson(res,TransRecordBean.class);
                            if(transRecordBean.getCode()==200) {
                                ActivityCollector.currentActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            //取消加载
                                            loading.setVisibility(GONE);
                                            //加载碎片
                                            getSupportFragmentManager().beginTransaction().replace(R.id.template_container, TransRecord.newInstance(transRecordBean.getData())).commit();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            }
                        }else{
                            Popup.hintPopupWindow(getWindow().getDecorView().getRootView(),"未知错误",1000);
                        }
                    }
                });
                break;
            case KeyValueUtil.TEMPLATE_MESSAGE :
                //系统消息页面
                NetworkUtils.requestWithCookie(NetworkUtils.MESSAGE, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        networkFail();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String res = response.body().string();
                        if(!TextUtils.isEmpty(res)){
                            LogUtil.d(TAG,res);
                            ActivityCollector.currentActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        loading.setVisibility(GONE);
                                        MessageBean messageBean = new Gson().fromJson(res, MessageBean.class);
                                        getSupportFragmentManager().beginTransaction().replace(R.id.template_container, Messages.newInstance(messageBean.getData())).commit();
                                    }catch (Exception e){
                                        e.printStackTrace();
                                    }
                                }
                            });

                        }
                    }
                });
                break;
        }
    }

    private void setTitle() {
        String content = getIntent().getStringExtra(KeyValueUtil.TEMPLATE_TITLE);
        if(title!=null){
            title.setText(content);
        }
    }

    private void setBackEvent() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void findView() {
        back = findViewById(R.id.template_back);
        title = findViewById(R.id.template_title);
        loading = findViewById(R.id.template_loading);
    }

    //网络请求失败之后干的事情
    private void networkFail(){
        //取消加载页面
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try{
                    loading.setVisibility(GONE);
                    Toast.makeText(Template.this, "页面加载失败", Toast.LENGTH_SHORT).show();
                    Template.this.getSupportFragmentManager().beginTransaction().replace(R.id.template_container, Fail.newInstance(R.drawable.network_fail)).commit();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

    }
}
