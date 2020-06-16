package com.example.marvel;

import android.app.Application;

public class AppApplication extends Application {

    private static AppApplication singleton = null;

    public static AppApplication getInstance() {

        if(singleton == null)
        {
            singleton = new AppApplication();
        }
        return singleton;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
    }
}
