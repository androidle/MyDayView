package demo.mydayviewdemo;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private int mDayNumbers = 7;
    private long timeMillis = System.currentTimeMillis();
    private ActionBar ab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyDayViewFragment myDayViewFragment = MyDayViewFragment.newInstance(timeMillis, mDayNumbers);
        getFragmentManager().beginTransaction().add(R.id.frame_layout, myDayViewFragment).commit();


        ab = getSupportActionBar();
//        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
//        ab.setDisplayHomeAsUpEnabled(true);
//        ab.setTitle(myDayViewFragment.getCurrentTitle());
    }

    public void updateTitle(String title) {
            ab.setTitle(title);
    }
}
