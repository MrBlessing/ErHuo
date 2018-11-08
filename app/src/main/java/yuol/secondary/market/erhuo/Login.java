package yuol.secondary.market.erhuo;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import yuol.secondary.market.erhuo.Utils.BasedActivity;

public class Login extends BasedActivity {

    private RelativeLayout back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findView();
        setEvent();
    }

    private void setEvent() {
        setBackEvent();
    }

    private void setBackEvent() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void findView() {
        back = findViewById(R.id.login_back);
    }
}
