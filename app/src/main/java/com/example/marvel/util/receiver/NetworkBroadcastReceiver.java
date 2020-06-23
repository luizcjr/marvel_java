package com.example.marvel.util.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.marvel.ui.activities.network.NetworkListener;

public class NetworkBroadcastReceiver extends BroadcastReceiver {

    public static NetworkListener networkListener;

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null
                && activeNetwork.isConnectedOrConnecting();

        if (networkListener != null) {
            networkListener.onNetworkConnectionChanged(isConnected);
        }
    }
}
