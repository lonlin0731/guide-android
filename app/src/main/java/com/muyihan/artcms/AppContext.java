package com.muyihan.artcms;

import android.content.Context;
import android.widget.ImageView;

import com.muyihan.artcms.base.BaseApplication;
import com.muyihan.artcms.third.MyImageDownloader;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * Created by Administrator on 15-12-11.
 */
public class AppContext extends BaseApplication {

    public static float sScale;
    public static int sWidthPix;
    public static int sHeightPix;


    @Override
    public void onCreate() {
        super.onCreate();

        //初始化Universal Image Loader
        initImageLoader(this);

        //获取屏幕的信息
        sScale = getResources().getDisplayMetrics().density;
        sWidthPix = getResources().getDisplayMetrics().widthPixels;
        sHeightPix = getResources().getDisplayMetrics().heightPixels;
    }

    public static void initImageLoader(Context context) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheSize(50 * 1024 * 1024) // 50 Mb
                .diskCacheFileCount(300)
                .imageDownloader(new MyImageDownloader(context))
                .tasksProcessingOrder(QueueProcessingType.LIFO)
//                .writeDebugLogs() // Remove for release app
                .diskCacheExtraOptions(sWidthPix / 3, sWidthPix / 3, null)
                .build();

        ImageLoader.getInstance().init(config);
    }

    public static String makeSmallUrl(ImageView view, String url) {
        return url;
    }

    public static int dpToPx(int dpValue) {
        return (int) (dpValue * sScale + 0.5f);
    }


}
