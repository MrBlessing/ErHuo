package yuol.secondary.market.erhuo.autoUpdate;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v4.content.LocalBroadcastManager;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;
import yuol.secondary.market.erhuo.Utils.KeyValueUtil;
import yuol.secondary.market.erhuo.Utils.LogUtil;
import yuol.secondary.market.erhuo.Utils.NetworkUtils;

public class AutoUpdate {
    private static final String TAG="AutoUpdate";

    //发送请求，获取包含版本信息的xml文件
    public static void Update(final Context context, final LocalBroadcastManager LBmanager){

        NetworkUtils.request(NetworkUtils.UPDATE, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogUtil.d(TAG,"网络链接失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();
                //解析更新文档
                List<String> versionInfo  = NetworkUtils.parseXML(res);
                //获取当前应用版本
                String versionNow=null;
                PackageManager manager;
                PackageInfo packageInfo;
                try {
                    manager = context.getPackageManager();
                    packageInfo = manager.getPackageInfo(context.getPackageName(),0);
                    versionNow = packageInfo.versionName;
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }

                //判断是否有版本更新
               if(!versionInfo.get(0).equals(versionNow)){
                   Intent intent = new Intent(KeyValueUtil.BROADCAST_UPDATE);

                   intent.putExtra("state",true);
                   intent.putExtra("url",versionInfo.get(2));
                   LBmanager.sendBroadcast(intent);
               }else{
                   Intent intent = new Intent(KeyValueUtil.BROADCAST_UPDATE);
                   intent.putExtra("state",false);
                   LBmanager.sendBroadcast(intent);
               }
            }
        });


    }


}
