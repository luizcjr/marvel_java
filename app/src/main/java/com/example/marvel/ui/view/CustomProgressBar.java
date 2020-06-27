package com.example.marvel.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.marvel.R;
import com.example.marvel.util.Util;

public class CustomProgressBar extends View {

    private TypedArray attributes;
    private int percentage, color;

    public CustomProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        attributes = context.obtainStyledAttributes(attrs, R.styleable.CustomProgressBar);
        percentage = attributes.getInt(R.styleable.CustomProgressBar_progressInt, 0);
        color = context.getResources().getColor(android.R.color.white);
        attributes.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(color);
        paint.setStrokeWidth(Util.convertDpToPixel(1f));

        final float spacing = Util.convertDpToPixel(5);
        final float lineH = Util.convertDpToPixel(10);
        final float fullH = Util.convertDpToPixel(12);

        int index = 1;

        for (int i = 44; index <= i; ++index) {

            float startX = (float) (index * spacing);
            float startY = (float) Util.convertDpToPixel(2);
            float stopX = (float) (index * spacing);
            float stopY = (float) lineH;

            double var13 = (double) (this.percentage * 44 / 100);
            int position = (int) Math.ceil(var13);

            if (index < position) {
                canvas.drawLine(startX, startY, stopX, stopY, paint);
            } else if (index == position) {
                startY = 0;
                stopY = fullH;
                canvas.drawLine(startX, startY, stopX, stopY, paint);
            } else {
                paint.setColor(this.getResources().getColor(R.color.primaryGrey));
                canvas.drawLine(startX, startY, stopX, stopY, paint);
            }
        }

        super.onDraw(canvas);
    }

    public void setProgress(int value) {
        percentage = value;
    }
}
