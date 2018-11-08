package yuol.secondary.market.erhuo.Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import yuol.secondary.market.erhuo.R;
import yuol.secondary.market.erhuo.Utils.ActivityCollector;
import yuol.secondary.market.erhuo.Utils.FileUtils;
import yuol.secondary.market.erhuo.FindGoods;
import yuol.secondary.market.erhuo.Utils.GoodsList;
import yuol.secondary.market.erhuo.Utils.NetworkUtils;
import yuol.secondary.market.erhuo.bean.GoodsInfo;
import yuol.secondary.market.erhuo.bean.ImageUrl;

public class HomeFragment extends Fragment {
    private View view;
    private ConvenientBanner convenientBanner;
    private Context context ;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refresh;
    private LinearLayout find;
    private ArrayList<GoodsInfo.DataBean> data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = ActivityCollector.currentActivity();
        view = inflater.inflate(R.layout.fragment_home, container, false);
        findView();
        setEvent();
        return view;
    }

    private void findView() {
        convenientBanner = view.findViewById(R.id.fragment_home_ConvenientBanner);
        recyclerView = view.findViewById(R.id.fragment_home_recycler);
        refresh = view.findViewById(R.id.fragment_home_refresh);
        find = view.findViewById(R.id.fragment_home_find);
    }

    private void setEvent() {
        setRefreshEvent();
        setConvenientBanner();
        setRecyclerView();
        setFindEvent();
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
        //设置自动刷新
        refresh.setRefreshing(true);
        new RefreshListener().onRefresh();
    }

    private void setRecyclerView() {
        String json = PreferenceManager.getDefaultSharedPreferences(context).getString("Json_goodsInfo",null);
        if(json!=null){
            GoodsInfo goodsInfo =  new Gson().fromJson(json,GoodsInfo.class);
            data = goodsInfo.getData();
            //加载列表
            GoodsList.setGoodsList(context,recyclerView,data);
        }else {
            Toast.makeText(context, "商品信息加载失败", Toast.LENGTH_SHORT).show();
            //商品加载失败之后强制刷新
            if(!refresh.isRefreshing()){
                new RefreshListener().onRefresh();
            }
        }
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
            NetworkUtils.loadJson("http://192.168.137.1/taoke/ImageUrl.json","Json_imageUrl");
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

            //重新进行网络请求
            NetworkUtils.request("http://192.168.137.1/taoke/goodsInfo.json", new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    ActivityCollector.currentActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "请检查你的网络", Toast.LENGTH_SHORT).show();
                            refresh.setRefreshing(false);
                        }
                    });
                }

                @Override
                public void onResponse(Call call, final Response response) throws IOException {
                    //保存数据
                    FileUtils.saveByPeference(ActivityCollector.currentActivity(), "Json_goodsInfo", response.body().string());
                    ActivityCollector.currentActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "数据更新完成", Toast.LENGTH_SHORT).show();
                            //重新将数据加载到页面中
                            setRecyclerView();
                            refresh.setRefreshing(false);
                        }
                    });
                }
            });
        }

    }

}
