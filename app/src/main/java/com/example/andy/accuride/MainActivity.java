package com.example.andy.accuride;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;

import java.io.IOException;
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

        //Adapters used to give suggestions when user types into the boxes
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_spinner_dropdown_item, stationNames);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_spinner_dropdown_item, stationNames);

        from_box.setAdapter(adapter);
        to_box.setAdapter(adapter2);

        //This part of the code will run when the "Go" button is clicked
        go_button.setOnClickListener(v -> {
            Editable from_box_str = from_box.getText();
            Editable to_box_str = to_box.getText();
            boolean fromChecker = stationChecker(from_box_str);
            boolean toChecker = stationChecker(to_box_str);

            if (fromChecker && toChecker) {
                openActivity2(from_box_str, to_box_str);
            } else {
                //Input invalid
                popUp();
            }
        });

        announcement_button.setOnClickListener(v -> {
            openAnnouncement();
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
     * This method is used to open Activity2.class
     * @param from_box_str The input that the user entered in the "From" box
     * @param to_box_str   The input that the user entered in the "To" box
     */
    public void openActivity2(Editable from_box_str, Editable to_box_str) {
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
    public boolean stationChecker(Editable input) {
        String userInput = input.toString();

        for (String station: stationNames) {
            if (station.equalsIgnoreCase(userInput)) {
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
//        Scanner sc = new Scanner(new
//                File("C:\\Users\\Andy\\Desktop\\MyProjects\\AndroidStudioProjects\\Accuride\\app\\src\\main\\java\\com\\example\\andy\\accuride\\EdgesTimeWait.txt")
//        );
//
//        while (sc.hasNextLine()) {
//            String str = sc.nextLine();
//            if (str.length() == 1) {
//                continue;
//            } else {
//                stationNames.add(str);
//            }
//        }

        stationNames.add("Changi Airport");
        stationNames.add("Expo");
        stationNames.add("Pasir Ris");
        stationNames.add("Tampines");
        stationNames.add("Simei");
        stationNames.add("Tanah Merah");
        stationNames.add("Bedok");
        stationNames.add("Kembangan");
        stationNames.add("Eunos");
        stationNames.add("Paya Lebar");
        stationNames.add("Aljunied");
        stationNames.add("Kallang");
        stationNames.add("Lavender");
        stationNames.add("Bugis");
        stationNames.add("City Hall");
        stationNames.add("Raffles Place");
        stationNames.add("Tanjong Pagar");
        stationNames.add("Outram Park");
        stationNames.add("Tiong Bahru");
        stationNames.add("Redhill");
        stationNames.add("Queenstown");
        stationNames.add("Commonwealth");
        stationNames.add("Buona Vista");
        stationNames.add("Dover");
        stationNames.add("Clementi");
        stationNames.add("Jurong East");
        stationNames.add("Chinese Garden");
        stationNames.add("Lakeside");
        stationNames.add("Boon Lay");
        stationNames.add("Pioneer");
        stationNames.add("Joo Koon");
        stationNames.add("Gul Circle");
        stationNames.add("Tuas Cresent");
        stationNames.add("Tuas West Road");
        stationNames.add("Tuas Link");
    }
}