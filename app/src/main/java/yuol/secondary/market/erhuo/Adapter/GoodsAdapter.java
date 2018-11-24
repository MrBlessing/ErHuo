package yuol.secondary.market.erhuo.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import yuol.secondary.market.erhuo.R;
import yuol.secondary.market.erhuo.Utils.ActivityCollector;
import yuol.secondary.market.erhuo.Utils.NetworkUtils;
import yuol.secondary.market.erhuo.bean.GoodsInfo;
import yuol.secondary.market.erhuo.bean.GoodsInfo_brief;

public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.LocalAdapter> {
    private onItemClickListener listener;
    private List<GoodsInfo_brief.DataBean.GoodsBean> data;
    private Context context;

    public GoodsAdapter(List<GoodsInfo_brief.DataBean.GoodsBean> goodsAdapters){
        context = ActivityCollector.currentActivity();
        data = goodsAdapters;
    }
    @NonNull
    @Override
    public LocalAdapter onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(ActivityCollector.currentActivity()).inflate(R.layout.item_home_goods,viewGroup,false);
        return new LocalAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final LocalAdapter localAdapter, int i) {
        final GoodsInfo_brief.DataBean.GoodsBean info = data.get(i);
        localAdapter.price.setText(info.getPrice());
        localAdapter.title.setText(info.getName());
        Glide.with(context).load(NetworkUtils.IP+info.getPic()).into(localAdapter.imageView);
        localAdapter.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(localAdapter.itemView, info.getGood_id());
            }
        });
        localAdapter.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                listener.onItemLongClick(localAdapter.itemView,localAdapter.getAdapterPosition());
                return  true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    //用于外部设置监听器

    public void setOnItemClickListener(GoodsAdapter.onItemClickListener onItemClickListener){
        listener = onItemClickListener;
    }

    //设置回调接口
    public interface onItemClickListener{
        void onItemClick(View view,String goods_id);
        void onItemLongClick(View view,int position);
    }

    class LocalAdapter extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView title;
        private TextView price;
        LocalAdapter(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_home_goods_image);
            title = itemView.findViewById(R.id.item_home_goods_title);
            price = itemView.findViewById(R.id.item_home_goods_price);
        }
    }
}
