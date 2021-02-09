package org.devio.as.proj.hi_jetpack;

import android.app.Application;

import com.cl.loglib.Printer.ConsolePrinter;
import com.cl.loglib.LogConfig;
import com.cl.loglib.LogManager;
import com.google.gson.Gson;

public class DemoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LogManager.init(new LogConfig() {

            @Override
            public JsonParser injectJsonParser() {
                return o -> {
                    Gson gson  = new Gson();
                    return gson.toJson(o);
                };
            }


            @Override
            public String getGlobalTag() {
                return "DemoApplication";
            }

            @Override
            public boolean enable() {
                return true;
            }
        });

        LogManager.getInstance().addPointer(new ConsolePrinter());


        ActivityManager.Companion.getInstance().init(this);
    }
}
