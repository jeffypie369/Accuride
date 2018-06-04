package com.example.andy.accuride;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> stationNames = new ArrayList<>();
    AutoCompleteTextView from_box;
    AutoCompleteTextView to_box;
    //Spinner stationsSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initStations();

        from_box = findViewById(R.id.from_box);
        to_box = findViewById(R.id.to_box);
        //stationsSpinner = findViewById(R.id.stations);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_spinner_dropdown_item, stationNames);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_spinner_dropdown_item, stationNames);

        from_box.setAdapter(adapter);
        to_box.setAdapter(adapter2);

//        stationsSpinner.setAdapter(adapter2);
    }

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
