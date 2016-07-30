package demo.mydayviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private int mDayNumbers = 7;
    private long timeMillis = System.currentTimeMillis();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyDayViewFragment myDayViewFragment = MyDayViewFragment.newInstance(timeMillis, mDayNumbers);
        getFragmentManager().beginTransaction().add(R.id.frame_layout, myDayViewFragment).commit();

    }
}
