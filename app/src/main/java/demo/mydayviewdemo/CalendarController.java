package demo.mydayviewdemo;

import android.content.Context;
import android.text.format.DateUtils;
import android.text.format.Time;

import java.util.WeakHashMap;

/**
 * Created by Leevin
 * CreateTime: 2016/7/12  8:29
 */
public class CalendarController {

    private Context mContext;
    private static WeakHashMap<Context, CalendarController> instances =
            new WeakHashMap<Context, CalendarController>();
    /**
     * Creates and/or returns an instance of CalendarController associated with
     * the supplied context. It is best to pass in the current Activity.
     *
     * @param context The activity if at all possible.
     */
    public static CalendarController getInstance(Context context) {
        synchronized (instances) {
            CalendarController controller = instances.get(context);
            if (controller == null) {
                controller = new CalendarController(context);
                instances.put(context, controller);
            }
            return controller;
        }
    }

    private CalendarController(Context context) {
        mContext = context;
//        mUpdateTimezone.run();
        mTime.setToNow();
    }

    /**
     * Removes an instance when it is no longer needed. This should be called in
     * an activity's onDestroy method.
     *
     * @param context The activity used to create the controller
     */
    public static void removeInstance(Context context) {
        instances.remove(context);
    }

    private Time mTime = new Time();

    public Time getTime() {
        return mTime;
    }

    public void setTime(long timeMillis) {
        mTime.set(timeMillis);
    }


    public void sendEvent(Context mContext, Time start, Time end, int formatFlags) {
        long startMillis = start.toMillis(true);
         mTitle = DateUtils.formatDateTime(mContext, startMillis, formatFlags);
        if (mContext instanceof MainActivity ) {
          ((MainActivity) mContext).updateTitle(mTitle);
        }
    }

    public String mTitle;
}
