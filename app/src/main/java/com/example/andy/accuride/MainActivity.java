package com.example.andy.accuride;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> stationNames = new ArrayList<>();
    AutoCompleteTextView from_box;
    AutoCompleteTextView to_box;
    Button go_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initStations();

        from_box = findViewById(R.id.from_box);
        to_box = findViewById(R.id.to_box);
        go_button = findViewById(R.id.go_button);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_spinner_dropdown_item, stationNames);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_spinner_dropdown_item, stationNames);

        from_box.setAdapter(adapter);
        to_box.setAdapter(adapter2);

        //Need to add condition that if from box and to box empty it doesn't go anywhere
        go_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });
    }

    //Method to open Activity2
    public void openActivity2() {
        Intent intent = new Intent(this, Activity2.class);
        startActivity(intent);
    }

    //Method to initialise list of stations
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