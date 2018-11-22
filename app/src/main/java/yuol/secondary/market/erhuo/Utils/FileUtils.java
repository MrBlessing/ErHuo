package yuol.secondary.market.erhuo.Utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class FileUtils {
    public static void saveByPreference (String key,String value){

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ActivityCollector.currentActivity());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key,value);
        editor.apply();
    }
}
