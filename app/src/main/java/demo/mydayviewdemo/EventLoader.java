package demo.mydayviewdemo;

import android.content.Context;

import java.util.ArrayList;


/**
 * Created by Leevin
 * CreateTime: 2016/7/12  10:39
 */
public class EventLoader {

    private Context mContext;

    public EventLoader(Context context) {
      mContext = context;
    }

    public  ArrayList<Event> loadEvents(int mNumDays, int mFirstJulianDay) {
        ArrayList<Event> mEventsList = new ArrayList<Event>();
        Event event = new Event();
        long endMillis = 1468213200000L;
        long  startMillis= 1468209600000L;
        event.setStartMillis(startMillis);
        event.setEndMillis(endMillis);
        event.startDay = 2457581;
        event.endDay = 2457581;
        event.startTime = 720;
        event.endTime = 780;
        event.title = "hello world";
        event.location = "西安市科技路";
        event.color = mContext .getResources().getColor(R.color.calendar_date_range_color);;
        mEventsList.add(event);

//        Time time = new Time();
//        long currentMillis = System.currentTimeMillis();
//        time.set(currentMillis);
//        int julianDay = Time.getJulianDay(currentMillis, time.gmtoff);
//        Event event1 = new Event();
//        event1.endTime = time.minute + time.hour *60;
//        event1.startTime = event1.endTime - 3*60;
//        event1.startDay = julianDay;
//        event1.endDay = julianDay;
//        event1.startMillis = currentMillis;
//        event1.endMillis = currentMillis + 1000*60*60*3;
//        event1.title = "java";
//        event1.color = mContext.getResources().getColor(R.color.calendar_date_banner_text_color);
//        mEventsList.add(event1);
        return mEventsList;
    }
}
