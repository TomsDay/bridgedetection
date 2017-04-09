package com.suken.bridgedetection;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;

import android.os.Environment;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import com.baidu.mapapi.SDKInitializer;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.suken.bridgedetection.activity.BaseActivity;
import com.suken.bridgedetection.location.LocationManager;
import com.suken.bridgedetection.storage.UserInfo;
import com.suken.bridgedetection.util.UiUtil;
import com.yuntongxun.ecdemo.common.CCPAppManager;
import com.yuntongxun.ecdemo.common.utils.FileAccessor;


import java.io.*;

public class BridgeDetectionApplication extends Application {

    private static BridgeDetectionApplication mInstance;

    public static String mDeviceId = "";

    public static UserInfo mCurrentUser = null;
    public static boolean mHasCacheUser = false;
    public static boolean mIsOffline = false;
    public static BaseActivity mCurrentActivity = null;


    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    @Override
    public void onCreate() {
        mInstance = this;
        LocationManager.getInstance();
        super.onCreate();
        TelephonyManager manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        mDeviceId = manager.getDeviceId();
        UiUtil.setAlarm(this);
        CCPAppManager.setContext(mInstance);
        FileAccessor.initFileAccess();
        SDKInitializer.initialize(mInstance);
        initImageLoader();
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable ex) {
                String exStr = Log.getStackTraceString(ex);
                ex.printStackTrace();
                write(exStr);
            }
        });




    }

    private void initImageLoader() {
        File cacheDir = StorageUtils.getOwnCacheDirectory(getApplicationContext(), "ECSDK_Demo/image");
        ImageLoaderConfiguration config = new ImageLoaderConfiguration
                .Builder(this)
                .threadPoolSize(1)//线程池内加载的数量
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .memoryCache(new WeakMemoryCache())
                // .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(CCPAppManager.md5FileNameGenerator)
                // 将保存的时候的URI名称用MD5 加密
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .diskCache(new UnlimitedDiscCache(cacheDir ,null ,CCPAppManager.md5FileNameGenerator))//自定义缓存路径
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                // .writeDebugLogs() // Remove for release app
                .build();//开始构建
        ImageLoader.getInstance().init(config);
    }
    public void write(String message) {
        Log.e("BridgeDetection", message);
        Log.e("BridgeDetection", message);
        Log.e("BridgeDetection", message);
        Log.e("BridgeDetection", message);
        Log.e("BridgeDetection", message);
        Log.e("BridgeDetection", message);
        Log.e("BridgeDetection", message);
        try {
            String state = Environment.getExternalStorageState();
            if (TextUtils.equals(state, Environment.MEDIA_MOUNTED)) {
                String dir = "/sdcard/com.suken.bridgedetection";
                File dirFile = new File(dir);
                if (dirFile != null && !dirFile.exists()) {
                    dirFile.mkdir();
                }
                String path = dir + "/log.log";
                File file = new File(path);
                if (file != null) {
                    long size = file.length();
                    if (size > 1 * 1024 * 1024 * 5) {
                        file.delete();
                    }
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    FileOutputStream fos = new FileOutputStream(file, true);
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
                    bw.newLine();
                    PackageInfo info;
                    info = getPackageManager().getPackageInfo(getPackageName(), 0);
                    String versionName = info.versionName;
                    bw.write(UiUtil.formatNowTime() + "  " + versionName + message);
                    bw.flush();
                    fos.close();
                    bw.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static BridgeDetectionApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        System.exit(0);
    }
}
