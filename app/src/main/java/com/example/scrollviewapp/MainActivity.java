package com.example.scrollviewapp;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.WebView;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener, ViewTreeObserver.OnScrollChangedListener {

    AppCompatCheckBox chck_switch;
    ScrollView sv_main;
    WebView wv_text;
    View shadow_top, shadow_bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chck_switch = (AppCompatCheckBox) findViewById(R.id.chck_switch);
        sv_main = (ScrollView) findViewById(R.id.sv_main);
        shadow_top = (View) findViewById(R.id.view_shadow_top);
        shadow_bottom = (View) findViewById(R.id.view_shadow_bottom);
        wv_text = (WebView) findViewById(R.id.wv_text);

        sv_main.setOnTouchListener(this);


        wv_text.loadData(getResources().getString(R.string.lorem_ipsum),"text/html",null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sv_main.scrollTo(0,0);
        sv_main.getViewTreeObserver().addOnScrollChangedListener(this);
        shadow_top.setVisibility(View.INVISIBLE);
        shadow_bottom.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sv_main.getViewTreeObserver().removeOnScrollChangedListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return chck_switch.isChecked();
    }

    @Override
    public void onScrollChanged() {
        View view = (View) sv_main.getChildAt(sv_main.getChildCount() - 1);
        int topDetector = sv_main.getScrollY();
        int bottomDetector = view.getBottom() -  (sv_main.getHeight() + sv_main.getScrollY());

        if(topDetector <= 0) {
            //Toast.makeText(getBaseContext(),"Scroll View top reached",Toast.LENGTH_SHORT).show();
            Log.d(MainActivity.class.getSimpleName(),"Scroll View top reached");
            shadow_top.setVisibility(View.INVISIBLE);
        }
        else if(bottomDetector <= 0 ) {
            //Toast.makeText(getBaseContext(),"Scroll View bottom reached",Toast.LENGTH_SHORT).show();
            Log.d(MainActivity.class.getSimpleName(),"Scroll View bottom reached");
            shadow_bottom.setVisibility(View.INVISIBLE);
        }
        else {
            shadow_top.setVisibility(View.VISIBLE);
            shadow_bottom.setVisibility(View.VISIBLE);
        }
    }
}
