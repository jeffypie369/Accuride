package com.example.andy.accuride;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * This class is used when the user clicks "Go" after entering
 * an invalid input in both "From" and "To" boxes
 * @author Andy Chan
 */
public class GoPopUp extends Activity {

    /**
     * This method displays the pop-up content of the application at this page.
     * Content taken from the go_popup xml file.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.go_popup);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT);
    }
}
