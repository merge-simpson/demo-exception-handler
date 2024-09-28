package com.example.common.util.thread;

public class ThreadUtil {
    /**
     * @return string of call stack trace
     */
    public static String callStack() {
        StringBuilder builder = new StringBuilder("call-stack trace");
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();

        for (int i = 2; i < elements.length; i++) {
            StackTraceElement s = elements[i];
            builder.append("\n\tat ");
            builder.append(s.getClassName());
            builder.append(".");
            builder.append(s.getMethodName());
            builder.append("(");
            builder.append(s.getFileName());
            builder.append(":");
            builder.append(s.getLineNumber());
            builder.append(")");
        }

        return builder.toString();
    }
}