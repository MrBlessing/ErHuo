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

import java.util.List;

import yuol.secondary.market.erhuo.R;
import yuol.secondary.market.erhuo.Utils.ActivityCollector;
import yuol.secondary.market.erhuo.bean.GoodsInfo;

public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.LocalAdapter> {
    private onItemClickListener listener;
    private List<GoodsInfo> data;
    private Context context = ActivityCollector.currentActivity();

    public GoodsAdapter(List<GoodsInfo> goodsAdapters){
        this.data = goodsAdapters;
    }
    @NonNull
    @Override
    public LocalAdapter onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(ActivityCollector.currentActivity()).inflate(R.layout.item_home_goods,viewGroup,false);
        return new LocalAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocalAdapter localAdapter, int i) {
        localAdapter.price.setText(data.get(i).getPrice());
        localAdapter.title.setText(data.get(i).getName());
        Glide.with(context).load(data.get(i).getImage()).into(localAdapter.imageView);
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
        void onItemClick(View view,int position,String data);
        void onItemLongClick(View view,int position);
    }

    public class LocalAdapter extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView title;
        private TextView price;
        public LocalAdapter(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_home_goods_image);
            title = itemView.findViewById(R.id.item_home_goods_title);
            price = itemView.findViewById(R.id.item_home_goods_price);
        }
    }
}