package com.bigcustard.blurp.util;

import java.io.*;
import com.bigcustard.blurp.core.*;

public class Exceptions {

    public static String getConcatenatedMessage(Throwable throwable) {

        String result = throwable.getClass().getName() + ": ";
        while(throwable != null) {
            if(throwable.getMessage() != null && !result.contains(throwable.getMessage())) {
                result += throwable.getMessage();
                if(!result.endsWith("\n")) {
                    result += "\n";
                }
            }
            throwable = throwable.getCause();
        }
        return result;
    }

    public static String getStackTraceString(Throwable throwable) {

        StringWriter writer = new StringWriter();
        throwable.printStackTrace(new PrintWriter(writer));
        return writer.toString();
    }

    public static RuntimeException exposeUserTemination(RuntimeException exception) {

        Throwable currentException = exception;
        while(currentException != null) {
            if(currentException instanceof BlurpTerminatedException) {
                return (RuntimeException) currentException;
            }
            currentException = currentException.getCause();
        }
        return exception;
    }
}
