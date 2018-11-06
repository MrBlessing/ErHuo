package yuol.secondary.market.erhuo.Utils;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import yuol.secondary.market.erhuo.Adapter.GoodsAdapter;
import yuol.secondary.market.erhuo.GoodsDetails;
import yuol.secondary.market.erhuo.bean.GoodsInfo;

public class GoodsList {
    public static void setGoodsList(final Context context, RecyclerView recyclerView, List<GoodsInfo.DataBean> data){

        GoodsAdapter adapter = new GoodsAdapter(data);
        GridLayoutManager manager = new GridLayoutManager(context,2);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        //设置每一个item的点击事件
        adapter.setOnItemClickListener(new GoodsAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(context, GoodsDetails.class);
                context.startActivity(intent);
            }
            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }
}
