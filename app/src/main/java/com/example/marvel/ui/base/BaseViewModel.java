package com.example.marvel.ui.base;

import android.content.Context;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.marvel.api.services.ApiRepository;
import com.example.marvel.di.DaggerViewModelComponent;
import com.example.marvel.util.Util;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseViewModel extends ViewModel {

    public Context context;
    public LifecycleOwner lifecycleOwner;
    public CompositeDisposable disposable = new CompositeDisposable();
    public MutableLiveData<Boolean> loading = new MutableLiveData<Boolean>();
    public MutableLiveData<String> loadError = new MutableLiveData<String>();

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
}

