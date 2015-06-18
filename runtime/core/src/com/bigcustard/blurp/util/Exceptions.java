package com.bigcustard.blurp.util;

public class Exceptions {

    public static String getConcatenatedMessage(Throwable throwable) {

        String result = "";
        while(throwable != null) {
            if(!result.contains(throwable.getMessage())) {
                result += throwable.getMessage();
                if(!result.endsWith("\n")) {
                    result += "\n";
                }
            }
            throwable = throwable.getCause();
        }
        return result;
    }
}
