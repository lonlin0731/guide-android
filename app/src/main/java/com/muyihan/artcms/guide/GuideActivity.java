package com.muyihan.artcms.guide;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.muyihan.artcms.R;
import com.prolificinteractive.parallaxpager.ParallaxContextWrapper;

/**
 * Created by chaochen on 14-9-22.
 */

public class GuideActivity extends AppCompatActivity {

    public static final String BROADCAST_GUIDE_ACTIVITY = "BROADCAST_GUIDE_ACTIVITY";
//    BroadcastReceiver receiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            GuideActivity.this.finish();
//        }
//    };
    private Uri mUri;

    @Override
    protected void attachBaseContext(Context newBase) {
        //ParallaxPager and Calligraphy don't seem to play nicely together
        //The solution was to add a listener for view creation events so that we can hook up
        // Calligraphy to our view creation calls instead.
        super.attachBaseContext(
                new ParallaxContextWrapper(new ParallaxContextWrapper(newBase))
        );
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_parallax);

//        IntentFilter filter = new IntentFilter();
//        filter.addAction(BROADCAST_GUIDE_ACTIVITY);
//        registerReceiver(receiver, filter);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content, new ParallaxFragment())
                    .commit();
        }
    }

//    @Override
//    protected void onDestroy() {
//        if (receiver != null) {
//            unregisterReceiver(receiver);
//            receiver = null;
//        }
//        super.onDestroy();
//    }

}
