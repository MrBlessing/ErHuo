package yuol.secondary.market.erhuo.Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import yuol.secondary.market.erhuo.FindGoods;
import yuol.secondary.market.erhuo.HomePage;
import yuol.secondary.market.erhuo.R;
import yuol.secondary.market.erhuo.Template;
import yuol.secondary.market.erhuo.Utils.ActivityCollector;
import yuol.secondary.market.erhuo.Utils.NetworkUtils;
import yuol.secondary.market.erhuo.Utils.Popup;
import yuol.secondary.market.erhuo.bean.GoodsInfo;
import yuol.secondary.market.erhuo.bean.GoodsInfo_brief;
import yuol.secondary.market.erhuo.bean.ImageUrl;

import static android.view.View.GONE;

public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";
    private View view;
    private ConvenientBanner convenientBanner;//轮播
    private SwipeRefreshLayout refresh;//下滑控件
    private LinearLayout find;//搜索框
    private LinearLayout category;//分类卡片
    private RelativeLayout loading;
    private ArrayList<GoodsInfo.DataBean> data;
    private Context context ;//这个上下文可以调用getSupportFragmentManager

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context =  ActivityCollector.currentActivity();
        view = inflater.inflate(R.layout.fragment_home, container, false);
        findView();
        setEvent();
        return view;
    }

    private void findView() {
        convenientBanner = view.findViewById(R.id.fragment_home_ConvenientBanner);
        refresh = view.findViewById(R.id.fragment_home_refresh);
        find = view.findViewById(R.id.fragment_home_find);
        category = view.findViewById(R.id.fragment_home_category);
        loading = view.findViewById(R.id.fragment_home_loading);
    }

    private void setEvent() {
        setRefreshEvent();
        setConvenientBanner();
        setFragment();
        setFindEvent();
        setCatEvent();
    }

    private void setFragment() {
        NetworkUtils.request(NetworkUtils.GOODS_INFO, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if(refresh.isRefreshing())
                    refresh.setRefreshing(false);
                //如果当前不在本页面停止进行页面的切换
                if(ActivityCollector.currentActivity() instanceof HomePage){
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_home_container,Fail.newInstance(R.drawable.network_fail)).commit();
                    ActivityCollector.currentActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "数据加载失败", Toast.LENGTH_SHORT).show();
                            //取消加载页面
                            loading.setVisibility(GONE);
                        }
                    });
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                    String res = response.body().string();
                    if(!TextUtils.isEmpty(res)){
                        ActivityCollector.currentActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(context, "数据加载成功", Toast.LENGTH_SHORT).show();
                                //取消加载页面
                                loading.setVisibility(GONE);
                            }
                         });
                        GoodsInfo_brief goodsInfo = new Gson().fromJson(res,GoodsInfo_brief.class);
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_home_container,GoodsList.newInstance(goodsInfo.getData().getGoods())).commit();
                        if(refresh.isRefreshing()){
                            refresh.setRefreshing(false);
                        }

                    }
            }
        });
    }

    private void setCatEvent() {
        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Template.class);
                intent.putExtra("tag","goods_list");
                intent.putExtra("cat_name","二手书籍");
                intent.putExtra("title","二手书籍");
                startActivity(intent);
            }
        });
    }

    private void setFindEvent() {
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, FindGoods.class);
                Bundle info = new Bundle();
                info.putSerializable("data",data);
                intent.putExtras(info);
                context.startActivity(intent);
            }
        });
    }

    private void setRefreshEvent() {
        refresh.setColorSchemeColors(Color.RED,Color.YELLOW,Color.GREEN);
        refresh.setOnRefreshListener(new RefreshListener());
    }

    private void setConvenientBanner() {
        List<String> data = new ArrayList<>();//轮播图片资源
        String json = PreferenceManager.getDefaultSharedPreferences(context).getString("Json_imageUrl",null);
        if(json!=null){
            //解析json数据
            Gson gson = new Gson();
            ImageUrl imageUrl = gson.fromJson(json,ImageUrl.class);
            List<ImageUrl.BannerBean> bannerBeans = imageUrl.getBanner();
            for(ImageUrl.BannerBean temp : bannerBeans){
                data.add(temp.getBanner());
            }
        }else {
            //保存的数据为空的话再次请求一遍数据
            NetworkUtils.loadJson(NetworkUtils.IMAGE_URL,"Json_imageUrl");
        }
        //存入数据和Holder
        convenientBanner.setPages(new CBViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new LocalHolder();
            }
        },data)
        .setPageIndicator(new int[]{R.drawable.dot_white ,R.drawable.dot_grey})
        .startTurning(2000);
    }

    //适配ConvenientBanner
    class LocalHolder implements Holder<String> {
        private ImageView imageView;
        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, String data) {
            Glide.with(context).load(data).into(imageView);
        }

    }

    //刷新的事件接口
    class RefreshListener implements SwipeRefreshLayout.OnRefreshListener{
        @Override
        public void onRefresh() {
            Toast.makeText(context, "努力加载中~", Toast.LENGTH_SHORT).show();
            setFragment();
        }

    }

}
