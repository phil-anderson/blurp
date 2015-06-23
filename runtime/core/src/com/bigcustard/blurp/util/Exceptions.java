package com.bigcustard.blurp.util;

import java.io.*;

public class Exceptions {

    public static String getConcatenatedMessage(Throwable throwable) {

        String result = "";
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
}
