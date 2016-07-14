package com.thinkcoo.www.drawdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btn = (Button) findViewById(R.id.btn);
        btn.setOnHoverListener(new View.OnHoverListener() {
            @Override
            public boolean onHover(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_HOVER_ENTER:
                        Log.e(TAG, "onHover: ======ACTION_HOVER_ENTER====" );
                        break;
                    case MotionEvent.ACTION_HOVER_MOVE:
                        Log.e(TAG, "onHover: =======ACTION_HOVER_MOVE====");
                        break;
                    case MotionEvent.ACTION_HOVER_EXIT:
                        Log.e(TAG, "onHover: =======ACTION_HOVER_EXIT====");
                        break;
                }
                return false;
            }
        });

        btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.e(TAG, "onHover: =======ACTION_DOWN====");
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.e(TAG, "onHover: =======ACTION_UP====");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.e(TAG, "onHover: =======ACTION_MOVE====");
                        break;
                }
                return true;
            }
        });
    }
}
