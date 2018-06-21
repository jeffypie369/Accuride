package com.example.andy.accuride;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

/**
 * Displayed when the user clicks "Go" after entering
 * an input for both "From" and "To"
 */
public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        //Bug here
        String start = getIntent().getStringExtra("fromBox");
        String destination = getIntent().getStringExtra("toBox");

        TextView fromString = findViewById(R.id.start);
        fromString.setText(start);
        fromString.setVisibility(View.VISIBLE);

        TextView toString = findViewById(R.id.destination);
        toString.setText(destination);
        toString.setVisibility(View.VISIBLE);

        TabHost tabHost = findViewById(android.R.id.tabhost);
        tabHost.setup();
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("Fastest", null).setContent(R.id.tab1));
        tabHost.addTab(tabHost.newTabSpec("tag2").setIndicator("Least Transfers", null).setContent(R.id.tab2));

//        TabHost.TabSpec tab1 = tabHost.newTabSpec("First Tab");
//        TabHost.TabSpec tab2 = tabHost.newTabSpec("Second Tab");

//        tab1.setIndicator("Tab1");
//        tab1.setContent(new Intent(this,TabActivity1.class));
//
//        tab2.setIndicator("Tab2");
//        tab2.setContent(new Intent(this,TabActivity2.class));
//
//        tabHost.addTab(tab1);
//        tabHost.addTab(tab2);

//        TabHost mTabHost = findViewById(R.id.tabs);
//        mTabHost.setup();
//        mTabHost.addTab(mTabHost.newTabSpec("tab0").setIndicator("title1", null).setContent(R.id.Fastest));
//        mTabHost.addTab(mTabHost.newTabSpec("tab1").setIndicator("title2", null).setContent(R.id.tab2));

    }
}
