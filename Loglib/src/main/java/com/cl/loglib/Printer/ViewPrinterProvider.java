package com.cl.loglib.Printer;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cl.loglib.util.DisplayUtil;

public class ViewPrinterProvider {
    private FrameLayout rootView;
    private View floatingView;
    private boolean isOpen;
    private FrameLayout logView;
    private RecyclerView recyclerView;

    public ViewPrinterProvider(FrameLayout rootView, RecyclerView recyclerView) {
        this.rootView = rootView;
        this.recyclerView = recyclerView;
    }


    public static final String LOG_TAG_VIEW = "LOG_TAG_VIEW";
    public static final String LOG_FLOATING_VIEW = "LOG_FLOATING_VIEW";

    public void showFloatingView() {

        if (rootView.findViewWithTag(LOG_FLOATING_VIEW) != null) {
            return;
        }
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.BOTTOM | Gravity.END;
        View floatingView = genFloatingView();
        floatingView.setTag(LOG_FLOATING_VIEW);
        floatingView.setBackgroundColor(Color.BLACK);
        floatingView.setAlpha(0.8f);
        params.bottomMargin = DisplayUtil.dp2px(recyclerView.getContext(), 100);
        rootView.addView(floatingView, params);
    }

    private View genFloatingView() {
        if (floatingView != null) {
            return floatingView;
        }
        TextView textView = new TextView(rootView.getContext());
        textView.setOnClickListener(v -> {
            if (!isOpen) {
                Log.d("xxx", "showLogView: ");
                showLogView();
            }
        });
        textView.setText("Log");
        textView.setTextColor(Color.WHITE);
        floatingView = textView;
        return floatingView;

    }


    private void showLogView() {
        if (rootView.findViewWithTag(LOG_TAG_VIEW) != null) {
            return;
        }
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DisplayUtil.dp2px(rootView.getContext(), 160));
        params.gravity = Gravity.BOTTOM;
        View logView = genLogView();
        logView.setTag(LOG_TAG_VIEW);
        rootView.addView(logView, params);
        isOpen = true;
    }

    private View genLogView() {
        if (logView != null) {
            return logView;
        }
        FrameLayout log_View = new FrameLayout(rootView.getContext());
        log_View.setBackgroundColor(Color.BLACK);
        log_View.addView(recyclerView);

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.END;
        TextView closeView = new TextView(rootView.getContext());
        closeView.setOnClickListener(v -> {
            Log.d("xxx", "closeView: ");
            closeView();
        });
        closeView.setText("close");
        closeView.setTextColor(Color.WHITE);
        log_View.addView(closeView, params);
        logView = log_View;
        return logView;

    }

    private void closeView() {
        if (logView != null) {
            isOpen = false;
            rootView.removeView(logView);
        }

    }

    private void closeFloatingView() {
        if (floatingView != null) {
            isOpen =true;
            rootView.removeView(floatingView);
        }

    }
}
