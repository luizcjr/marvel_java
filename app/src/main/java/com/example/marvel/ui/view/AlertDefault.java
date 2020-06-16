package com.example.marvel.ui.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.widget.AppCompatButton;

import com.example.marvel.R;

import java.util.ArrayList;
import java.util.List;

public class AlertDefault extends Dialog {

    // views
    private TextView titleLabel;
    private TextView contentLabel;
    private LinearLayout actionsContainer;

    //
    private String title;
    private String content;
    boolean error;
    private List<AppCompatButton> buttonList;
    private Context context;

    public AlertDefault(Context context, String content, boolean error) {
        this(context, null, content, error);
    }

    public AlertDefault(Context context, String title, String content) {
        this(context, title, content, false);
    }

    public AlertDefault(Context context, String title, String content, boolean error) {
        super(context);
        this.title = title;
        this.content = content;
        this.error = error;
        this.buttonList = new ArrayList<>();
        this.context = context;

        // disable cancel
        setCancelable(false);
        setCanceledOnTouchOutside(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog);

        // assign views
        titleLabel = findViewById(R.id.alertTitle);
        contentLabel = findViewById(R.id.alertContent);
        actionsContainer = findViewById(R.id.actionsContainer);

        if (error)
            setErrorAlert();
        else
            setSuccessAlert();

        // update content
        contentLabel.setText(content);

        // check button
        if (buttonList.size() == 0) {
            addButton(getContext().getString(R.string.alert_ok_action),
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            hide();
                        }
                    });
        }

        if (buttonList.size() > 0) {
            actionsContainer.setWeightSum(buttonList.size());

            for (AppCompatButton button : buttonList) {
                actionsContainer.addView(button);
            }
        }
    }

    private void setErrorAlert() {
        titleLabel.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_sad, 0, 0, 0);

        if (title == null)
            titleLabel.setText(getContext().getString(R.string.alert_error_title));
        else
            updateTitleLabel();
    }

    private void setSuccessAlert() {
        titleLabel.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_smile, 0, 0, 0);

        if (title == null)
            titleLabel.setText(getContext().getString(R.string.alert_success_title));
        else
            updateTitleLabel();
    }

    private void updateTitleLabel() {
        titleLabel.setText(title);
    }

    public void addButton(String title, View.OnClickListener clickListener) {
        addButton(title, 0, clickListener);
    }

    public void addButton(String title, int styleId, View.OnClickListener clickListener) {
        if (styleId == 0) {
            styleId = R.style.ButtonAlert;
        }

        AppCompatButton button = new AppCompatButton(new ContextThemeWrapper(getContext(), styleId), null, 0);
        button.setText(title);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1f
        );
        params.setMarginStart(20);
        button.setLayoutParams(params);
        button.setMinWidth(0);

        if (clickListener != null) {
            button.setOnClickListener(clickListener);
        }

        buttonList.add(button);
    }
}

