package com.bigcustard.blurp.testutils;

public class Sleep {

    public static void forOneMs() {

        forDuration(1);
    }

    public static void forDuration(long millis) {

        try {
            Thread.sleep(millis);
        } catch(InterruptedException e) {
            // Never going to happen.
        }
    }

}
