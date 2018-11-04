package yuol.secondary.market.erhuo.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import yuol.secondary.market.erhuo.R;

public class SortRecyclerAdapter extends RecyclerView.Adapter<SortRecyclerAdapter.LocalHolder>{
    private List<String> data ;
    private onItemClickListener listener;
    //每个列表的页面
    public SortRecyclerAdapter(List<String> data){
        this.data = data;
    }

    @NonNull
    @Override
    public LocalHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.popup_release_sort_item,viewGroup,false);
        return new LocalHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final LocalHolder localHolder,  int i) {
        final int index = localHolder.getAdapterPosition();
        localHolder.tag.setText(data.get(i));
        localHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener!=null){
                    //调用监听器方法
                    listener.onItemClick(localHolder.itemView,index,data.get(index));
                }
            }
        });
        localHolder.container.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(listener!=null){
                    //调用监听器方法
                    listener.onItemLongClick(localHolder.itemView,index);
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    //用于外部设置监听器
    public void setOnItemClickListener(SortRecyclerAdapter.onItemClickListener onItemClickListener){
        listener = onItemClickListener;
    }

    class LocalHolder extends RecyclerView.ViewHolder {
        TextView tag;
        RelativeLayout container;
        LocalHolder(@NonNull View itemView) {
            super(itemView);
            tag = itemView.findViewById(R.id.popup_release_sort_item_tag);
            container=itemView.findViewById(R.id.popup_release_sort_item_container);
        }
    }


    //设置回调接口
    public interface onItemClickListener{
        void onItemClick(View view,int position,String data);
        void onItemLongClick(View view,int position);
    }
}
