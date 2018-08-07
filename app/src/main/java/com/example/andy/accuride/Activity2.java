package com.example.andy.accuride;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

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

        /*
            This part of the program finds the path with the fastest time and path with the least transfers
         */
        Dijkstra dijkstra = new Dijkstra(this);
        try {
            dijkstra.run(start, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
            This part modifies Minutes, Transfers and Stops
         */
        TextView tab1Minutes = findViewById(R.id.tab1minutes);
        TextView tab1numOfTransfers = findViewById(R.id.tab1numOfTransfers);
        TextView tab1numOfStops = findViewById(R.id.tab1numOfStops);
        TextView tab2Minutes = findViewById(R.id.tab2minutes);
        TextView tab2numOfTransfers = findViewById(R.id.tab2numOfTransfers);
        TextView tab2numOfStops = findViewById(R.id.tab2numOfStops);

        //Minutes
        String fastestTime = dijkstra.fastestTime + " Minutes";
        String leastTransfersTime = dijkstra.leastTransfers + " Minutes";
        tab1Minutes.setText(fastestTime);
        tab2Minutes.setText(leastTransfersTime);

        //Transfers
        String numOfTransfersFastest = dijkstra.numOfTransfersFastest + " Transfers";
        String numOfTransfersLeast = dijkstra.numOfTransfersLeast + " Transfers";
        tab1numOfTransfers.setText(numOfTransfersFastest);
        tab2numOfTransfers.setText(numOfTransfersLeast);

        //Stops
        int numOfStopsFastest = dijkstra.fastestPath.size() - dijkstra.numOfTransfersFastest - 1;
        int numOfStopsLeast = dijkstra.leastTransferspath.size() - dijkstra.numOfTransfersLeast - 1;
        String numOfStopsFastestStr = Integer.toString(numOfStopsFastest) + " Stops";
        String numOfStopsLeastStr = Integer.toString(numOfStopsLeast) + " Stops";
        tab1numOfStops.setText(numOfStopsFastestStr);
        tab2numOfStops.setText(numOfStopsLeastStr);

        /*
            Setting the display path on the xml file.
         */
        setDisplayPath(dijkstra.fastestPath, dijkstra.numOfTransfersFastest, dijkstra.leastTransferspath, dijkstra.numOfTransfersLeast, start, destination);


        /*
            This part of the program finds the TextView Id at the top of the screen and modifies it.
         */
        TextView fromString = findViewById(R.id.start_string);
        fromString.setText(start);
        fromString.setVisibility(View.VISIBLE);
        TextView toString = findViewById(R.id.destination_string);
        toString.setText(destination);
        toString.setVisibility(View.VISIBLE);

        /*
            This part of the program finds the Tabhost Id and modifies it.
         */
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

    /**
     * This method is used to set the display path of the journey
     */
    private void setDisplayPath(ArrayList<String> arrFastest, int numOfTransfersFastest, ArrayList<String> arrLeast, int numOfTransfersLeast, String start, String destination) {
        RelativeLayout tab1 = findViewById(R.id.tab1);
        RelativeLayout tab2 = findViewById(R.id.tab2);
//
//        if (numOfTransfersFastest != 0) {
//            ArrayList<ImageView> fastestArrImage = new ArrayList<>();
//            ArrayList<TextView> fastestArrDash = new ArrayList<>();
//
//            for (int i = 0; i < numOfTransfersFastest; i++) {
//                //Creating new Buttons for the transfers
//                ImageView imageFastest = new ImageView(this);
//                imageFastest.setImageResource(R.mipmap.circle);
//                //Need to take into account pixel to dp conversion
//                imageFastest.setLayoutParams(new ViewGroup.LayoutParams(216, 100));
//                fastestArrImage.add(imageFastest);
//
//                //Creating new journey dashes "|" for the transfers
//                TextView dashFastest = new TextView(this);
//                dashFastest.setText("|");
//                dashFastest.setPadding(51, 0, 51, 0);
//                fastestArrDash.add(dashFastest);
//            }
//
//            for (int j = 0; j < fastestArrImage.size(); j++) {
//                ImageView imgView = fastestArrImage.get(j);
//                TextView textView = fastestArrDash.get(j);
//                RelativeLayout.LayoutParams textLayout = (RelativeLayout.LayoutParams) textView.getLayoutParams();
//
//
//                tab1.addView(imgView);
//                tab1.addView(textView);
//
//
////              RelativeLayout.LayoutParams params= new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
////              params.addRule(RelativeLayout.BELOW, R.id.below_id);
////              viewToLayout.setLayoutParams(params);
//            }
//
//        } else {
//            TextView startStationFastest = findViewById(R.id.startStop);
//            startStationFastest.setText(start);
//            TextView endStationFastest = findViewById(R.id.endStop);
//            endStationFastest.setText(destination);
//        }
//
//        if (numOfTransfersLeast != 0) {
//
//        } else {
//            TextView startStationLeast = findViewById(R.id.startStop2);
//            startStationLeast.setText(start);
//            TextView endStationLeast = findViewById(R.id.endStop2);
//            endStationLeast.setText(destination);
//        }
        String string1 = new String();
        for (String station: arrFastest) {
            string1 = string1 + station;
            if (station.equals(arrFastest.get(arrFastest.size() - 1))) {
                string1 = string1 + " ";
            } else {
                string1 = string1 + " ->";
            }
        }
        TextView textView1 = new TextView(this);
        textView1.setText(string1);
        tab1.addView(textView1);
        RelativeLayout.LayoutParams textLayout1 = (RelativeLayout.LayoutParams) textView1.getLayoutParams();
        textLayout1.addRule(RelativeLayout.CENTER_IN_PARENT);

        String string2 = new String();
        for (String station: arrLeast) {
            string2 = string2 + station;
            if (station.equals(arrLeast.get(arrLeast.size() - 1))) {
                string2 = string2 + " ";
            } else {
                string2 = string2 + " ->";
            }
        }
        TextView textView2 = new TextView(this);
        textView2.setText(string2);
        tab2.addView(textView2);
        RelativeLayout.LayoutParams textLayout2 = (RelativeLayout.LayoutParams) textView2.getLayoutParams();
        textLayout2.addRule(RelativeLayout.CENTER_IN_PARENT);
    }
}