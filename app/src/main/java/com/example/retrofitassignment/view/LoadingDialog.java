package com.example.retrofitassignment.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;

import com.example.retrofitassignment.R;

public class LoadingDialog extends Dialog {
    Context context;
    private Listener listener;

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public interface Listener {
        void onDismissListener();
    }

    public LoadingDialog(@NonNull Context context) {
        super(context);
        this.context = context;
        setCancelable(true);
    }

    public LoadingDialog(@NonNull Context context, boolean cancelable) {
        super(context);
        this.context = context;
        setCancelable(cancelable);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_loading_new);
        Window window = getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.gravity = Gravity.CENTER;
        window.setAttributes(layoutParams);

        ProgressBar progressBar = findViewById(R.id.progress_wheel);
        Button button = findViewById(R.id.btn_cancel);

        button.setOnClickListener(v -> {
            listener.onDismissListener();
        });

        progressBar.setIndeterminateDrawable(AppCompatResources.getDrawable(context, R.drawable.loading_progress));

    }


}
