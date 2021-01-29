package com.cl.loglib;

import android.support.annotation.NonNull;
import android.util.Log;

/**
 * tips:
 * 1. 打印堆栈信息
 * 2. File输出
 * 3. 模拟控制台
 */
public class iLog {
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
        log(type,LogManager.getInstance().getLogConfig().getGlobalTag(),contents);
    }

    public static void log(@LogType.TYPE int type, @NonNull String tag, Object... contents) {
        log(LogManager.getInstance().getLogConfig(),type,tag,contents);
    }

    public static void log(@NonNull LogConfig logConfig,@LogType.TYPE int type, @NonNull String tag, Object... contents) {
        if(!logConfig.enable())
            return ;
        //TODO
        StringBuilder sb = new StringBuilder();
        String body = parseBody(contents);
        sb.append(body);
        Log.println(type,tag,body);

    }

    private static String parseBody(@NonNull Object[] objects)
    {
        StringBuilder sb = new StringBuilder();
        for (Object o : objects) {
            sb.append(o.toString()).append(";");
        }
        if (sb.length()>0)
        {
            sb.deleteCharAt(sb.length()-1);
        }
        return sb.toString();
    }
}
