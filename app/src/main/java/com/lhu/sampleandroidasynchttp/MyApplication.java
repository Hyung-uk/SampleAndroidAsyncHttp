package com.lhu.sampleandroidasynchttp;

import android.app.Application;
import android.content.Context;

/**
 * Created by Tacademy on 2015-10-20.
 */
public class MyApplication extends Application {
    private static Context mContext;


    @Override
    public void onCreate() {
        super.onCreate();

        mContext = this;
    }

    public static Context getContext() {
        return mContext;
    }
}

