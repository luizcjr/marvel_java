package com.example.marvel.util;

import androidx.databinding.BindingAdapter;

import com.example.marvel.ui.view.CustomProgressBar;

public class CustomBinding {

    static void progress(CustomProgressBar view, int progress) {
        view.setProgress(progress);
    }

    @BindingAdapter("android:progress")
    public static void setProgress(CustomProgressBar view, int progress) {
        progress(view, progress);
    }
}
