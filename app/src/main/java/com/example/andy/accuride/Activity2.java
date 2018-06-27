package com.example.andy.accuride;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;

/**
 * This class is used when the user clicks "Go" after entering
 * an input in both "From" and "To" boxes
 * @author Andy Chan
 */
public class Activity2 extends AppCompatActivity {
    Button cardfare_button;

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

        TabHost.TabSpec tabSpec1 = tabHost.newTabSpec("tab1");
        tabSpec1.setIndicator("Fastest", null).setContent(R.id.tab1);
        TabHost.TabSpec tabSpec2 = tabHost.newTabSpec("tab2");
        tabSpec2.setIndicator("Least Transfers", null).setContent(R.id.tab2);
        tabHost.addTab(tabSpec1);
        tabHost.addTab(tabSpec2);

        cardfare_button = findViewById(R.id.cardfareButton);
        cardfare_button.setOnClickListener(x -> {
            openCardfareActivity();
        });
    }

    /**
     * This method is used to open CardfareActivity.class
     */
    public void openCardfareActivity() {
        Intent intent = new Intent(this, CardfareActivity.class);
        startActivity(intent);
    }
}
