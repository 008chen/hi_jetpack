package com.cl.loglib.util;

import android.util.Log;

public class StackTraceUtil {


    public static StackTraceElement[] getCropRealStackTrace(StackTraceElement[] callStack, String ignorePackageName, int maxDepth) {
        Log.d("xxx", "getCropRealStackTrace: "+ignorePackageName);
        return cropStackTrace(getRealStackTrace(callStack, ignorePackageName), maxDepth);
    }

    /**
     * 获取除忽略包之外的堆栈
     *
     * @param callStack
     * @param ignorePackageName
     * @return
     */
    private static StackTraceElement[] getRealStackTrace(StackTraceElement[] callStack, String ignorePackageName) {
        int ignoreDepth = 0;
        int allDepth = callStack.length;
        String classname;
        for (int depth = allDepth-1; depth > 0; depth--) {
            classname = callStack[depth].getClassName();
            if (ignorePackageName != null && classname.startsWith(ignorePackageName)) {
                ignoreDepth = depth + 1;
                break;
            }
        }
        int realDepth = allDepth - ignoreDepth;
        StackTraceElement[] realStack = new StackTraceElement[realDepth];
        System.arraycopy(callStack, ignoreDepth, realStack, 0, realDepth);
        return realStack;
    }

    /**
     * 裁剪堆栈信息
     *
     * @param callStack
     * @param maxDepth
     * @return
     */
    private static StackTraceElement[] cropStackTrace(StackTraceElement[] callStack, int maxDepth) {
        int realDepth = callStack.length;
        if (maxDepth > 0) {
            realDepth = Math.min(realDepth, maxDepth);
        }
        StackTraceElement[] realStack = new StackTraceElement[realDepth];
        System.arraycopy(callStack, 0, realStack, 0, realDepth);
        return realStack;
    }
}
