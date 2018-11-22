package yuol.secondary.market.erhuo.Utils;

import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NetworkUtils {
    public static final String UPDATE = "http://192.168.137.1/erhuo/api/update.xml";
    public static final String IMAGE_URL ="http://192.168.137.1/erhuo/api/img/ImageUrl.json";
    public static final String GOODS_INFO = "http://192.168.137.1/erhuo/api/erhuo.php";
    public static final String CATEGORY_GOODS = "http://192.168.137.1/erhuo/api/keyword.php?catname=";
    public static final String GOODS_DETAILS = "http://192.168.137.1/erhuo/api/details?good_id=";
    public static final String LOGIN = "http://192.168.137.1/erhuo/api/login";
    public static final String PERSONAL_INFO = "http://192.168.137.1/erhuo/api/personalcenter2.php";
    public static final String TRANS_RECORD = "http://192.168.137.1/erhuo/api/goodsno.php";
    public static final String MESSAGE = "http://192.168.137.1/erhuo/api/news.php";
    public static final String SELLOUT = "http://192.168.137.1/erhuo/api/sellout.php?good_id=";
    private static OkHttpClient client = new OkHttpClient();


    //该类使用OkHttp向服务器发出请求，可以获得相应的数据，此方法不用带参
    public static void request(String url,okhttp3.Callback callback){
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(callback);
    }


    //更新信息专门用的xml解析
    public static List<String> parseXML(String content){
        List<String> info = new ArrayList<>();
        try {
            XmlPullParserFactory factor = XmlPullParserFactory.newInstance();
            XmlPullParser pullParser = factor.newPullParser();
            //setInput要传入一个流，但是如果要传入普通流就要加入对应的编码格式
            //该方法还可以传入一个Reader流，不清楚这个流，StringReader(content)可以将字符串变成流的格式
            pullParser.setInput(new StringReader(content));
            int eventType = pullParser.getEventType();
            while(eventType != XmlPullParser.END_DOCUMENT){
                String tagName = pullParser.getName();
                switch (eventType){
                    case XmlPullParser.START_DOCUMENT :
                        //表示此时读取的状态在流的首端
                        break;

                    case XmlPullParser.START_TAG:
                        //表示到达标签的开始
                        if("version".equals(tagName)){
                            //当标签是version的时候将pullparse指向该标签的属性
                            eventType = pullParser.next();
                            //获取内容
                            info.add(pullParser.getText());
                        }
                        if("name".equals(tagName)){
                            eventType = pullParser.next();
                            info.add(pullParser.getText());
                        }
                        if("url".equals(tagName)){
                            eventType = pullParser.next();
                            info.add(pullParser.getText());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        //表示到达标签尾部
                        break;
                }
                eventType = pullParser.next();
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return info;
    }


    //提前加载图片Json数据,并保存
    public static void loadJson(String Url, final String key) {
        //向服务器请求数据JSON
        NetworkUtils.request(Url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                ActivityCollector.currentActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ActivityCollector.currentActivity(), "请检查你的网络", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                FileUtils.saveByPreference(key, response.body().string());
            }
        });
    }


    //post请求
    public static void requestByPost(String url,RequestBody body,Callback callback){
        final HashMap<String , List<Cookie>> cookieStore = new HashMap<>();
        OkHttpClient client = new OkHttpClient.Builder()
                .cookieJar(new CookieJar() {
                    @Override
                    public void saveFromResponse(@NonNull HttpUrl httpUrl, @NonNull List<Cookie> list) {
                        cookieStore.put("personalInfo", list);
                        //储存cookie
                        saveCookie(list);
                    }

                    @Override
                    public List<Cookie> loadForRequest(HttpUrl httpUrl) {
                        List<Cookie> cookies = cookieStore.get("personalInfo");
                        return cookies != null ? cookies : new ArrayList<Cookie>();
                    }
                })
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    //带着保存的cookie请求
    public static void requestWithCookie(String url,Callback callback){
        String cookie = PreferenceManager.getDefaultSharedPreferences(ActivityCollector.currentActivity()).getString(KeyValueUtil.COOKIE,null);
        if(!TextUtils.isEmpty(cookie)){
            LogUtil.d("Template",cookie);
            Request request = new Request.Builder()
                    .url(url)
                    .header("Cookie",cookie)
                    .build();
            client.newCall(request).enqueue(callback);
        }else{
            Popup.hintPopupWindow(ActivityCollector.currentActivity().getWindow().getDecorView().getRootView(),"请先登陆",1000);
        }

    }

    private static void saveCookie(List<Cookie> cookies){
        StringBuilder cookieContent = new StringBuilder("");
        for(Cookie cookie : cookies){
            if(!TextUtils.isEmpty(cookie.name())){
                cookieContent.append(cookie.name()+"="+cookie.value()+";");
            }
        }
        LogUtil.d("Login",cookieContent.toString());
        FileUtils.saveByPreference(KeyValueUtil.COOKIE,cookieContent.toString());
    }

}
