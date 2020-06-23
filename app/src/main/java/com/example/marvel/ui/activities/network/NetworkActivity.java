package com.example.marvel.ui.activities.network;

import android.os.Bundle;
import android.view.View;

import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.ViewModelProvider;

import com.example.marvel.R;
import com.example.marvel.databinding.ActivityNetworkBinding;
import com.example.marvel.ui.activities.main.MainActivity;
import com.example.marvel.ui.base.BaseActivity;

import static com.example.marvel.ui.base.BaseViewModel.TYPE_ANIMATION_INTERNET_ACCESS_SUCCESS;

public class NetworkActivity extends BaseActivity<ActivityNetworkBinding, NetworkViewModel> {

    @Override
    public int layoutId() {
        return R.layout.activity_network;
    }

    @Override
    public int binding() {
        return BR.viewModel;
    }

    @Override
    public NetworkViewModel viewModel() {
        return new ViewModelProvider(this).get(NetworkViewModel.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.viewModel().context = this;
        this.viewDataBinding().setViewModel(this.viewModel());
    }

    @Override
    protected boolean verifyChangedNetworkState() {
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.startAnimationNoAccessInternet();
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        if (isConnected) {
            this.connectionReestablished(MainActivity.class);
        }
    }

    public void startAnimationNoAccessInternet() {
        this.viewDataBinding().txtLabelNoInternet.setVisibility(View.VISIBLE);
        this.viewDataBinding().clNoInternet.setVisibility(View.VISIBLE);
    }

    public <A> void connectionReestablished(Class<A> activity) {
        /* Details */
        this.viewDataBinding().txtLabelNoInternet.setVisibility(View.GONE);
        this.viewDataBinding().clConnectionReestablished.setVisibility(View.VISIBLE);

        this.viewDataBinding().lottieConnectionReestablished.setVisibility(View.VISIBLE);
        this.viewDataBinding().clNoInternet.setVisibility(View.GONE);

        this.viewDataBinding().lottieConnectionReestablished.addAnimatorListener(
                this.viewModel().callbackAnimator(
                        TYPE_ANIMATION_INTERNET_ACCESS_SUCCESS, activity
                )
        );
    }
}