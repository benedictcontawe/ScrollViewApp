package com.example.scrollviewapp;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener, ViewTreeObserver.OnScrollChangedListener {

    CheckBox chck_switch;
    NestedScrollView nsv_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chck_switch = (CheckBox) findViewById(R.id.chck_switch);
        nsv_main = (NestedScrollView) findViewById(R.id.nsv_main);

        nsv_main.setOnTouchListener(this);
        nsv_main.getViewTreeObserver().addOnScrollChangedListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return chck_switch.isChecked();
    }

    @Override
    public void onScrollChanged() {
        View view = (View) nsv_main.getChildAt(nsv_main.getChildCount() - 1);
        int topDetector = nsv_main.getScrollY();
        int bottomDetector = view.getBottom() -  (nsv_main.getHeight() + nsv_main.getScrollY());

        if(bottomDetector <= 0 ){
            Toast.makeText(getBaseContext(),"Nested Scroll View bottom reached",Toast.LENGTH_SHORT).show();
        }
        if(topDetector <= 0){
            Toast.makeText(getBaseContext(),"Nested Scroll View top reached",Toast.LENGTH_SHORT).show();
        }
    }
}
