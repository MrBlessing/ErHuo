package yuol.secondary.market.erhuo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import yuol.secondary.market.erhuo.Utils.BasedActivity;
import yuol.secondary.market.erhuo.Utils.FileUtils;
import yuol.secondary.market.erhuo.Utils.KeyValueUtil;
import yuol.secondary.market.erhuo.Utils.LogUtil;
import yuol.secondary.market.erhuo.Utils.NetworkUtils;
import yuol.secondary.market.erhuo.bean.ImageUrl;
import yuol.secondary.market.erhuo.bean.Personal;

public class StartActivity extends BasedActivity {

    //限时跳转
    private CountDownTimer timer = new CountDownTimer(300,3000) {
        @Override
        public void onTick(long l) {

        }
        @Override
        public void onFinish() {
            Intent intent = new Intent(StartActivity.this,HomePage.class);
            startActivity(intent);
            finish();
        }
    };
    private static final String TAG = "StartActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        load();
        loadStartImage();
        applyForPermission();
    }

    private void loadStartImage() {
        ImageView image = findViewById(R.id.start_image);
        //获得已经储存好的json数据
        String json = PreferenceManager.getDefaultSharedPreferences(this).getString("Json_imageUrl",null);
        if(json!=null){
            //解析json数据
            Gson gson = new Gson();
            LogUtil.d(TAG,json);
            ImageUrl imageUrl = gson.fromJson(json,ImageUrl.class);
            Glide.with(this)
                    .load(imageUrl.getStart())
                    .into(image);
        }else {
            //保存的数据为空的话再次请求一遍数据
            load();
        }
    }

    private void load() {
        ////提前加载图片Json数据,并保存
        NetworkUtils.loadJson(NetworkUtils.IMAGE_URL,"Json_imageUrl");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
                if(event.getAction() == KeyEvent.ACTION_DOWN){
                    Toast.makeText(this, "正在加载中", Toast.LENGTH_SHORT).show();
                    return true;
                }break;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void applyForPermission() {
        List<String> permissionList = new ArrayList<>();
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        if(!permissionList.isEmpty()){
            String[] permission =  permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(this,permission,1);
        }else {
            //如果权限通过开始跳转页面
            timer.start();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if(grantResults.length>0){
                    for(int res : grantResults){
                        if(res != PackageManager.PERMISSION_GRANTED){
                            Toast.makeText(this, "需要全部权限才能使用该应用", Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                    timer.start();
                }
        }
    }


//    //检测登陆状态，获取登陆用户信息
//    public void getPersonalInfo() {
//        String cookie = PreferenceManager.getDefaultSharedPreferences(StartActivity.this).getString(KeyValueUtil.COOKIE,null);
//        NetworkUtils.requestWithCookie(NetworkUtils.PERSONAL_INFO,cookie, new Callback() {
//                @Override
//                public void onFailure(Call call, IOException e) {
//
//                }
//
//                @Override
//                public void onResponse(Call call, Response response) throws IOException {
//                    //保存用户整体信息和姓名
//                    String res = response.body().string();
//                    if(!TextUtils.isEmpty(res)){
//                        FileUtils.saveByPreference(KeyValueUtil.USER_INFO,res);
//                        Personal personal = new Gson().fromJson(res,Personal.class);
//                        String name = personal.getData().getNick();
//                        FileUtils.saveByPreference(KeyValueUtil.USER_NAME,name);
//                    }
//
//                }
//            });
//    }
}
