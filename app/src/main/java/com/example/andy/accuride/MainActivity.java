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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initStations();

        from_box = findViewById(R.id.from_box);
        to_box = findViewById(R.id.to_box);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_spinner_dropdown_item, stationNames);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_spinner_dropdown_item, stationNames);

        from_box.setAdapter(adapter);
        to_box.setAdapter(adapter2);
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

//package com.example.andy.accuride;
//
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.AutoCompleteTextView;
//import android.widget.Button;
//import android.widget.PopupWindow;
//import android.widget.RelativeLayout;
//
//import java.util.ArrayList;
//
//public class MainActivity extends AppCompatActivity {
//    private ArrayList<String> stationNames = new ArrayList<>();
//    private Button from_button;
//    private Button to_button;
//
//    private AutoCompleteTextView from_box;
//    private AutoCompleteTextView to_box;
//
//    private PopupWindow fromPopupwindow;
//    private PopupWindow toPopupwindow;
//
//    private RelativeLayout relativeLayout;
//
//    //Layout Inflater will allow loading a new layout inside the pop window.
//    private LayoutInflater fromLayoutinflater;
//    private LayoutInflater toLayoutinflater;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        initStations();
//
//        from_box = findViewById(R.id.from_box);
//        to_box = findViewById(R.id.to_box);
//
//        //Layout where the from and to pop up box is supposed to appear at
//        relativeLayout = findViewById(R.id.relative);
//
//        from_button = findViewById(R.id.from_button);
//        to_button = findViewById(R.id.to_button);
//
//        //To Pop up a window when "From" Button is clicked
//        from_button.setOnClickListener(view -> {
//            fromLayoutinflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
//            ViewGroup fromContainer = (ViewGroup) fromLayoutinflater.inflate(R.layout.from_box, null);
//
//            fromPopupwindow = new PopupWindow(fromContainer, 800, 800, true);
//            fromPopupwindow.showAtLocation(relativeLayout, Gravity.NO_GRAVITY, 500, 250);
//
//            fromContainer.setOnTouchListener((view1, motionEvent) -> {
//                fromPopupwindow.dismiss();
//                return true;
//            });
//        });
//
//        //To Pop up a window when "To" Button is clicked
//        to_button.setOnClickListener(view -> {
//            toLayoutinflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
//            ViewGroup fromContainer = (ViewGroup) toLayoutinflater.inflate(R.layout.to_box, null);
//
//            toPopupwindow = new PopupWindow(fromContainer, 400, 400, true);
//            toPopupwindow.showAtLocation(relativeLayout, Gravity.NO_GRAVITY, 500, 500);
//
//            fromContainer.setOnTouchListener((view2, motionEvent) -> {
//                toPopupwindow.dismiss();
//                return true;
//            });
//        });
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_spinner_dropdown_item, stationNames);
//
//        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this,
//                android.R.layout.simple_spinner_dropdown_item, stationNames);
//
////        Bug here
////        from_box.setAdapter(adapter);
////        to_box.setAdapter(adapter2);
//    }
//
//    private void initStations() {
//        stationNames.add("Pasir Ris");
//        stationNames.add("Tampines");
//        stationNames.add("Simei");
//        stationNames.add("Tanah Merah");
//        stationNames.add("Bedok");
//        stationNames.add("Kembangan");
//        stationNames.add("Eunos");
//        stationNames.add("Paya Lebar");
//        stationNames.add("Aljunied");
//        stationNames.add("Kallang");
//        stationNames.add("Lavender");
//        stationNames.add("Bugis");
//    }
//}
