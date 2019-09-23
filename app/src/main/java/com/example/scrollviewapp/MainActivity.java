package com.example.scrollviewapp;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.WebView;
import android.widget.HorizontalScrollView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener, ViewTreeObserver.OnScrollChangedListener {

    AppCompatCheckBox chck_switch;
    HorizontalScrollView hsv_main;
    WebView wv_text;
    View shadow_start, shadow_end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chck_switch = (AppCompatCheckBox) findViewById(R.id.chck_switch);
        hsv_main = (HorizontalScrollView) findViewById(R.id.hsv_main);
        shadow_start = (View) findViewById(R.id.view_shadow_start);
        shadow_end = (View) findViewById(R.id.view_shadow_end);

        hsv_main.setOnTouchListener(this);
        hsv_main.getViewTreeObserver().addOnScrollChangedListener(this);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return chck_switch.isChecked();
    }

    @Override
    public void onScrollChanged() {
        View view = (View) hsv_main.getChildAt(hsv_main.getChildCount() - 1);
        int startDetector = hsv_main.getScrollX();
        int endDetector = view.getRight() -  (hsv_main.getWidth() + hsv_main.getScrollX());

        if(startDetector <= 0) {
            //Toast.makeText(getBaseContext(),"Scroll View start reached",Toast.LENGTH_SHORT).show();
            Log.d(MainActivity.class.getSimpleName(),"Horizontal Scroll View start reached");
            shadow_start.setVisibility(View.INVISIBLE);
        }
        else if(endDetector <= 0 ) {
            //Toast.makeText(getBaseContext(),"Scroll View bottom reached",Toast.LENGTH_SHORT).show();
            Log.d(MainActivity.class.getSimpleName(),"Scroll View bottom reached");
            shadow_end.setVisibility(View.INVISIBLE);
        }
        else {
            shadow_start.setVisibility(View.VISIBLE);
            shadow_end.setVisibility(View.VISIBLE);
        }
    }
}
