package com.cl.loglib;

import com.cl.loglib.Printer.ILogPrinter;
import com.cl.loglib.formatter.StackTraceLogFormatter;
import com.cl.loglib.formatter.ThreadLogFormatter;

public abstract class LogConfig {

    public static final int MAX_LEN = 512;
    static StackTraceLogFormatter stackTraceLogFormatter = new StackTraceLogFormatter();
    static ThreadLogFormatter threadLogFormatter = new ThreadLogFormatter();

    public JsonParser injectJsonParser() {
        return null;
    }

    public String getGlobalTag() {
        return "iLog";
    }

    public boolean enable() {
        return true;
    }

    public boolean includeThread() {
        return false;
    }

    public int stackTraceDepth() {
        return 5;
    }


    public ILogPrinter[] getPrints() {
        return null;
    }

    public interface JsonParser {
        String toJson(Object o);
    }

}
