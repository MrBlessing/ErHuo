package yuol.secondary.market.erhuo.Utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import yuol.secondary.market.erhuo.Adapter.GoodsAdapter;
import yuol.secondary.market.erhuo.GoodsDetails;
import yuol.secondary.market.erhuo.Login;
import yuol.secondary.market.erhuo.R;
import yuol.secondary.market.erhuo.bean.GoodsInfo;
import yuol.secondary.market.erhuo.bean.GoodsInfo_brief;

public class RecyclerUtils {
    private static final String TAG = "RecyclerUtils";
    public static void setGoodsList(final Context context, RecyclerView recyclerView, List<GoodsInfo_brief.DataBean.GoodsBean> data){

        GoodsAdapter adapter = new GoodsAdapter(data);
        GridLayoutManager manager = new GridLayoutManager(context,2);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        //设置每一个item的点击事件
        adapter.setOnItemClickListener(new GoodsAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View view, String goods_id) {
                //启动加载页面
                Popup.processPopupWindow(view);

                //进行单个商品的详细信息查询
                String url =NetworkUtils.GOODS_DETAILS+goods_id;
                NetworkUtils.request(url, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        ActivityCollector.currentActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(Popup.easyPopup!=null){
                                    Popup.easyPopup.dismiss();
                                }
                                Toast.makeText(context, "加载失败", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String res = response.body().string();
                        LogUtil.d(TAG,res);
                        if(!TextUtils.isEmpty(res)){
                            //取消加载页面
                            ActivityCollector.currentActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if(Popup.easyPopup!=null){
                                        Popup.easyPopup.dismiss();
                                    }
                                }
                            });
                            //传递请求过来的数据
                            GoodsInfo data = new Gson().fromJson(res,GoodsInfo.class);
                            if(data.getCode()==200){
                                Intent intent = new Intent(context, GoodsDetails.class);
                                Bundle info = new Bundle();
                                info.putSerializable(KeyValueUtil.GOODS_INFO,data.getData());
                                intent.putExtras(info);
                                context.startActivity(intent);
                            }else {
                                //没有该商品
                                Toast.makeText(context, "没有该商品", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                });



            }
            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }
}
