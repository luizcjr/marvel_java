package com.example.marvel.util;

import android.content.Context;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.marvel.R;

public class ImageUtil {
    static CircularProgressDrawable getProgressDrawable(Context context) {
        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(context);
        circularProgressDrawable.setStrokeWidth(10f);
        circularProgressDrawable.setCenterRadius(50f);
        circularProgressDrawable.start();
        return circularProgressDrawable;
    }

    static void loadImage(ImageView imageView, String uri, CircularProgressDrawable progressDrawable) {
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(progressDrawable)
                .centerCrop()
                .error(R.drawable.ic_image_not_found);

        GlideApp.with(imageView.getContext())
                .setDefaultRequestOptions(options)
                .load(uri)
                .thumbnail(0.5f)
                .into(imageView);
    }

    @BindingAdapter("android:imageUrl")
    public static void loadImage(ImageView view, String url) {
        loadImage(view, url, getProgressDrawable(view.getContext()));
    }
}
