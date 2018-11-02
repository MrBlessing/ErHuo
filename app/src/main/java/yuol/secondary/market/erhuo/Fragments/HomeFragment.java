package yuol.secondary.market.erhuo.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import yuol.secondary.market.erhuo.Adapter.GoodsAdapter;
import yuol.secondary.market.erhuo.R;
import yuol.secondary.market.erhuo.Utils.ActivityCollector;
import yuol.secondary.market.erhuo.Utils.LogUtil;
import yuol.secondary.market.erhuo.Utils.NetworkUtils;
import yuol.secondary.market.erhuo.bean.GoodsInfo;
import yuol.secondary.market.erhuo.bean.ImageUrl;

public class HomeFragment extends Fragment {
    private View view;
    private ConvenientBanner convenientBanner;
    private Context context = ActivityCollector.currentActivity();
    private RecyclerView recyclerView;
    private static final String TAG = "HomeFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        findView();
        setEvent();
        return view;
    }

    private void findView() {
        convenientBanner = view.findViewById(R.id.fragment_home_ConvenientBanner);
        recyclerView = view.findViewById(R.id.fragment_home_recycler);
    }

    private void setEvent() {
        setConvenientBanner();
        setRecyclerView();
    }

    private void setRecyclerView() {
        //为测试手动产生数据
        GoodsInfo info = new GoodsInfo();
        info.setImage("http://192.168.137.1/taoke/start.png");
        info.setName("贱卖葫芦娃");
        info.setPrice("9.90");
        List<GoodsInfo> data = new ArrayList<>();
        for(int i = 0 ; i<10 ;i++){
            data.add(info);
        }
        GoodsAdapter adapter = new GoodsAdapter(data);
        GridLayoutManager manager = new GridLayoutManager(context,2);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    private void setConvenientBanner() {
        List<String> data = new ArrayList<>();//轮播图片资源
        String json = PreferenceManager.getDefaultSharedPreferences(context).getString("Json_imageUrl",null);
        if(json!=null){
            //解析json数据
            LogUtil.d(TAG,"noKong");
            Gson gson = new Gson();
            ImageUrl imageUrl = gson.fromJson(json,ImageUrl.class);
            List<ImageUrl.BannerBean> bannerBeans = imageUrl.getBanner();
            for(ImageUrl.BannerBean temp : bannerBeans){
                data.add(temp.getBanner());
            }
        }else {
            //保存的数据为空的话再次请求一遍数据
            LogUtil.d(TAG,"Kong");
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
}
