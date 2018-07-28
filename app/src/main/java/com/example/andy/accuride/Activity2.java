package com.example.andy.accuride;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;

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

        setDisplayPath();

        //This part of the program finds the TextView Id at the top of the screen and modifies it.
        TextView fromString = findViewById(R.id.start_string);
        fromString.setText(start);
        fromString.setVisibility(View.VISIBLE);
        TextView toString = findViewById(R.id.destination_string);
        toString.setText(destination);
        toString.setVisibility(View.VISIBLE);

        //This part of the program finds the Tabhost Id and modifies it.
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
    private void setDisplayPath() {
        ArrayList<String> path = new ArrayList<>();
        path.add("Aljunied");
        path.add("Paya Lebar");
        path.add("Eunos");

        //Need to edit this
//        RelativeLayout layout = findViewById(R.id.relative_layout);
//        ImageView imageView = new ImageView(this);
//        imageView.setImageResource(R.drawable.circle);
//        layout.addView(imageView);
//        imageView.getLayoutParams().height = 90;
    }
}



//        //Bug here
//        String from = "EW " + start;
//        String to = "EW " + destination;
//
//        String test1 = "EW Buona Vista";
//        String test2 = "DT Fort Canning";
//
//        int count = 0; 											 // count gives a unique value to each station
//        ArrayList<ArrayList<Edge>> adjList = new ArrayList<>();
//        HashMap<String, Integer> hmap = new HashMap<>();  		 // First hashmap for station name as key and count as value
//        HashMap<Integer, String> hmap2 = new HashMap<>();		 // Second hashmap for count as key and station name as value
//        for (int i = 0; i < 183; i++) {
//            adjList.add(new ArrayList<>()); 					 // create adjList, 183 stations
//        }
//
//        try {
//            BufferedReader br = new BufferedReader(new InputStreamReader(getAssets().open("EdgesTimeWait.txt")));
//            String line;
//
//            while ((line = br.readLine()) != null) {
//                // Adds all stations into hashmaps. If station is not in yet, add it
//                String stationFrom = line;
//                if (!hmap.containsKey(stationFrom)) {
//                    hmap.put(stationFrom, count);
//                    hmap2.put(count, stationFrom);
//                    count++;
//                }
//                String stationTo = br.readLine();
//                if (!hmap.containsKey(stationTo)) {
//                    hmap.put(stationTo, count);
//                    hmap2.put(count, stationTo);
//                    count++;
//                }
//                int time = br.read();
//                br.readLine();
//                int fromKey = hmap.get(stationFrom);
//                int toKey = hmap.get(stationTo);
//                // Adds edges to adjList
//                adjList.get(fromKey).add(new Edge(fromKey, toKey, time));
//                adjList.get(toKey).add(new Edge(toKey, fromKey, time));
//            }
//            br.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        //Possible bug in this part
//        Dijkstra dijkstra = new Dijkstra();
//        Log.d("Before try catch","Before try catch");
//        try {
//            Log.d("In try", String.valueOf(dijkstra.timeTaken));
//            dijkstra.run(test1, test2, hmap, hmap2, adjList);
//            Log.d("After run", String.valueOf(dijkstra.timeTaken));
//        } catch (IOException e) {
//            Log.d("In catch","In catch");
//            e.printStackTrace();
//        }
//
//        Log.d("After try catch","After try catch");

//        //In activity_2.xml there is a Plain Text. Tried to run Dijkstra and display the time taken but it is always -1.
//        //Might be an issue in dijkstra prog. Really uncertain.
//        TextView test = findViewById(R.id.test);
//        test.setText((Integer.toString(dijkstra.timeTaken)));
//        test.setVisibility(View.VISIBLE);

