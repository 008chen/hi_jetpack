package com.cl.loglib;

import android.support.annotation.NonNull;

public class LogManager {
    private LogConfig logConfig;
    private static LogManager instance;

    private LogManager(LogConfig logConfig) {
        this.logConfig = logConfig;
    }

    public static LogManager getInstance() {
        return instance;
    }

    public static void init(@NonNull LogConfig config)
    {
        instance = new LogManager(config);
    }

    public LogConfig getLogConfig()
    {
        return logConfig;
    }


}
