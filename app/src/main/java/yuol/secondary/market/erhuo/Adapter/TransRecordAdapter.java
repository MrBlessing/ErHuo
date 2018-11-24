package yuol.secondary.market.erhuo.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import yuol.secondary.market.erhuo.R;
import yuol.secondary.market.erhuo.Utils.ActivityCollector;
import yuol.secondary.market.erhuo.Utils.LogUtil;
import yuol.secondary.market.erhuo.Utils.NetworkUtils;
import yuol.secondary.market.erhuo.Utils.Popup;
import yuol.secondary.market.erhuo.bean.GoodsInfo;
import yuol.secondary.market.erhuo.bean.Result;
import yuol.secondary.market.erhuo.bean.TransRecordBean;

public class TransRecordAdapter extends RecyclerView.Adapter<TransRecordAdapter. LocalHolder> {
    private Context context;
    private List<TransRecordBean.DataBean> data;
    private static final String TAG = "TransRecordAdapter";

    public TransRecordAdapter(List<TransRecordBean.DataBean> data){
        this.data = data;
        context = ActivityCollector.currentActivity();
    }

    @NonNull
    @Override
    public  LocalHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_trans_record, viewGroup, false);
        return new  LocalHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final  LocalHolder  localHolder, final int i) {
        localHolder.name.setText(data.get(i).getName());
        //查询商品属性
        NetworkUtils.requestWithCookie(NetworkUtils.GOODS_DETAILS + data.get(i).getGood_id(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                networkFail();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();
                if(!TextUtils.isEmpty(res)){
                    LogUtil.d(TAG,res);
                    GoodsInfo info = new Gson().fromJson(res,GoodsInfo.class);
                    final GoodsInfo.DataBean dataBean = info.getData();
                    ActivityCollector.currentActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try{
                                Glide.with(context).load(NetworkUtils.IP + dataBean.getGoods().getPic().get(0)).into(localHolder.image1);

                                if(dataBean.getGoods().getPic().get(1) != null){
                                    Glide.with(context).load(NetworkUtils.IP + dataBean.getGoods().getPic().get(1)).into(localHolder.image2);
                                }
                                localHolder.time.setText(dataBean.getGoods().getPubtime());
                                localHolder.store.setText(dataBean.getGoods().getManylike());
                            }catch (Exception e){
                                e.printStackTrace();
                            }

                        }
                    });

                }
            }
        });
        //设置下架商品点击事件
        localHolder.sellout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                //加载动画
                Popup.processPopupWindow(view);
                NetworkUtils.requestWithCookie(NetworkUtils.SELLOUT + data.get(i).getGood_id(), new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        networkFail();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String res = response.body().string();
                        final Result result = new Gson().fromJson(res,Result.class);
                        ActivityCollector.currentActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try{
                                    if(result.getCode()==212){
                                        Popup.easyPopup.dismiss();//取消加载动画
                                        data.remove(localHolder.getAdapterPosition());
                                        notifyItemRemoved(localHolder.getAdapterPosition());
                                        Popup.hintPopupWindow(view,"下架成功",Popup.HINT_TIME);
                                    }
                                }catch (Exception e){
                                    e.printStackTrace();
                                }

                            }
                        });
                        LogUtil.d(TAG,res);
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class LocalHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private TextView time;
        private TextView store;//收藏人数
        private LinearLayout sellout;
        private ImageView image1;
        private ImageView image2;
        LocalHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_trans_record_name);
            time = itemView.findViewById(R.id.item_trans_record_time);
            store =itemView.findViewById(R.id.item_trans_record_store);
            image1 = itemView.findViewById(R.id.item_trans_record_image1);
            image2 = itemView.findViewById(R.id.item_trans_record_image2);
            sellout = itemView.findViewById(R.id.item_trans_record_soldout);
        }
    }

    //给外部设置接口
    public void setOnItemClickListener(onItemClickListener onItemClickListener,int code){


    }

    //设置回调接口
    public interface onItemClickListener{
        void onItemClick(View view);
        void onItemLongClick(View view,int position);
    }

    //加载失败的情况
   private void networkFail(){
        ActivityCollector.currentActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Popup.easyPopup.dismiss();//取消加载动画
                Toast.makeText(context, "加载失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
