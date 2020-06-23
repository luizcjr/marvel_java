package com.example.marvel.ui.base;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.marvel.AppApplication;
import com.example.marvel.R;
import com.example.marvel.api.services.ApiRepository;
import com.example.marvel.di.DaggerViewModelComponent;
import com.example.marvel.ui.activities.network.NetworkListener;
import com.example.marvel.util.Util;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseViewModel extends ViewModel {

    public Context context;
    public LifecycleOwner lifecycleOwner;
    public CompositeDisposable disposable = new CompositeDisposable();
    public MutableLiveData<Boolean> loading = new MutableLiveData<Boolean>();
    public MutableLiveData<String> loadError = new MutableLiveData<String>();
    public static final int TYPE_ANIMATION_INTERNET_ACCESS_SUCCESS = 2;
    private NetworkListener networkListener = new NetworkListener() {
        @Override
        public <A> void openActivity(Class<A> activity, boolean isFinished, Bundle bundle) {
            Intent intent = new Intent(context, activity);
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            context.startActivity(intent);
        }

        @Override
        public void onNetworkConnectionChanged(boolean isConnected) {

        }
    };

    public void beforeRequest() {
        this.loading.postValue(true);
    }

    public void afterRequest() {
        this.loading.postValue(false);
    }

    public void afterRequest(Throwable e) {
        this.loading.postValue(false);
        this.loadError.postValue(Util.getMessageErrorObject(e));
    }

    @Inject
    protected ApiRepository apiRepository;

    public BaseViewModel() {
        DaggerViewModelComponent.create().inject(this);
    }

    @Override
    protected void onCleared() {
        disposable.clear();

        super.onCleared();
    }

    /*
     *  @Param int type - type final animation
     *  @Param Class<A> activity? - for redirect or null to return previous activity (onBackPressed)
     *
     * Use this method when you want to treat the end of the lottie's animation
     * */
    public <A> Animator.AnimatorListener callbackAnimator(int type, Class<A> activity) {
        return new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {}

            @Override
            public void onAnimationEnd(Animator animation) {
                switch (type) {
                    case TYPE_ANIMATION_INTERNET_ACCESS_SUCCESS:
                        networkListener.openActivity(activity, false, null);
                        break;
                    default:
                        throw new RuntimeException(
                                context.getResources().getString(
                                        R.string.text_message_invalid_type_animation
                                )
                        );
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {}

            @Override
            public void onAnimationRepeat(Animator animation) {}
        };
    }
}

