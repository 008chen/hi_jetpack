package com.cl.loglib;

//import android.support.annotation.NonNull;

import androidx.annotation.NonNull;

import com.cl.loglib.Printer.ILogPrinter;
import com.cl.loglib.util.StackTraceUtil;

import java.util.Arrays;
import java.util.List;

/**
 * tips:
 * 1. 打印堆栈信息
 * 2. File输出
 * 3. 模拟控制台
 */
public class iLog {

    static final String LOG_PACKAGE;

    static {
        String classname = iLog.class.getName();
        LOG_PACKAGE = classname.substring(0, classname.lastIndexOf('.') + 1);
    }

    public static void v(Object... contents) {
        log(LogType.V, contents);
    }

    public static void vt(String tag, Object... contents) {
        log(LogType.V, tag, contents);
    }

    public static void d(Object... contents) {
        log(LogType.D, contents);
    }

    public static void dt(String tag, Object... contents) {
        log(LogType.D, tag, contents);
    }


    public static void i(Object... contents) {
        log(LogType.I, contents);
    }

    public static void it(String tag, Object... contents) {
        log(LogType.I, tag, contents);
    }


    public static void w(Object... contents) {
        log(LogType.W, contents);
    }

    public static void wt(String tag, Object... contents) {
        log(LogType.W, tag, contents);
    }

    public static void e(Object... contents) {
        log(LogType.E, contents);
    }

    public static void et(String tag, Object... contents) {
        log(LogType.E, tag, contents);
    }

    public static void a(Object... contents) {
        log(LogType.E, contents);
    }

    public static void at(String tag, Object... contents) {
        log(LogType.E, tag, contents);
    }


    public static void log(@LogType.TYPE int type, Object... contents) {
        log(type, LogManager.getInstance().getLogConfig().getGlobalTag(), contents);
    }

    public static void log(@LogType.TYPE int type, @NonNull String tag, Object... contents) {
        log(LogManager.getInstance().getLogConfig(), type, tag, contents);
    }

    public static void log(@NonNull LogConfig logConfig, @LogType.TYPE int type, @NonNull String tag, Object... contents) {
        if (!logConfig.enable())
            return;

        StringBuilder sb = new StringBuilder();
        if (logConfig.includeThread()) {
            String threadInfo = LogConfig.threadLogFormatter.format(Thread.currentThread());
            sb.append(threadInfo).append("\n");
        }
        if (logConfig.stackTraceDepth() > 0) {
            String stackTrace = LogConfig.stackTraceLogFormatter.format(StackTraceUtil.getCropRealStackTrace(new Throwable().getStackTrace(), LOG_PACKAGE, logConfig.stackTraceDepth()));
            sb.append(stackTrace).append("\n");

        }
        String body = parseBody(contents, logConfig);
        sb.append(body);
        List<ILogPrinter> printers = logConfig.getPrints() != null ? Arrays.asList(logConfig.getPrints()) : LogManager.getInstance().getPrinters();
        if (printers == null)
            return;
        for (ILogPrinter printer : printers) {
            printer.print(logConfig, type, tag, sb.toString());
        }

    }

    private static String parseBody(@NonNull Object[] objects, @NonNull LogConfig logConfig) {
        if (logConfig.injectJsonParser() != null) {
            return logConfig.injectJsonParser().toJson(objects);
        }
        StringBuilder sb = new StringBuilder();
        for (Object o : objects) {
            sb.append(o.toString()).append(";");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
