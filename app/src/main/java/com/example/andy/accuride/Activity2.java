package com.example.andy.accuride;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

/**
 * This class  when the user clicks "Go" after entering
 * an input in both "From" and "To" boxes
 * @author Andy Chan
 */
public class Activity2 extends AppCompatActivity {

    /**
     * This method displays the content of the application at this page.
     * Image taken from the activity_2 xml file.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        String start = getIntent().getStringExtra("fromBox");
        String destination = getIntent().getStringExtra("toBox");

        TextView fromString = findViewById(R.id.start_string);
        fromString.setText(start);
        fromString.setVisibility(View.VISIBLE);

        TextView toString = findViewById(R.id.destination_string);
        toString.setText(destination);
        toString.setVisibility(View.VISIBLE);

        TabHost tabHost = findViewById(android.R.id.tabhost);
        tabHost.setup();
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("Fastest", null).setContent(R.id.tab1));
        tabHost.addTab(tabHost.newTabSpec("tag2").setIndicator("Least Transfers", null).setContent(R.id.tab2));
    }
}
