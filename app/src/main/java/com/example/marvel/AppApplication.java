package com.example.marvel;

import android.app.Application;

import com.example.marvel.ui.activities.network.NetworkListener;
import com.example.marvel.util.receiver.NetworkBroadcastReceiver;

public class AppApplication extends Application {

    private static AppApplication singleton = null;

    public static AppApplication getInstance() {

        if (singleton == null) {
            singleton = new AppApplication();
        }
        return singleton;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
    }

    public static void setNetworkListener(NetworkListener listener) {
        NetworkBroadcastReceiver.networkListener = listener;
    }
}
