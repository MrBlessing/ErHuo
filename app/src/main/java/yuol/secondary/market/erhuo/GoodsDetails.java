package yuol.secondary.market.erhuo;

import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;
import yuol.secondary.market.erhuo.Utils.BasedActivity;
import yuol.secondary.market.erhuo.Utils.RecyclerUtils;
import yuol.secondary.market.erhuo.Utils.Popup;
import yuol.secondary.market.erhuo.bean.GoodsInfo;

public class GoodsDetails extends BasedActivity {
    private CircleImageView merchantInfo;//商家头像
    private View popup;//商家详细信息弹窗
    private RelativeLayout back;
    private TextView content;//商品描述
    private TextView time;//发布时间
    private TextView manyLike;//想要人数
    private TextView howNew;//新旧程度
    private TextView way;//交易方式
    private TextView goWhere;//交易地点
    private TextView bargain;//是否议价
    private TextView promise;//售后承诺
    private TextView contactText;//联系方式
    private RelativeLayout contact;//联系按钮
    private TextView wantGet;
    private TextView name;
    private TextView price;
    private TextView oldPrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_details);
        findView();
        Event();
    }

    private void Event() {
        setMerchantInfo();
        showData();
        setBack();
    }

    private void setBack() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void showData() {
        final GoodsInfo.DataBean info = (GoodsInfo.DataBean) getIntent().getExtras().getSerializable(RecyclerUtils.INFO);//获取上个页面传来的商品信息
        if(info!=null){
            content.setText(info.getContent());
            time.setText(info.getPubtime());
            manyLike.setText(info.getManylike());
            howNew.setText(info.getHownew());
            way.setText(info.getWay());
            goWhere.setText(info.getGwhere());
            bargain.setText(info.getBargin());
            promise.setText(info.getPromise());
            name.setText(info.getName());
            price.setText("￥"+info.getPrice());
            oldPrice.setText("￥"+info.getOldprice());
            if(info.getQqnum()!=null) contactText.setText("QQ");
            if(info.getWechat()!=null) contactText.setText("微信");
            if(info.getPhone()!=null) contactText.setText("手机");
            //展示联系方式

            contact.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ClipboardManager manager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData data=null;
                    switch(contactText.getText().toString()){
                        case "QQ" :
                            //先检查是否安装qq
                            data= ClipData.newPlainText("weChatNum",info.getQqnum().toString());
                            manager.setPrimaryClip(data);
                            Toast.makeText(GoodsDetails.this, "QQ号已放入剪切板", Toast.LENGTH_SHORT).show();
                            try{
                                String urlQQ = "mqqwpa://im/chat?chat_type=wpa&uin="+info.getQqnum().toString();
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlQQ)));
                            }catch (ActivityNotFoundException e){
                                e.printStackTrace();
                                Toast.makeText(GoodsDetails.this, "未安装QQ", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case "微信" :
                            data = ClipData.newPlainText("weChatNum",info.getWechat().toString());
                            manager.setPrimaryClip(data);
                            Toast.makeText(GoodsDetails.this, "微信号已放入剪切板", Toast.LENGTH_SHORT).show();
                            try{
                                Intent intent = new Intent(Intent.ACTION_MAIN);
                                ComponentName componentName = new ComponentName("com.tencent.mm","com.tencent.mm.ui.LauncherUI");
                                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.setComponent(componentName);
                                startActivity(intent);
                            }catch (ActivityNotFoundException e){
                                e.printStackTrace();
                                Toast.makeText(GoodsDetails.this, "未安装微信", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case "手机":
                            data = ClipData.newPlainText("weChatNum",info.getPhone());
                            manager.setPrimaryClip(data);
                            Toast.makeText(GoodsDetails.this, "手机号已放入剪切板", Toast.LENGTH_SHORT).show();
                            try{
                                Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+info.getPhone()));
                                startActivity(intent);
                            }catch (ActivityNotFoundException e){
                                e.printStackTrace();
                                Toast.makeText(GoodsDetails.this, "无法进行拨号", Toast.LENGTH_SHORT).show();
                            }
                            break;

                    }
                }
            });
        }
    }

    private void setMerchantInfo() {
        popup = LayoutInflater.from(this).inflate(R.layout.popup_goods_details_info,null);
        ImageView back = popup.findViewById(R.id.popup_goods_details_info);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Popup.easyPopup.dismiss();
            }
        });
        merchantInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Popup.hintPopupWindow(findViewById(R.id.goods_details_container),popup);
            }
        });
    }

    private void findView() {
        back = findViewById(R.id.goods_details_back);
        merchantInfo = findViewById(R.id.item_goods_details_card_info);
        content = findViewById(R.id.goods_details_content);
        time = findViewById(R.id.goods_details_time);
        manyLike = findViewById(R.id.goods_details_manyLike);
        howNew = findViewById(R.id.goods_details_howNew);
        way = findViewById(R.id.goods_details_way);
        goWhere = findViewById(R.id.goods_details_goWhere);
        bargain = findViewById(R.id.goods_details_bargain);
        promise = findViewById(R.id.goods_details_promiss);
        contactText = findViewById(R.id.goods_details_contact_text);
        contact = findViewById(R.id.goods_details_contact);
        name = findViewById(R.id.item_goods_details_card_name);
        price = findViewById(R.id.item_goods_details_card_price);
        oldPrice = findViewById(R.id.item_goods_details_card_oldPrice);
    }
}
