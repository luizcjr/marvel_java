package com.example.marvel.ui.base;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.example.marvel.AppApplication;
import com.example.marvel.ui.activities.network.NetworkActivity;
import com.example.marvel.ui.activities.network.NetworkListener;
import com.example.marvel.util.receiver.NetworkBroadcastReceiver;

import static com.example.marvel.ui.activities.network.NetworkViewModel.NETWORK_TAG;

public abstract class BaseActivity<T extends ViewDataBinding, D extends BaseViewModel> extends AppCompatActivity implements NetworkListener {

    public T viewDataBinding() {
        return vDatabinding;
    }

    private T vDatabinding;
    private D vModel;

    private BroadcastReceiver networkReceiver;

    private static final String FILTER_ACTION_CONNECTIVITY_CHANGE = "android.net.conn.CONNECTIVITY_CHANGE";

    protected void initializeDatabinding() {
        vDatabinding = DataBindingUtil.setContentView(this, layoutId());
        vModel = viewModel();
        vDatabinding.setVariable(binding(), vModel);
        vDatabinding.executePendingBindings();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initializeDatabinding();
    }

    @LayoutRes
    public abstract int layoutId();

    public abstract int binding();

    public abstract D viewModel();

    protected abstract boolean verifyChangedNetworkState();

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        if (!isConnected && !getClass().getSimpleName().equals(NETWORK_TAG)) {
            this.openActivity(NetworkActivity.class, false, null);
        }
    }

    @Override
    public <A> void openActivity(Class<A> activity, boolean isFinished, Bundle bundle) {
        if (isFinished) finish();
        Intent intent = new Intent(this, activity);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void onRegisterReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(FILTER_ACTION_CONNECTIVITY_CHANGE);

        this.networkReceiver = new NetworkBroadcastReceiver();
        this.registerReceiver(this.networkReceiver, filter);
    }

    public void onUnregisterReceiver() {
        if (this.networkReceiver != null) {
            this.unregisterReceiver(this.networkReceiver);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        /* Checks whether the activity allows you to monitor the state of the internet */
        if (this.verifyChangedNetworkState()) {
            this.onRegisterReceiver();
            return;
        }

        this.networkReceiver = null;
    }

    @Override
    protected void onResume() {
        super.onResume();

        /* Register listener of network state */
        AppApplication.setNetworkListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.onUnregisterReceiver();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}