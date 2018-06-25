package com.example.andy.accuride;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

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

    /**
     * This method will render the landing page of the application when its open.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initStations();

        from_box = findViewById(R.id.from_box);
        to_box = findViewById(R.id.to_box);
        go_button = findViewById(R.id.go_button);

        //Adapters used to give suggestions when user types into the boxes
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_spinner_dropdown_item, stationNames);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_spinner_dropdown_item, stationNames);

        from_box.setAdapter(adapter);
        to_box.setAdapter(adapter2);

        //Need to handle the case if both inputs are the same
        go_button.setOnClickListener(v -> {
            Editable from_box_str = from_box.getText();
            Editable to_box_str = to_box.getText();
            if (from_box_str.length() == 0 || to_box_str.length() == 0) {
                //Either of the boxes are empty or if input is the same.
                popUp();
            } else {
                openActivity2(from_box_str, to_box_str);
            }
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
     * This Method is used to initialise the list of stations for the ArrayAdapter
     */
    private void initStations() {
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
    }
}