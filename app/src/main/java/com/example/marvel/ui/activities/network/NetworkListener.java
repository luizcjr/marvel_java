package com.example.marvel.ui.activities.network;

import android.os.Bundle;

public interface NetworkListener {
    <A> void openActivity(Class<A> activity, boolean isFinished, Bundle bundle);
    void onNetworkConnectionChanged(boolean isConnected);
}
