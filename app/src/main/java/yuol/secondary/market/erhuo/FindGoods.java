package yuol.secondary.market.erhuo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import yuol.secondary.market.erhuo.Fragments.Find;
import yuol.secondary.market.erhuo.Fragments.FindFail;
import yuol.secondary.market.erhuo.R;
import yuol.secondary.market.erhuo.Utils.BasedActivity;
import yuol.secondary.market.erhuo.bean.GoodsInfo;

public class FindGoods extends BasedActivity {

    private ImageView back;
    private EditText content;
    private RelativeLayout find;
    private ArrayList<GoodsInfo.DataBean> data;//总数据
    private ArrayList<GoodsInfo.DataBean> aim;//筛选结果
    private boolean res;//用于判断查找结果

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_goods);
        findView();
        setEvent();
    }

    private void findView() {
        back = findViewById(R.id.find_goods_back);
        content = findViewById(R.id.find_goods_content);
        find = findViewById(R.id.find_goods_find);
    }

    private void setEvent() {
        getFindEvent();
        back.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if(data!=null){
                   data.clear();
               }
               if(aim!=null){
                   aim.clear();
               }
               finish();
           }
        });
    }

    private void dataFilter(String text) {
        //相当于每次都会清空数据
        aim = new ArrayList<>();
        for(GoodsInfo.DataBean temp:data){
            if(temp.getName().contains(text)||text.contains(temp.getName())){
                aim.add(temp);
            }
        }
    }

    public boolean getData() {
        //获取商品信息
        //同时判断数据的大小，初步筛选数据
        Bundle info = getIntent().getExtras();
        if(info!=null){
            data =  (ArrayList<GoodsInfo.DataBean>)info.getSerializable("data") ;
            if(data!=null&&data.size()>0){
                return true;
            }
        }
        return false;
    }

    public void getFindEvent() {
        //初始化搜索结果
        if(getData()){
            //初始化碎片事务类
            find.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String text = content.getText().toString();
                    if(!TextUtils.isEmpty(text)){
                        dataFilter(text);
                        if(aim!=null&&aim.size()>0){
                            res = true;
                        }
                    }

                    //判断结果选择显示哪个页面
                    if(res){
                        getSupportFragmentManager().beginTransaction().replace(R.id.find_goods_container,Find.newInstance(aim)).commit();
                        //提交以后清除容器数据，将结果初始化
                        res = false;
                    }

                    else{
                        getSupportFragmentManager().beginTransaction().replace(R.id.find_goods_container,new FindFail() ).commit();
                    }
                }
            });
        }
    }
}
