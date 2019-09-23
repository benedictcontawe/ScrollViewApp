package com.example.scrollviewapp;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.WebView;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener, ViewTreeObserver.OnScrollChangedListener {

    AppCompatCheckBox chck_switch;
    ScrollView sv_main;
    WebView wv_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chck_switch = (AppCompatCheckBox) findViewById(R.id.chck_switch);
        sv_main = (ScrollView) findViewById(R.id.sv_main);
        wv_text = (WebView) findViewById(R.id.wv_text);

        sv_main.setOnTouchListener(this);
        sv_main.getViewTreeObserver().addOnScrollChangedListener(this);

        wv_text.loadData(getResources().getString(R.string.lorem_ipsum),"text/html",null);
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

        if(bottomDetector <= 0 ){
            Toast.makeText(getBaseContext(),"Scroll View bottom reached",Toast.LENGTH_SHORT).show();
        }
        if(topDetector <= 0){
            Toast.makeText(getBaseContext(),"Scroll View top reached",Toast.LENGTH_SHORT).show();
        }
    }
}
