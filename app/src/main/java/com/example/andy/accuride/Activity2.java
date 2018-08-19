package com.example.andy.accuride;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
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
        TextView tab1Minutes = findViewById(R.id.tab1timeTaken);
        TextView tab1numOfTransfers = findViewById(R.id.tab1Transfers);
        TextView tab1numOfStops = findViewById(R.id.tab1Stops);

        TextView tab2Minutes = findViewById(R.id.tab2timeTaken);
        TextView tab2numOfTransfers = findViewById(R.id.tab2Transfers);
        TextView tab2numOfStops = findViewById(R.id.tab2Stops);

        //Minutes
        String fastestTime = dijkstra.fastestTime + " Minutes";
        String leastTransfersTime = dijkstra.leastTransfers + " Minutes";
        tab1Minutes.setText(fastestTime);
        tab2Minutes.setText(leastTransfersTime);

        //Transfers
        tab1numOfTransfers.setText(Integer.toString(dijkstra.numOfTransfersFastest));
        tab2numOfTransfers.setText(Integer.toString(dijkstra.numOfTransfersLeast));

        //Stops
        int numOfStopsFastest = dijkstra.fastestPath.size() - dijkstra.numOfTransfersFastest - 1;
        int numOfStopsLeast = dijkstra.leastTransferspath.size() - dijkstra.numOfTransfersLeast - 1;
        tab1numOfStops.setText(Integer.toString(numOfStopsFastest));
        tab2numOfStops.setText(Integer.toString(numOfStopsLeast));

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
    }

    /**
     * This method is used to set the display paths of the journey
     * @param arrFastest                Fastest Route in an ArrayList
     * @param numOfTransfersFastest     Number of transfers in this journey
     * @param arrLeast                  Route with Least Transfers in an ArrayList
     * @param numOfTransfersLeast       Number of transfers in this journey
     * @param start                     Start point
     * @param destination               End point
     */
    private void setDisplayPath(ArrayList<String> arrFastest, int numOfTransfersFastest,
                                ArrayList<String> arrLeast, int numOfTransfersLeast, String start, String destination) {
        TextView tab1startStation1 = findViewById(R.id.tab1startStation1);
        TextView tab1startStation2 = findViewById(R.id.tab1startStation2);
        TextView tab1startStation3 = findViewById(R.id.tab1startStation3);
        TextView tab1startStation4 = findViewById(R.id.tab1startStation4);
        TextView tab1startStation5 = findViewById(R.id.tab1startStation5);
        TextView tab1endStation1 = findViewById(R.id.tab1endStation1);
        TextView tab1endStation2 = findViewById(R.id.tab1endStation2);
        TextView tab1endStation3 = findViewById(R.id.tab1endStation3);
        TextView tab1endStation4 = findViewById(R.id.tab1endStation4);
        TextView tab1endStation5 = findViewById(R.id.tab1endStation5);
        ImageView tab1startCircle1 = findViewById(R.id.tab1startCircle1);
        ImageView tab1startCircle2 = findViewById(R.id.tab1startCircle2);
        ImageView tab1startCircle3 = findViewById(R.id.tab1startCircle3);
        ImageView tab1startCircle4 = findViewById(R.id.tab1startCircle4);
        ImageView tab1startCircle5 = findViewById(R.id.tab1startCircle5);
        ImageView tab1endCircle1 = findViewById(R.id.tab1endCircle1);
        ImageView tab1endCircle2 = findViewById(R.id.tab1endCircle2);
        ImageView tab1endCircle3 = findViewById(R.id.tab1endCircle3);
        ImageView tab1endCircle4 = findViewById(R.id.tab1endCircle4);
        ImageView tab1endCircle5 = findViewById(R.id.tab1endCircle5);
        ImageView tab1Arrow2 = findViewById(R.id.tab1Arrow2);
        ImageView tab1Arrow3 = findViewById(R.id.tab1Arrow3);
        ImageView tab1Arrow4 = findViewById(R.id.tab1Arrow4);
        ImageView tab1Arrow5 = findViewById(R.id.tab1Arrow5);
        TextView tab1numOfStops1 = findViewById(R.id.tab1numOfStops1);
        TextView tab1numOfStops2 = findViewById(R.id.tab1numOfStops2);
        TextView tab1numOfStops3 = findViewById(R.id.tab1numOfStops3);
        TextView tab1numOfStops4 = findViewById(R.id.tab1numOfStops4);
        TextView tab1numOfStops5 = findViewById(R.id.tab1numOfStops5);

        if (numOfTransfersFastest == 0) {
            tab1startStation2.setVisibility(View.INVISIBLE);
            tab1startStation3.setVisibility(View.INVISIBLE);
            tab1startStation4.setVisibility(View.INVISIBLE);
            tab1startStation5.setVisibility(View.INVISIBLE);
            tab1endStation2.setVisibility(View.INVISIBLE);
            tab1endStation3.setVisibility(View.INVISIBLE);
            tab1endStation4.setVisibility(View.INVISIBLE);
            tab1endStation5.setVisibility(View.INVISIBLE);
            tab1startCircle2.setVisibility(View.INVISIBLE);
            tab1startCircle3.setVisibility(View.INVISIBLE);
            tab1startCircle4.setVisibility(View.INVISIBLE);
            tab1startCircle5.setVisibility(View.INVISIBLE);
            tab1endCircle2.setVisibility(View.INVISIBLE);
            tab1endCircle3.setVisibility(View.INVISIBLE);
            tab1endCircle4.setVisibility(View.INVISIBLE);
            tab1endCircle5.setVisibility(View.INVISIBLE);
            tab1Arrow2.setVisibility(View.INVISIBLE);
            tab1Arrow3.setVisibility(View.INVISIBLE);
            tab1Arrow4.setVisibility(View.INVISIBLE);
            tab1Arrow5.setVisibility(View.INVISIBLE);
            tab1numOfStops2.setVisibility(View.INVISIBLE);
            tab1numOfStops3.setVisibility(View.INVISIBLE);
            tab1numOfStops4.setVisibility(View.INVISIBLE);
            tab1numOfStops5.setVisibility(View.INVISIBLE);

            String numOfStops;
            if (arrFastest.size() - 1 == 1) {
                numOfStops = "1 Stop";
            } else {
                numOfStops = Integer.toString(arrFastest.size() - 1) + " Stops";
            }

            tab1numOfStops1.setText(numOfStops);
            tab1startStation1.setText(start);
            tab1endStation1.setText(destination);
            setCircleColour(arrFastest, tab1startCircle1, tab1endCircle1);

        } else if (numOfTransfersFastest == 1) {
            tab1startStation3.setVisibility(View.INVISIBLE);
            tab1startStation4.setVisibility(View.INVISIBLE);
            tab1startStation5.setVisibility(View.INVISIBLE);
            tab1endStation3.setVisibility(View.INVISIBLE);
            tab1endStation4.setVisibility(View.INVISIBLE);
            tab1endStation5.setVisibility(View.INVISIBLE);
            tab1startCircle3.setVisibility(View.INVISIBLE);
            tab1startCircle4.setVisibility(View.INVISIBLE);
            tab1startCircle5.setVisibility(View.INVISIBLE);
            tab1endCircle3.setVisibility(View.INVISIBLE);
            tab1endCircle4.setVisibility(View.INVISIBLE);
            tab1endCircle5.setVisibility(View.INVISIBLE);
            tab1Arrow3.setVisibility(View.INVISIBLE);
            tab1Arrow4.setVisibility(View.INVISIBLE);
            tab1Arrow5.setVisibility(View.INVISIBLE);
            tab1numOfStops3.setVisibility(View.INVISIBLE);
            tab1numOfStops4.setVisibility(View.INVISIBLE);
            tab1numOfStops5.setVisibility(View.INVISIBLE);

            //indexArr to store the index where there is an interchange
            ArrayList<Integer> indexArr = new ArrayList<>(numOfTransfersFastest);
            int counterFastest = numOfTransfersFastest;

            //This while-loop finds the interchange and puts it in the indexArr
            for (int i = 0; (counterFastest > 0 && i < arrFastest.size()); i++) {
                String trainStation1 = (arrFastest.get(i).split(" ", 2))[1];
                String trainStation2 = (arrFastest.get(i + 1).split(" ", 2))[1];

                if (trainStation1.equals(trainStation2)) {
                    indexArr.add(i);
                    counterFastest--;
                }
            }

            //Used for displaying different journeys for transfers
            ArrayList<String> firstPart = new ArrayList<>();
            ArrayList<String> secondPart = new ArrayList<>();

            //The following part of the program splits the array into different parts
            int first_part_number = indexArr.get(0);
            int second_part_number = indexArr.get(0) + 1;

            for (int i = 0; i <= first_part_number; i++) {
                firstPart.add(arrFastest.get(i));
            }

            for (int i = second_part_number; i < arrFastest.size(); i++) {
                secondPart.add(arrFastest.get(i));
            }

            String numOfStops1;
            String numOfStops2;
            if (firstPart.size() - 1 == 1) {
                numOfStops1 = "1 Stop";
            } else {
                numOfStops1 = Integer.toString(firstPart.size() - 1) + " Stops";
            }
            if (secondPart.size() - 1 == 1) {
                numOfStops2 = "1 Stop";
            } else {
                numOfStops2 = Integer.toString(secondPart.size() - 1) + " Stops";
            }

            tab1numOfStops1.setText(numOfStops1);
            tab1numOfStops2.setText(numOfStops2);
            tab1startStation1.setText(start);
            tab1endStation1.setText(firstPart.get(firstPart.size() - 1).split(" ", 2)[1]);   //Sets last station in this arr
            tab1startStation2.setText(secondPart.get(0).split(" ", 2)[1]);                   //Sets first station in this arr
            tab1endStation2.setText(destination);
            setCircleColour(firstPart, tab1startCircle1, tab1endCircle1);
            setCircleColour(secondPart, tab1startCircle2, tab1endCircle2);


        } else if (numOfTransfersFastest == 2) {
            tab1startStation4.setVisibility(View.INVISIBLE);
            tab1startStation5.setVisibility(View.INVISIBLE);
            tab1endStation4.setVisibility(View.INVISIBLE);
            tab1endStation5.setVisibility(View.INVISIBLE);
            tab1startCircle4.setVisibility(View.INVISIBLE);
            tab1startCircle5.setVisibility(View.INVISIBLE);
            tab1endCircle4.setVisibility(View.INVISIBLE);
            tab1endCircle5.setVisibility(View.INVISIBLE);
            tab1Arrow4.setVisibility(View.INVISIBLE);
            tab1Arrow5.setVisibility(View.INVISIBLE);
            tab1numOfStops4.setVisibility(View.INVISIBLE);
            tab1numOfStops5.setVisibility(View.INVISIBLE);

            ArrayList<Integer> indexArr = new ArrayList<>(numOfTransfersFastest);
            int counterFastest = numOfTransfersFastest;

            //This while-loop finds the interchange
            for (int i = 0; (counterFastest > 0 && i < arrFastest.size()); i++) {
                String trainStation1 = (arrFastest.get(i).split(" ", 2))[1];
                String trainStation2 = (arrFastest.get(i + 1).split(" ", 2))[1];

                if (trainStation1.equals(trainStation2)) {
                    indexArr.add(i);
                    counterFastest--;
                }
            }

            //Used for displaying different journeys for transfers
            ArrayList<String> firstPart = new ArrayList<>();
            ArrayList<String> secondPart = new ArrayList<>();
            ArrayList<String> thirdPart = new ArrayList<>();

            int first_part_number = indexArr.get(0);
            int second_part_number = indexArr.get(0) + 1;
            int third_part_number = indexArr.get(1);

            for (int i = 0; i <= first_part_number; i++) {
                firstPart.add(arrFastest.get(i));
            }

            for (int i = second_part_number; i <= third_part_number; i++) {
                secondPart.add(arrFastest.get(i));
            }

            for (int i = third_part_number + 1; i < arrFastest.size(); i++) {
                thirdPart.add(arrFastest.get(i));
            }

            String numOfStops1;
            String numOfStops2;
            String numOfStops3;
            if (firstPart.size() - 1 == 1) {
                numOfStops1 = "1 Stop";
            } else {
                numOfStops1 = Integer.toString(firstPart.size() - 1) + " Stops";
            }
            if (secondPart.size() - 1 == 1) {
                numOfStops2 = "1 Stop";
            } else {
                numOfStops2 = Integer.toString(secondPart.size() - 1) + " Stops";
            }
            if (thirdPart.size() - 1 == 1) {
                numOfStops3 = "1 Stop";
            } else {
                numOfStops3 = Integer.toString(thirdPart.size() - 1) + " Stops";
            }

            tab1numOfStops1.setText(numOfStops1);
            tab1numOfStops2.setText(numOfStops2);
            tab1numOfStops3.setText(numOfStops3);
            tab1startStation1.setText(start);
            tab1endStation1.setText(firstPart.get(firstPart.size() - 1).split(" ", 2)[1]);   //Sets last station in this arr
            tab1startStation2.setText(secondPart.get(0).split(" ", 2)[1]);                   //Sets first station in this arr
            tab1endStation2.setText(secondPart.get(secondPart.size() - 1).split(" ", 2)[1]);
            tab1startStation3.setText(thirdPart.get(0).split(" ", 2)[1]);
            tab1endStation3.setText(destination);
            setCircleColour(firstPart, tab1startCircle1, tab1endCircle1);
            setCircleColour(secondPart, tab1startCircle2, tab1endCircle2);
            setCircleColour(thirdPart, tab1startCircle3, tab1endCircle3);

        } else if (numOfTransfersFastest == 3) {
            tab1startStation5.setVisibility(View.INVISIBLE);
            tab1endStation5.setVisibility(View.INVISIBLE);
            tab1startCircle5.setVisibility(View.INVISIBLE);
            tab1endCircle5.setVisibility(View.INVISIBLE);
            tab1Arrow5.setVisibility(View.INVISIBLE);
            tab1numOfStops5.setVisibility(View.INVISIBLE);

            ArrayList<Integer> indexArr = new ArrayList<>(numOfTransfersFastest);
            int counterFastest = numOfTransfersFastest;

            //This while-loop finds the interchange
            for (int i = 0; (counterFastest > 0 && i < arrFastest.size()); i++) {
                String trainStation1 = (arrFastest.get(i).split(" ", 2))[1];
                String trainStation2 = (arrFastest.get(i + 1).split(" ", 2))[1];

                if (trainStation1.equals(trainStation2)) {
                    indexArr.add(i);
                    counterFastest--;
                }
            }

            //Used for displaying different journeys for transfers
            ArrayList<String> firstPart = new ArrayList<>();
            ArrayList<String> secondPart = new ArrayList<>();
            ArrayList<String> thirdPart = new ArrayList<>();
            ArrayList<String> fourthPart = new ArrayList<>();

            int first_part_number = indexArr.get(0);
            int second_part_number = indexArr.get(0) + 1;
            int third_part_number = indexArr.get(1);
            int fourth_part_number = indexArr.get(2);

            for (int i = 0; i <= first_part_number; i++) {
                firstPart.add(arrFastest.get(i));
            }

            for (int i = second_part_number; i <= third_part_number; i++) {
                secondPart.add(arrFastest.get(i));
            }

            for (int i = third_part_number + 1; i <= fourth_part_number; i++) {
                thirdPart.add(arrFastest.get(i));
            }

            for (int i = fourth_part_number + 1; i < arrFastest.size(); i++) {
                fourthPart.add(arrFastest.get(i));
            }

            String numOfStops1;
            String numOfStops2;
            String numOfStops3;
            String numOfStops4;
            if (firstPart.size() - 1 == 1) {
                numOfStops1 = "1 Stop";
            } else {
                numOfStops1 = Integer.toString(firstPart.size() - 1) + " Stops";
            }
            if (secondPart.size() - 1 == 1) {
                numOfStops2 = "1 Stop";
            } else {
                numOfStops2 = Integer.toString(secondPart.size() - 1) + " Stops";
            }
            if (thirdPart.size() - 1 == 1) {
                numOfStops3 = "1 Stop";
            } else {
                numOfStops3 = Integer.toString(thirdPart.size() - 1) + " Stops";
            }
            if (fourthPart.size() - 1 == 1) {
                numOfStops4 = "1 Stop";
            } else {
                numOfStops4 = Integer.toString(fourthPart.size() - 1) + " Stops";
            }

            tab1numOfStops1.setText(numOfStops1);
            tab1numOfStops2.setText(numOfStops2);
            tab1numOfStops3.setText(numOfStops3);
            tab1numOfStops4.setText(numOfStops4);
            tab1startStation1.setText(start);
            tab1endStation1.setText(firstPart.get(firstPart.size() - 1).split(" ", 2)[1]);   //Sets last station in this arr
            tab1startStation2.setText(secondPart.get(0).split(" ", 2)[1]);                   //Sets first station in this arr
            tab1endStation2.setText(secondPart.get(secondPart.size() - 1).split(" ", 2)[1]);
            tab1startStation3.setText(thirdPart.get(0).split(" ", 2)[1]);
            tab1endStation3.setText(thirdPart.get(thirdPart.size() - 1).split(" ", 2)[1]);
            tab1startStation4.setText(fourthPart.get(0).split(" ", 2)[1]);
            tab1endStation4.setText(destination);

            setCircleColour(firstPart, tab1startCircle1, tab1endCircle1);
            setCircleColour(secondPart, tab1startCircle2, tab1endCircle2);
            setCircleColour(thirdPart, tab1startCircle3, tab1endCircle3);
            setCircleColour(fourthPart, tab1startCircle4, tab1endCircle4);

        } else if (numOfTransfersFastest == 4) {
            ArrayList<Integer> indexArr = new ArrayList<>(numOfTransfersFastest);
            int counterFastest = numOfTransfersFastest;

            //This while-loop finds the interchange
            for (int i = 0; (counterFastest > 0 && i < arrFastest.size()); i++) {
                String trainStation1 = (arrFastest.get(i).split(" ", 2))[1];
                String trainStation2 = (arrFastest.get(i + 1).split(" ", 2))[1];

                if (trainStation1.equals(trainStation2)) {
                    indexArr.add(i);
                    counterFastest--;
                }
            }

            //Used for displaying different journeys for transfers
            ArrayList<String> firstPart = new ArrayList<>();
            ArrayList<String> secondPart = new ArrayList<>();
            ArrayList<String> thirdPart = new ArrayList<>();
            ArrayList<String> fourthPart = new ArrayList<>();
            ArrayList<String> fifthPart = new ArrayList<>();

            int first_part_number = indexArr.get(0);
            int second_part_number = indexArr.get(0) + 1;
            int third_part_number = indexArr.get(1);
            int fourth_part_number = indexArr.get(2);
            int fifth_part_number = indexArr.get(3);

            for (int i = 0; i <= first_part_number; i++) {
                firstPart.add(arrFastest.get(i));
            }

            for (int i = second_part_number; i <= third_part_number; i++) {
                secondPart.add(arrFastest.get(i));
            }

            for (int i = third_part_number + 1; i <= fourth_part_number; i++) {
                thirdPart.add(arrFastest.get(i));
            }

            for (int i = fourth_part_number + 1; i <= fifth_part_number; i++) {
                fourthPart.add(arrFastest.get(i));
            }

            for (int i  = fifth_part_number + 1; i < arrFastest.size(); i++) {
                fifthPart.add(arrFastest.get(i));
            }

            String numOfStops1;
            String numOfStops2;
            String numOfStops3;
            String numOfStops4;
            String numOfStops5;
            if (firstPart.size() - 1 == 1) {
                numOfStops1 = "1 Stop";
            } else {
                numOfStops1 = Integer.toString(firstPart.size() - 1) + " Stops";
            }
            if (secondPart.size() - 1 == 1) {
                numOfStops2 = "1 Stop";
            } else {
                numOfStops2 = Integer.toString(secondPart.size() - 1) + " Stops";
            }
            if (thirdPart.size() - 1 == 1) {
                numOfStops3 = "1 Stop";
            } else {
                numOfStops3 = Integer.toString(thirdPart.size() - 1) + " Stops";
            }
            if (fourthPart.size() - 1 == 1) {
                numOfStops4 = "1 Stop";
            } else {
                numOfStops4 = Integer.toString(fourthPart.size() - 1) + " Stops";
            }
            if (fifthPart.size() - 1 == 0) {
                numOfStops5 = "1 Stop";
            } else {
                numOfStops5 = Integer.toString(fifthPart.size() - 1) + " Stops";
            }

            tab1numOfStops1.setText(numOfStops1);
            tab1numOfStops2.setText(numOfStops2);
            tab1numOfStops3.setText(numOfStops3);
            tab1numOfStops4.setText(numOfStops4);
            tab1numOfStops5.setText(numOfStops5);
            tab1startStation1.setText(start);
            tab1endStation1.setText(firstPart.get(firstPart.size() - 1).split(" ", 2)[1]);   //Sets last station in this arr
            tab1startStation2.setText(secondPart.get(0).split(" ", 2)[1]);                   //Sets first station in this arr
            tab1endStation2.setText(secondPart.get(secondPart.size() - 1).split(" ", 2)[1]);
            tab1startStation3.setText(thirdPart.get(0).split(" ", 2)[1]);
            tab1endStation3.setText(thirdPart.get(thirdPart.size() - 1).split(" ", 2)[1]);
            tab1startStation4.setText(fourthPart.get(0).split(" ", 2)[1]);
            tab1endStation4.setText(fourthPart.get(fourthPart.size() - 1).split(" ", 2)[1]);
            tab1startStation5.setText(fifthPart.get(0).split(" ", 2)[1]);
            setCircleColour(firstPart, tab1startCircle1, tab1endCircle1);
            setCircleColour(secondPart, tab1startCircle2, tab1endCircle2);
            setCircleColour(thirdPart, tab1startCircle3, tab1endCircle3);
            setCircleColour(fourthPart, tab1startCircle4, tab1endCircle4);
            setCircleColour(fifthPart, tab1startCircle5, tab1endCircle5);
        }


        //For Least Transfers
        TextView tab2startStation1 = findViewById(R.id.tab2startStation1);
        TextView tab2startStation2 = findViewById(R.id.tab2startStation2);
        TextView tab2startStation3 = findViewById(R.id.tab2startStation3);
        TextView tab2startStation4 = findViewById(R.id.tab2startStation4);
        TextView tab2startStation5 = findViewById(R.id.tab2startStation5);
        TextView tab2endStation1 = findViewById(R.id.tab2endStation1);
        TextView tab2endStation2 = findViewById(R.id.tab2endStation2);
        TextView tab2endStation3 = findViewById(R.id.tab2endStation3);
        TextView tab2endStation4 = findViewById(R.id.tab2endStation4);
        TextView tab2endStation5 = findViewById(R.id.tab2endStation5);
        ImageView tab2startCircle1 = findViewById(R.id.tab2startCircle1);
        ImageView tab2startCircle2 = findViewById(R.id.tab2startCircle2);
        ImageView tab2startCircle3 = findViewById(R.id.tab2startCircle3);
        ImageView tab2startCircle4 = findViewById(R.id.tab2startCircle4);
        ImageView tab2startCircle5 = findViewById(R.id.tab2startCircle5);
        ImageView tab2endCircle1 = findViewById(R.id.tab2endCircle1);
        ImageView tab2endCircle2 = findViewById(R.id.tab2endCircle2);
        ImageView tab2endCircle3 = findViewById(R.id.tab2endCircle3);
        ImageView tab2endCircle4 = findViewById(R.id.tab2endCircle4);
        ImageView tab2endCircle5 = findViewById(R.id.tab2endCircle5);
        ImageView tab2Arrow2 = findViewById(R.id.tab2Arrow2);
        ImageView tab2Arrow3 = findViewById(R.id.tab2Arrow3);
        ImageView tab2Arrow4 = findViewById(R.id.tab2Arrow4);
        ImageView tab2Arrow5 = findViewById(R.id.tab2Arrow5);
        TextView tab2numOfStops1 = findViewById(R.id.tab2numOfStops1);
        TextView tab2numOfStops2 = findViewById(R.id.tab2numOfStops2);
        TextView tab2numOfStops3 = findViewById(R.id.tab2numOfStops3);
        TextView tab2numOfStops4 = findViewById(R.id.tab2numOfStops4);
        TextView tab2numOfStops5 = findViewById(R.id.tab2numOfStops5);

        if (numOfTransfersLeast == 0) {
            tab2startStation2.setVisibility(View.INVISIBLE);
            tab2startStation3.setVisibility(View.INVISIBLE);
            tab2startStation4.setVisibility(View.INVISIBLE);
            tab2startStation5.setVisibility(View.INVISIBLE);
            tab2endStation2.setVisibility(View.INVISIBLE);
            tab2endStation3.setVisibility(View.INVISIBLE);
            tab2endStation4.setVisibility(View.INVISIBLE);
            tab2endStation5.setVisibility(View.INVISIBLE);
            tab2startCircle2.setVisibility(View.INVISIBLE);
            tab2startCircle3.setVisibility(View.INVISIBLE);
            tab2startCircle4.setVisibility(View.INVISIBLE);
            tab2startCircle5.setVisibility(View.INVISIBLE);
            tab2endCircle2.setVisibility(View.INVISIBLE);
            tab2endCircle3.setVisibility(View.INVISIBLE);
            tab2endCircle4.setVisibility(View.INVISIBLE);
            tab2endCircle5.setVisibility(View.INVISIBLE);
            tab2Arrow2.setVisibility(View.INVISIBLE);
            tab2Arrow3.setVisibility(View.INVISIBLE);
            tab2Arrow4.setVisibility(View.INVISIBLE);
            tab2Arrow5.setVisibility(View.INVISIBLE);
            tab2numOfStops2.setVisibility(View.INVISIBLE);
            tab2numOfStops3.setVisibility(View.INVISIBLE);
            tab2numOfStops4.setVisibility(View.INVISIBLE);
            tab2numOfStops5.setVisibility(View.INVISIBLE);

            String numOfStops;
            if (arrLeast.size() - 1 == 1) {
                numOfStops = "1 Stop";
            } else {
                numOfStops = Integer.toString(arrLeast.size() - 1) + " Stops";
            }

            tab2numOfStops1.setText(numOfStops);
            tab2startStation1.setText(start);
            tab2endStation1.setText(destination);
            setCircleColour(arrFastest, tab2startCircle1, tab2endCircle1);

        } else if (numOfTransfersLeast == 1) {
            tab2startStation3.setVisibility(View.INVISIBLE);
            tab2startStation4.setVisibility(View.INVISIBLE);
            tab2startStation5.setVisibility(View.INVISIBLE);
            tab2endStation3.setVisibility(View.INVISIBLE);
            tab2endStation4.setVisibility(View.INVISIBLE);
            tab2endStation5.setVisibility(View.INVISIBLE);
            tab2startCircle3.setVisibility(View.INVISIBLE);
            tab2startCircle4.setVisibility(View.INVISIBLE);
            tab2startCircle5.setVisibility(View.INVISIBLE);
            tab2endCircle3.setVisibility(View.INVISIBLE);
            tab2endCircle4.setVisibility(View.INVISIBLE);
            tab2endCircle5.setVisibility(View.INVISIBLE);
            tab2Arrow3.setVisibility(View.INVISIBLE);
            tab2Arrow4.setVisibility(View.INVISIBLE);
            tab2Arrow5.setVisibility(View.INVISIBLE);
            tab2numOfStops3.setVisibility(View.INVISIBLE);
            tab2numOfStops4.setVisibility(View.INVISIBLE);
            tab2numOfStops5.setVisibility(View.INVISIBLE);

            ArrayList<Integer> indexArr = new ArrayList<>(numOfTransfersLeast);
            int counterLeast = numOfTransfersLeast;

            //This while-loop finds the interchange
            for (int i = 0; (counterLeast > 0 && i < arrLeast.size()); i++) {
                String trainStation1 = (arrLeast.get(i).split(" ", 2))[1];
                String trainStation2 = (arrLeast.get(i + 1).split(" ", 2))[1];

                if (trainStation1.equals(trainStation2)) {
                    indexArr.add(i);
                    counterLeast--;
                }
            }

            //Used for displaying different journeys for transfers
            ArrayList<String> firstPart = new ArrayList<>();
            ArrayList<String> secondPart = new ArrayList<>();

            int first_part_number = indexArr.get(0);
            int second_part_number = indexArr.get(0) + 1;

            for (int i = 0; i <= first_part_number; i++) {
                firstPart.add(arrLeast.get(i));
            }

            for (int i = second_part_number; i < arrLeast.size(); i++) {
                secondPart.add(arrLeast.get(i));
            }

            String numOfStops1;
            String numOfStops2;
            if (firstPart.size() - 1 == 1) {
                numOfStops1 = "1 Stop";
            } else {
                numOfStops1 = Integer.toString(firstPart.size() - 1) + " Stops";
            }
            if (secondPart.size() - 1 == 1) {
                numOfStops2 = "1 Stop";
            } else {
                numOfStops2 = Integer.toString(secondPart.size() - 1) + " Stops";
            }

            tab2numOfStops1.setText(numOfStops1);
            tab2numOfStops2.setText(numOfStops2);
            tab2startStation1.setText(start);
            tab2endStation1.setText(firstPart.get(firstPart.size() - 1).split(" ", 2)[1]);   //Sets last station in this arr
            tab2startStation2.setText(secondPart.get(0).split(" ", 2)[1]);                   //Sets first station in this arr
            tab2endStation2.setText(destination);
            setCircleColour(firstPart, tab2startCircle1, tab2endCircle1);
            setCircleColour(secondPart, tab2startCircle2, tab2endCircle2);

        } else if (numOfTransfersLeast == 2) {
            tab2startStation4.setVisibility(View.INVISIBLE);
            tab2startStation5.setVisibility(View.INVISIBLE);
            tab2endStation4.setVisibility(View.INVISIBLE);
            tab2endStation5.setVisibility(View.INVISIBLE);
            tab2startCircle4.setVisibility(View.INVISIBLE);
            tab2startCircle5.setVisibility(View.INVISIBLE);
            tab2endCircle4.setVisibility(View.INVISIBLE);
            tab2endCircle5.setVisibility(View.INVISIBLE);
            tab2Arrow4.setVisibility(View.INVISIBLE);
            tab2Arrow5.setVisibility(View.INVISIBLE);
            tab2numOfStops4.setVisibility(View.INVISIBLE);
            tab2numOfStops5.setVisibility(View.INVISIBLE);


            ArrayList<Integer> indexArr = new ArrayList<>(numOfTransfersLeast);
            int counterLeast = numOfTransfersLeast;

            //This while-loop finds the interchange
            for (int i = 0; (counterLeast > 0 && i < arrLeast.size()); i++) {
                String trainStation1 = (arrLeast.get(i).split(" ", 2))[1];
                String trainStation2 = (arrLeast.get(i + 1).split(" ", 2))[1];

                if (trainStation1.equals(trainStation2)) {
                    indexArr.add(i);
                    counterLeast--;
                }
            }

            //Used for displaying different journeys for transfers
            ArrayList<String> firstPart = new ArrayList<>();
            ArrayList<String> secondPart = new ArrayList<>();
            ArrayList<String> thirdPart = new ArrayList<>();

            int first_part_number = indexArr.get(0);
            int second_part_number = indexArr.get(0) + 1;
            int third_part_number = indexArr.get(1);

            for (int i = 0; i <= first_part_number; i++) {
                firstPart.add(arrLeast.get(i));
            }

            for (int i = second_part_number; i <= third_part_number; i++) {
                secondPart.add(arrLeast.get(i));
            }

            for (int i = third_part_number + 1; i < arrLeast.size(); i++) {
                thirdPart.add(arrLeast.get(i));
            }

            String numOfStops1;
            String numOfStops2;
            String numOfStops3;
            if (firstPart.size() - 1 == 1) {
                numOfStops1 = "1 Stop";
            } else {
                numOfStops1 = Integer.toString(firstPart.size() - 1) + " Stops";
            }
            if (secondPart.size() - 1 == 1) {
                numOfStops2 = "1 Stop";
            } else {
                numOfStops2 = Integer.toString(secondPart.size() - 1) + " Stops";
            }
            if (thirdPart.size() - 1 == 1) {
                numOfStops3 = "1 Stop";
            } else {
                numOfStops3 = Integer.toString(thirdPart.size() - 1) + " Stops";
            }

            tab2numOfStops1.setText(numOfStops1);
            tab2numOfStops2.setText(numOfStops2);
            tab2numOfStops3.setText(numOfStops3);
            tab2startStation1.setText(start);
            tab2endStation1.setText(firstPart.get(firstPart.size() - 1).split(" ", 2)[1]);   //Sets last station in this arr
            tab2startStation2.setText(secondPart.get(0).split(" ", 2)[1]);                   //Sets first station in this arr
            tab2endStation2.setText(secondPart.get(secondPart.size() - 1).split(" ", 2)[1]);
            tab2startStation3.setText(thirdPart.get(0).split(" ", 2)[1]);
            tab2endStation3.setText(destination);

            setCircleColour(firstPart, tab2startCircle1, tab2endCircle1);
            setCircleColour(secondPart, tab2startCircle2, tab2endCircle2);
            setCircleColour(thirdPart, tab2startCircle3, tab2endCircle3);

        } else if (numOfTransfersLeast == 3) {
            tab2startStation5.setVisibility(View.INVISIBLE);
            tab2endStation5.setVisibility(View.INVISIBLE);
            tab2startCircle5.setVisibility(View.INVISIBLE);
            tab2endCircle5.setVisibility(View.INVISIBLE);
            tab2Arrow5.setVisibility(View.INVISIBLE);
            tab2numOfStops5.setVisibility(View.INVISIBLE);

            ArrayList<Integer> indexArr = new ArrayList<>(numOfTransfersLeast);
            int counterLeast = numOfTransfersLeast;

            //This while-loop finds the interchange
            for (int i = 0; (counterLeast > 0 && i < arrLeast.size()); i++) {
                String trainStation1 = (arrLeast.get(i).split(" ", 2))[1];
                String trainStation2 = (arrLeast.get(i + 1).split(" ", 2))[1];

                if (trainStation1.equals(trainStation2)) {
                    indexArr.add(i);
                    counterLeast--;
                }
            }

            //Used for displaying different journeys for transfers
            ArrayList<String> firstPart = new ArrayList<>();
            ArrayList<String> secondPart = new ArrayList<>();
            ArrayList<String> thirdPart = new ArrayList<>();
            ArrayList<String> fourthPart = new ArrayList<>();

            int first_part_number = indexArr.get(0);
            int second_part_number = indexArr.get(0) + 1;
            int third_part_number = indexArr.get(1);
            int fourth_part_number = indexArr.get(2);

            for (int i = 0; i <= first_part_number; i++) {
                firstPart.add(arrLeast.get(i));
            }

            for (int i = second_part_number; i <= third_part_number; i++) {
                secondPart.add(arrLeast.get(i));
            }

            for (int i = third_part_number + 1; i <= fourth_part_number; i++) {
                thirdPart.add(arrLeast.get(i));
            }

            for (int i = fourth_part_number + 1; i < arrLeast.size(); i++) {
                fourthPart.add(arrLeast.get(i));
            }

            String numOfStops1;
            String numOfStops2;
            String numOfStops3;
            String numOfStops4;
            if (firstPart.size() - 1 == 1) {
                numOfStops1 = "1 Stop";
            } else {
                numOfStops1 = Integer.toString(firstPart.size() - 1) + " Stops";
            }
            if (secondPart.size() - 1 == 1) {
                numOfStops2 = "1 Stop";
            } else {
                numOfStops2 = Integer.toString(secondPart.size() - 1) + " Stops";
            }
            if (thirdPart.size() - 1 == 1) {
                numOfStops3 = "1 Stop";
            } else {
                numOfStops3 = Integer.toString(thirdPart.size() - 1) + " Stops";
            }
            if (fourthPart.size() - 1 == 1) {
                numOfStops4 = "1 Stop";
            } else {
                numOfStops4 = Integer.toString(fourthPart.size() - 1) + " Stops";
            }

            tab2numOfStops1.setText(numOfStops1);
            tab2numOfStops2.setText(numOfStops2);
            tab2numOfStops3.setText(numOfStops3);
            tab2numOfStops4.setText(numOfStops4);
            tab2startStation1.setText(start);
            tab2endStation1.setText(firstPart.get(firstPart.size() - 1).split(" ", 2)[1]);   //Sets last station in this arr
            tab2startStation2.setText(secondPart.get(0).split(" ", 2)[1]);                   //Sets first station in this arr
            tab2endStation2.setText(secondPart.get(secondPart.size() - 1).split(" ", 2)[1]);
            tab2startStation3.setText(thirdPart.get(0).split(" ", 2)[1]);
            tab2endStation3.setText(thirdPart.get(thirdPart.size() - 1).split(" ", 2)[1]);
            tab2startStation4.setText(fourthPart.get(0).split(" ", 2)[1]);
            tab2endStation4.setText(destination);

            setCircleColour(firstPart, tab2startCircle1, tab2endCircle1);
            setCircleColour(secondPart, tab2startCircle2, tab2endCircle2);
            setCircleColour(thirdPart, tab2startCircle3, tab2endCircle3);
            setCircleColour(fourthPart, tab2startCircle4, tab2endCircle4);

        } else if (numOfTransfersLeast == 4) {
            ArrayList<Integer> indexArr = new ArrayList<>(numOfTransfersLeast);
            int counterLeast = numOfTransfersLeast;

            //This while-loop finds the interchange
            for (int i = 0; (counterLeast > 0 && i < arrFastest.size()); i++) {
                String trainStation1 = (arrLeast.get(i).split(" ", 2))[1];
                String trainStation2 = (arrLeast.get(i + 1).split(" ", 2))[1];

                if (trainStation1.equals(trainStation2)) {
                    indexArr.add(i);
                    counterLeast--;
                }
            }

            //Used for displaying different journeys for transfers
            ArrayList<String> firstPart = new ArrayList<>();
            ArrayList<String> secondPart = new ArrayList<>();
            ArrayList<String> thirdPart = new ArrayList<>();
            ArrayList<String> fourthPart = new ArrayList<>();
            ArrayList<String> fifthPart = new ArrayList<>();

            int first_part_number = indexArr.get(0);
            int second_part_number = indexArr.get(0) + 1;
            int third_part_number = indexArr.get(1);
            int fourth_part_number = indexArr.get(2);
            int fifth_part_number = indexArr.get(3);

            for (int i = 0; i <= first_part_number; i++) {
                firstPart.add(arrLeast.get(i));
            }

            for (int i = second_part_number; i <= third_part_number; i++) {
                secondPart.add(arrLeast.get(i));
            }

            for (int i = third_part_number + 1; i <= fourth_part_number; i++) {
                thirdPart.add(arrLeast.get(i));
            }

            for (int i = fourth_part_number + 1; i <= fifth_part_number; i++) {
                fourthPart.add(arrLeast.get(i));
            }

            for (int i  = fifth_part_number + 1; i < arrLeast.size(); i++) {
                fifthPart.add(arrLeast.get(i));
            }

            String numOfStops1;
            String numOfStops2;
            String numOfStops3;
            String numOfStops4;
            String numOfStops5;
            if (firstPart.size() - 1 == 1) {
                numOfStops1 = "1 Stop";
            } else {
                numOfStops1 = Integer.toString(firstPart.size() - 1) + " Stops";
            }
            if (secondPart.size() - 1 == 1) {
                numOfStops2 = "1 Stop";
            } else {
                numOfStops2 = Integer.toString(secondPart.size() - 1) + " Stops";
            }
            if (thirdPart.size() - 1 == 1) {
                numOfStops3 = "1 Stop";
            } else {
                numOfStops3 = Integer.toString(thirdPart.size() - 1) + " Stops";
            }
            if (fourthPart.size() - 1 == 1) {
                numOfStops4 = "1 Stop";
            } else {
                numOfStops4 = Integer.toString(fourthPart.size() - 1) + " Stops";
            }
            if (fifthPart.size() - 1 == 0) {
                numOfStops5 = "1 Stop";
            } else {
                numOfStops5 = Integer.toString(fifthPart.size() - 1) + " Stops";
            }

            tab2numOfStops1.setText(numOfStops1);
            tab2numOfStops2.setText(numOfStops2);
            tab2numOfStops3.setText(numOfStops3);
            tab2numOfStops4.setText(numOfStops4);
            tab2numOfStops5.setText(numOfStops5);
            tab2startStation1.setText(start);
            tab2endStation1.setText(firstPart.get(firstPart.size() - 1).split(" ", 2)[1]);   //Sets last station in this arr
            tab2startStation2.setText(secondPart.get(0).split(" ", 2)[1]);                   //Sets first station in this arr
            tab2endStation2.setText(secondPart.get(secondPart.size() - 1).split(" ", 2)[1]);
            tab2startStation3.setText(thirdPart.get(0).split(" ", 2)[1]);
            tab2endStation3.setText(thirdPart.get(thirdPart.size() - 1).split(" ", 2)[1]);
            tab2startStation4.setText(fourthPart.get(0).split(" ", 2)[1]);
            tab2endStation4.setText(fourthPart.get(fourthPart.size() - 1).split(" ", 2)[1]);
            tab2startStation5.setText(fifthPart.get(0).split(" ", 2)[1]);

            setCircleColour(firstPart, tab2startCircle1, tab2endCircle1);
            setCircleColour(secondPart, tab2startCircle2, tab2endCircle2);
            setCircleColour(thirdPart, tab2startCircle3, tab2endCircle3);
            setCircleColour(fourthPart, tab2startCircle4, tab2endCircle4);
            setCircleColour(fifthPart, tab2startCircle5, tab2endCircle5);
        }
    }

    /**
     * This method checks if the station lines are EW, NS, NE, CC, DT or LRT
     * and sets the colour of the circle.
     * @param array        Array of stations to check
     * @param startCircle  Circle to colour
     * @param endCircle    Circle to colour
     */
    private void setCircleColour(ArrayList<String> array, ImageView startCircle, ImageView endCircle) {
        String line[] = array.get(0).split(" ", 2);
        String trainLine = line[0];

        switch (trainLine) {
            case "EW":
                startCircle.setColorFilter(startCircle.getContext().getResources().getColor(R.color.EW));
                endCircle.setColorFilter(endCircle.getContext().getResources().getColor(R.color.EW));
                break;

            case "NS":
                startCircle.setColorFilter(startCircle.getContext().getResources().getColor(R.color.NS));
                endCircle.setColorFilter(endCircle.getContext().getResources().getColor(R.color.NS));
                break;

            case "NE":
                startCircle.setColorFilter(startCircle.getContext().getResources().getColor(R.color.NE));
                endCircle.setColorFilter(endCircle.getContext().getResources().getColor(R.color.NE));
                break;

            case "CC":
                startCircle.setColorFilter(startCircle.getContext().getResources().getColor(R.color.CC));
                endCircle.setColorFilter(endCircle.getContext().getResources().getColor(R.color.CC));
                break;

            case "DT":
                startCircle.setColorFilter(startCircle.getContext().getResources().getColor(R.color.DT));
                endCircle.setColorFilter(endCircle.getContext().getResources().getColor(R.color.DT));
                break;

            default:
                startCircle.setColorFilter(startCircle.getContext().getResources().getColor(R.color.LRT));
                endCircle.setColorFilter(endCircle.getContext().getResources().getColor(R.color.LRT));
                break;
        }
    }

}