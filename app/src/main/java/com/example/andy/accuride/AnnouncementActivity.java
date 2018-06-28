package com.example.andy.accuride;

import android.app.Activity;
import android.os.Bundle;

/**
 * This class is used to display announcements regarding train delays/breakdowns
 * @author Andy Chan
 */
public class AnnouncementActivity extends Activity{

    /**
     * This method displays the contents of the announcements.
     * Content taken from activity_announcement.xml
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);
    }
}
