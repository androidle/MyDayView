package com.thinkcoo.www.drawdemo;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;

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
                        Calendar calendar = Calendar.getInstance();
                        new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int day) {
                                // TODO Auto-generated method stub
                            }
                        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                                calendar.get(Calendar.DAY_OF_MONTH) ).show();

                        Log.e(TAG, "onHover: =======ACTION_UP====");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.e(TAG, "onHover: =======ACTION_MOVE====");
                        break;
                }


                Calendar calendar = Calendar.getInstance();
                new TimePickerDialog(MainActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hour, int minute) {

                            }
                        }, calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE), true).show();

                return true;
            }
        });
    }

    public void open(View view) {
        startActivity(new Intent(this,TestActivity.class));
    }
}
