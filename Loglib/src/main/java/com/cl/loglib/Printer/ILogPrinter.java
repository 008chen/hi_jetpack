package com.cl.loglib.Printer;

import androidx.annotation.NonNull;

import com.cl.loglib.LogConfig;

public interface ILogPrinter {
    void print(@NonNull LogConfig logConfig, int level, String tag, @NonNull String printData);
}
