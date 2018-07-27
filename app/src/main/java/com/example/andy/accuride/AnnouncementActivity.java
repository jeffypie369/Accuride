package com.example.andy.accuride;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;

import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

/**
 * This class is used to display announcements regarding train delays/breakdowns
 * @author Andy Chan
 */
public class AnnouncementActivity extends ListActivity {

    /**
     * This method displays the contents of the announcements.
     * Content taken from activity_announcement.xml
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Initialising TwitterKit and changing default configuration
        TwitterConfig config = new TwitterConfig.Builder(this)
                .logger(new DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(new TwitterAuthConfig("insert key here", "insert secret here"))
                .debug(true)
                .build();
        Twitter.initialize(config);

        setContentView(R.layout.activity_announcement);

        //Setting up Twitter Timeline. xml file edited to display this.
        final UserTimeline userTimeline = new UserTimeline.Builder()
                .screenName("LTAsg")
                .build();
        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(this)
                .setTimeline(userTimeline)
                .build();
        setListAdapter(adapter);
    }
}