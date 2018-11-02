package yuol.secondary.market.erhuo.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import yuol.secondary.market.erhuo.Login;
import yuol.secondary.market.erhuo.R;
import yuol.secondary.market.erhuo.Utils.ActivityCollector;

public class PersonalCenterFragment extends Fragment {
private View view;
private Context context= ActivityCollector.currentActivity();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_personal_center, container, false);
        setEvent();
        final FrameLayout login = view.findViewById(R.id.personal_center_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Login.class);
                context.startActivity(intent);
            }
        });
        return view;
    }

    private void setEvent() {

    }

}
