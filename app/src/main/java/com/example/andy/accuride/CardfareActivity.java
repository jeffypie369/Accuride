package com.example.andy.accuride;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * This class is used when the user clicks on the "Card Fare" button
 * in Activity2.
 * @author Andy Chan
 */
public class CardfareActivity extends Activity {

    /**
     * This method displays the pop-up content of the application at this page.
     * Content taken from the card_fare.xml file.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_fare);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT);
    }
}
