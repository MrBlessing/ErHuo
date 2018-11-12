package yuol.secondary.market.erhuo;

import android.content.Intent;
import android.os.Bundle;
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
import yuol.secondary.market.erhuo.Utils.BasedActivity;
import yuol.secondary.market.erhuo.Utils.LogUtil;
import yuol.secondary.market.erhuo.Utils.NetworkUtils;
import yuol.secondary.market.erhuo.Utils.Popup;
import yuol.secondary.market.erhuo.bean.GoodsInfo;
import yuol.secondary.market.erhuo.bean.GoodsInfo_brief;

import static android.view.View.GONE;

public class Template extends BasedActivity {

    private RelativeLayout back;
    private TextView title;//状态栏标题
    private RelativeLayout loading;//加载控件

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
        Intent intent = getIntent();
        String tag = intent.getStringExtra("tag");

        switch (tag){
            case "goods_list": {
                //进行网络请求获取商品分类数据
                String catName = intent.getStringExtra("cat_name");
                String url = NetworkUtils.CATEGORY_GOODS + catName;
                NetworkUtils.request(url, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Template.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //取消加载页面
                                loading.setVisibility(GONE);

                                Toast.makeText(Template.this, "页面加载失败", Toast.LENGTH_SHORT).show();
                                Template.this.getSupportFragmentManager().beginTransaction().replace(R.id.template_container, Fail.newInstance(R.drawable.network_fail)).commit();
                            }
                        });
                    }
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String res = response.body().string();
                        if (res != null) {
                            final GoodsInfo_brief goodsInfo = new Gson().fromJson(res, GoodsInfo_brief.class);
                            Template.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    //取消加载页面
                                    loading.setVisibility(GONE);

                                    getSupportFragmentManager().beginTransaction().replace(R.id.template_container, GoodsList.newInstance(goodsInfo.getData().getGoods())).commit();
                                }
                            });
                        }

                    }
                });
                break;
            }
        }
    }

    private void setTitle() {
        String content = getIntent().getStringExtra("title");
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
}
