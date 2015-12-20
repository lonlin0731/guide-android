package com.muyihan.artcms;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.muyihan.artcms.guide.GuideActivity;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 15-12-11.
 */

public class AppStart extends AppCompatActivity implements Handler.Callback {

    private static final int HANDLER_MESSAGE_ANIMATION = 0;
    private static final int HANDLER_MESSAGE_NEXT_ACTIVITY = 1;

    @Bind(R.id.image)
    ImageView image;
    @Bind(R.id.foreMask)
    View foreMask;

    Animation aniStart ;
    Handler mHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_start);
        ButterKnife.bind(this);

        init();

    }

    void init()
    {
        mHandler = new Handler(this);
        aniStart = AnimationUtils.loadAnimation(this, R.anim.start);

        settingBackground();

        aniStart.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mHandler.removeMessages(0);
                mHandler.sendEmptyMessageDelayed(HANDLER_MESSAGE_NEXT_ACTIVITY, 500);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        mHandler.removeMessages(0);
        mHandler.sendEmptyMessageDelayed(HANDLER_MESSAGE_ANIMATION, 900);
    }

    private void settingBackground()
    {
        ImageSize imageSize = new ImageSize(AppContext.sWidthPix, AppContext.sHeightPix);
        image.setImageBitmap(ImageLoader.getInstance().loadImageSync("drawable://" + R.drawable.entrance, imageSize));
    }

    @Override
    public boolean handleMessage(Message msg) {
        if (msg.what == HANDLER_MESSAGE_ANIMATION) {
            playAnimator1();
        } else if (msg.what == HANDLER_MESSAGE_NEXT_ACTIVITY) {
            next();
        }
        return true;
    }

    private void playAnimator1()
    {
        foreMask.startAnimation(aniStart);
    }

    void next() {
        Intent intent;

        if (true)
        {
            intent = new Intent(this, GuideActivity.class);
        }
        else
        {
            //intent = new Intent(this, MainActivity_.class);
        }

        startActivity(intent);
        overridePendingTransition(R.anim.scroll_in, R.anim.scroll_out);

        finish();
    }
}
