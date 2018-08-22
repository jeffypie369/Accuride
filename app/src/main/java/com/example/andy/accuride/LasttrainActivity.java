package com.example.andy.accuride;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

/**
 * This class handles the activities in the page with the list of train lines.
 * @author Andy Chan
 */
public class LasttrainActivity extends Activity {
    Button EW_button;
    Button NS_button;
    Button NE_button;
    Button CC_button;
    Button DT_button;
    Button LRT_button;

    /**
     *  This method renders the page taken from activity_lasttrain.xml
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lasttrain);

        EW_button = findViewById(R.id.EWLine);
        NS_button = findViewById(R.id.NSLine);
        NE_button = findViewById(R.id.NELine);
        CC_button = findViewById(R.id.CCLine);
        DT_button = findViewById(R.id.DTLine);
        LRT_button = findViewById(R.id.LRTLine);

        EW_button.setOnClickListener(v -> {
            openEW();
        });

        NS_button.setOnClickListener(v -> {
            openNS();
        });

        NE_button.setOnClickListener(v -> {
            openNE();
        });

        CC_button.setOnClickListener(v -> {
            openCC();
        });

        DT_button.setOnClickListener(v -> {
            openDT();
        });

        LRT_button.setOnClickListener(v -> {
            openLRT();
        });
    }

    /**
     *
     * The methods below are used to open up the train lines
     * the user wants to see for the last train timings.
     *
     */

    private void openEW() {
        Intent intent = new Intent(this, lastEWtimingsActivity.class);
        startActivity(intent);
    }

    private void openNS() {
        Intent intent = new Intent(this, lastNStimingsActivity.class);
        startActivity(intent);
    }

    private void openNE() {
        Intent intent = new Intent(this, lastNEtimingsActivity.class);
        startActivity(intent);
    }

    private void openCC() {
        Intent intent = new Intent(this, lastCCtimingsActivity.class);
        startActivity(intent);
    }

    private void openDT() {
        Intent intent = new Intent(this, lastDTtimingsActivity.class);
        startActivity(intent);
    }

    private void openLRT() {
        Intent intent = new Intent(this, lastLRTtimingsActivity.class);
        startActivity(intent);
    }
}
