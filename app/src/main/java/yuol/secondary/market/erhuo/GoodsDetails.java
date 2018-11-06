package yuol.secondary.market.erhuo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import de.hdodenhof.circleimageview.CircleImageView;
import yuol.secondary.market.erhuo.Utils.BasedActivity;
import yuol.secondary.market.erhuo.Utils.Popup;

public class GoodsDetails extends BasedActivity {
    private CircleImageView merchantInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_details);
        findView();
        Event();
    }

    private void Event() {
        setMerchantInfo();
    }

    private void setMerchantInfo() {
        final View popup = LayoutInflater.from(this).inflate(R.layout.popup_goods_details_info,null);
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
        merchantInfo = findViewById(R.id.item_goods_details_card_info);//卡片中卖家头像
    }
}
