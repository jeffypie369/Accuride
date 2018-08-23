package com.example.andy.accuride;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;


public class MapActivity extends AppCompatActivity {

    FrameLayout fl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        ZoomLayout myZoomView = new ZoomLayout(MapActivity.this);

        fl = findViewById(R.id.mrt_map);
        fl.addView(myZoomView);
    }

}
