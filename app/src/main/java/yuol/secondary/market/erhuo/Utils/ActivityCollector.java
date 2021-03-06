package yuol.secondary.market.erhuo.Utils;

import android.app.Activity;

import java.util.LinkedList;

public class ActivityCollector {
    private static LinkedList<Activity> activities = new LinkedList<>();
    public static void addActivity(Activity activity){
        activities.add(activity);
    }
    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }
    public static void finishall(){
        for(Activity activity : activities){
            activities.remove(activity);
            activity.finish();
        }
    }
    public static Activity currentActivity(){
        if(activities != null){
            Activity activity=activities.getLast();
            return activity;
        }
      return null;
    }
}
