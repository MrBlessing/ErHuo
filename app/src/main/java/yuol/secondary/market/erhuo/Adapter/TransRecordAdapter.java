package yuol.secondary.market.erhuo.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import yuol.secondary.market.erhuo.R;
import yuol.secondary.market.erhuo.Utils.ActivityCollector;

public class TransRecordAdapter extends RecyclerView.Adapter<TransRecordAdapter.LocalAdapter> {
    private Context context;
    private List<int> data;
    public TransRecordAdapter(List<int> data){
        this.data = data;
        context = ActivityCollector.currentActivity();
    }

    @NonNull
    @Override
    public LocalAdapter onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_trans_record,viewGroup);
        return new LocalAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocalAdapter localAdapter, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class LocalAdapter extends RecyclerView.ViewHolder{


        LocalAdapter(@NonNull View itemView) {
            super(itemView);

        }
    }

    //给外部设置接口
    public void setOnItemClickListener(GoodsAdapter.onItemClickListener onItemClickListener){
        listener = onItemClickListener;
    }

    //设置回调接口
    public interface onItemClickListener{
        void onItemClick(View view);
        void onItemLongClick(View view,int position);
    }
}
