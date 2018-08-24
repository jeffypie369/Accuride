package com.example.andy.accuride;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * This class is the main class of the whole program.
 * @author Andy Chan
 */
public class MainActivity extends AppCompatActivity {
    ArrayList<String> stationNames = new ArrayList<>();
    AutoCompleteTextView from_box;
    AutoCompleteTextView to_box;
    Button go_button;
    ImageButton announcement_button;
    ImageButton lasttrain_button;
    ImageButton map_button;

    /**
     * This method will render the landing page of the application when its open.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            initStations();
        } catch (IOException e) {
            e.printStackTrace();
        }

        from_box = findViewById(R.id.from_box);
        to_box = findViewById(R.id.to_box);
        go_button = findViewById(R.id.go_button);
        announcement_button = findViewById(R.id.announcement_button);
        lasttrain_button = findViewById(R.id.lastTraintimings);
        map_button = findViewById(R.id.map_button);

        //Adapters used to give suggestions when user types into the boxes
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_spinner_dropdown_item, stationNames);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_spinner_dropdown_item, stationNames);

        from_box.setAdapter(adapter);
        to_box.setAdapter(adapter2);

        //This part of the code will run when the "Go" button is clicked
        go_button.setOnClickListener(v -> {
            String from_box_str = from_box.getText().toString();
            String to_box_str = to_box.getText().toString();
            boolean fromChecker = stationChecker(from_box_str);
            boolean toChecker = stationChecker(to_box_str);

            if (fromChecker && toChecker && !(from_box_str.equalsIgnoreCase(to_box_str))) {
                openActivity2(from_box_str, to_box_str);
            } else {
                //Input invalid
                popUp();
            }
        });

        announcement_button.setOnClickListener(v -> {
            openAnnouncement();
        });

        lasttrain_button.setOnClickListener(v -> {
            openLasttrain();
        });

        map_button.setOnClickListener(v -> {
            openMap();
        });

        //To make soft keyboard disappear upon touching anything on the layout
        findViewById(R.id.mainActivitylinearlayout).setOnTouchListener((v, event) -> {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            return true;
        });
    }

    /**
     * This method is used open GoPopUp.class
     */
    public void popUp() {
        Intent intent = new Intent(this, GoPopUp.class);
        startActivity(intent);
    }

    /**
     * This method is used to open AnnouncementActivity.class
     */
    public void openAnnouncement() {
        Intent intent = new Intent(this, AnnouncementActivity.class);
        startActivity(intent);
    }

    /**
     * This method is used to open LasttrainActivity.class
     */
    public void openLasttrain() {
        Intent intent = new Intent(this, LasttrainActivity.class);
        startActivity(intent);
    }

    /**
     * This method is used to open MapActivity.class
     */
    public void openMap() {
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }

    /**
     * This method is used to open Activity2.class
     * @param from_box_str The input that the user entered in the "From" box
     * @param to_box_str   The input that the user entered in the "To" box
     */
    public void openActivity2(String from_box_str, String to_box_str) {
        Intent intent = new Intent(this, Activity2.class);
        //The 2 lines below passes the inputs the user entered for "From" and "To" to the next activity
        intent.putExtra("fromBox", String.valueOf(from_box_str));
        intent.putExtra("toBox", String.valueOf(to_box_str));
        startActivity(intent);
    }

    /**
     * This method checks if the user input is valid.
     * @param input The string to check against.
     * @return      True if the input belongs in the stationNames array.
     */
    public boolean stationChecker(String input) {
        for (String station: stationNames) {
            if (station.equalsIgnoreCase(input)) {
                return true;
            }
        }
        //for loop exited. Input is not in the array.
        return false;
    }

    /**
     * This method is used to initialise the list of stations for the ArrayAdapter
     */
    private void initStations() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(getAssets().open("EdgesTimeWait.txt")));
        String line;
        while ((line = br.readLine()) != null) {
            if (line.length() == 1 || line.length() == 2) {
                continue;                                      //To avoid putting the waiting time in the array.
            } else {
                String parts[] = line.split(" ", 2); //Splits the string into 2 parts.
                if (stationChecker(parts[1])) {
                    continue;
                } else {
                    stationNames.add(parts[1]);
                }
            }
        }
    }
}