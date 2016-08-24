package demo.mydayviewdemo;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Time;
import android.view.Menu;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity implements CalendarController.EventHandler {
    private int mDayNumbers = 7;
    private long timeMillis = System.currentTimeMillis();
    private ActionBar ab;
    private FragmentTransaction mFm;
    private MyDayViewFragment mWeekViewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWeekViewFragment = MyDayViewFragment.newInstance(timeMillis, mDayNumbers);
        mFm = getFragmentManager().beginTransaction();
        mFm.add(R.id.frame_layout, mWeekViewFragment).commit();



        CalendarController controller = CalendarController.getInstance(this);
        controller.setHandler(this);

        ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
//        ab.setTitle(myDayViewFragment.getCurrentTitle());
    }

    public void updateTitle(String title) {
            ab.setTitle(title);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void handleEvent(Time time) {
        FrameLayout container = (FrameLayout) findViewById(R.id.frame_layout_2);
        MyDayViewFragment myDayViewFragment = MyDayViewFragment.newInstance(time.toMillis(false), 7);
        getFragmentManager().beginTransaction().replace(R.id.frame_layout_2, myDayViewFragment).commit();

    }
}
