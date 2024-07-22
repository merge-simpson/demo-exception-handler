package com.example.demo.common.utils.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

public final class ExceptionUtils {
    public static String getStackTrace(Throwable exception) {
        StringWriter sw = new StringWriter();
        exception.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }

    private ExceptionUtils() {}
}
