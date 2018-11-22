package yuol.secondary.market.erhuo.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import yuol.secondary.market.erhuo.R;
import yuol.secondary.market.erhuo.Utils.ActivityCollector;
import yuol.secondary.market.erhuo.bean.MessageBean;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.LocalAdapter> {
    private List<MessageBean.DataBean> data;

    public MessageAdapter(List<MessageBean.DataBean> d){
        data = d;
    }

    @NonNull
    @Override
    public LocalAdapter onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = ActivityCollector.currentActivity();
        View view = LayoutInflater.from(context).inflate(R.layout.item_message,viewGroup,false);
        return new LocalAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocalAdapter localAdapter, int i) {
        localAdapter.title.setText(data.get(i).getTitle());
        localAdapter.time.setText(data.get(i).getPubtime());
        localAdapter.content.setText(data.get(i).getContent());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class LocalAdapter extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView time;
        private TextView content;
        LocalAdapter(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_message_title);
            time = itemView.findViewById(R.id.item_message_time);
            content = itemView.findViewById(R.id.item_message_content);
        }
    }
}
