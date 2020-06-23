package com.example.marvel.ui.activities.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.ViewModelProvider;

import com.example.marvel.R;
import com.example.marvel.databinding.MainBinding;
import com.example.marvel.ui.base.BaseActivity;

public class MainActivity extends BaseActivity<MainBinding, MainViewModel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.viewModel().context = this;
    }

    @Override
    public int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    public int binding() {
        return BR.viewModel;
    }

    @Override
    public MainViewModel viewModel() {
        return new ViewModelProvider(this).get(MainViewModel.class);
    }

    @Override
    protected boolean verifyChangedNetworkState() {
        return true;
    }
}