package yuol.secondary.market.erhuo;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import yuol.secondary.market.erhuo.Utils.BasedActivity;
import yuol.secondary.market.erhuo.Utils.FileUtils;
import yuol.secondary.market.erhuo.Utils.KeyValueUtil;
import yuol.secondary.market.erhuo.Utils.LogUtil;
import yuol.secondary.market.erhuo.Utils.NetworkUtils;
import yuol.secondary.market.erhuo.Utils.Popup;
import yuol.secondary.market.erhuo.bean.Personal;
import yuol.secondary.market.erhuo.bean.Result;

public class Login extends BasedActivity {

    private RelativeLayout back;
    private Button submit;
    private static final String TAG = "Login";
    private EditText account;
    private EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findView();
        setEvent();
    }

    private void setEvent() {
        setBackEvent();
        login();
    }

    private void login() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //开始加载
                Popup.processPopupWindow(getWindow().getDecorView().getRootView());
                //获取输入框中的信息
                String accountText = account.getText().toString();
                String passwordText = password.getText().toString();
                checkText(accountText,passwordText);
                //检查输入是否有误
                if(checkText(accountText,passwordText)){
                    //post登陆
                    RequestBody requestBody = new FormBody.Builder()
                            .add("name", accountText)
                            .add("password", passwordText)
                            .build();

                    NetworkUtils.requestByPost(NetworkUtils.LOGIN, requestBody, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Login.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    //取消加载
                                    Popup.easyPopup.dismiss();
                                    Toast.makeText(Login.this, "请检查你的网络", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                        @Override
                        public void onResponse(Call call, final Response response) throws IOException {
                            String res = response.body().string();
                            //解析结果
                            LogUtil.d(TAG,res);
                            final Result result = new Gson().fromJson(res,Result.class);
                            Login.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    //获取信息
                                    if(result.getCode()==123){
                                        //当登陆成功以后，先查询用户数据，然后提示用户登陆成功
                                        getPersonalInfo();
                                    }else {
                                        //取消加载
                                        Popup.easyPopup.dismiss();
                                        Popup.hintPopupWindow(getWindow().getDecorView().getRootView(),"用户名或密码错误",Popup.HINT_TIME);
                                        //清除不合格的cookie
                                        FileUtils.saveByPreference(KeyValueUtil.COOKIE,"");
                                    }
                                }
                            });
                        }
                    });
                }
            }
        });
    }

    private boolean checkText(String a,String p) {
        return true;
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
        submit = findViewById(R.id.login_submit);
        account = findViewById(R.id.login_account);
        password = findViewById(R.id.login_password);
    }

    //取得登陆人信息
    private void getPersonalInfo(){
        NetworkUtils.requestWithCookie(NetworkUtils.PERSONAL_INFO, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    try{
                        Login.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //取消加载
                                Popup.easyPopup.dismiss();
                                Toast.makeText(Login.this, "请检查你的网络", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }catch (Exception e1){
                        e.printStackTrace();
                    }

                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    //保存用户整体信息和姓名
                    try{
                        String res = response.body().string();
                        if(!TextUtils.isEmpty(res)){
                            Personal personal = new Gson().fromJson(res,Personal.class);
                            String name = personal.getData().getNick();
                            FileUtils.saveByPreference(KeyValueUtil.USER_NAME,name);
                            //开始跳转页面
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    //取消加载
                                    Popup.easyPopup.dismiss();
                                    //显示登陆成功
                                    Popup.hintPopupWindow(getWindow().getDecorView().getRootView(),"登陆成功",Popup.HINT_TIME);
                                    finish();
                                }
                            });
                        }
                    }catch (Exception e1){
                        e1.printStackTrace();
                    }
                }
            });
    }
}
