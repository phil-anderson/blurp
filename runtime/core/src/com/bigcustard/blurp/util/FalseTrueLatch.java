package com.bigcustard.blurp.util;

public class FalseTrueLatch {

    private boolean previousCondition = false;

    public boolean getValue(boolean condition) {

        boolean result = !previousCondition && condition;
        previousCondition = condition;
        return result;
    }
}
