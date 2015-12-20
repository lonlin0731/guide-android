package com.muyihan.artcms.guide;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.muyihan.artcms.R;
import com.prolificinteractive.parallaxpager.ParallaxContextWrapper;

/**
 * Created by Administrator on 15-12-11.
 */

public class GuideActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(
                new ParallaxContextWrapper(new ParallaxContextWrapper(newBase))
        );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.guide_activity);

        getSupportFragmentManager().beginTransaction()
                    .add(R.id.content, new ParallaxFragment())
                    .commit();
    }

}
