package org.devio.as.proj.hi_jetpack;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cl.loglib.LogConfig;
import com.cl.loglib.LogManager;
import com.cl.loglib.LogType;
import com.cl.loglib.Printer.ViewPrinter;
import com.cl.loglib.iLog;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
    private ViewPrinter viewPrinter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPrinter = new ViewPrinter(this);
        printLog();
        viewPrinter.getViewProvider().showFloatingView();
    }

    private void printLog() {
        LogManager.getInstance().addPointer(viewPrinter);
        iLog.log(new LogConfig() {
            @Override
            public boolean includeThread() {
                return true;
            }

            @Override
            public int stackTraceDepth() {
                return 0;
            }
        }, LogType.E, "DemoApplication", "55555");
        iLog.a("9900");
    }
}
