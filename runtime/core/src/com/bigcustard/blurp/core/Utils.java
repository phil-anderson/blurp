package com.bigcustard.blurp.core;

import com.badlogic.gdx.math.*;

public class Utils {

    private static final double DEG_TO_RAD = 2 * Math.PI / 360;

    public double random(double from, double to) {

        double range = to - from;
        return Math.random() * range + from;
    }

    public double sin(double angle) {

        return MathUtils.sinDeg((float) angle);
    }

    public double cos(double angle) {

        return MathUtils.cosDeg((float) angle);
    }

    public double tan(double angle) {

        return Math.tan(Math.toRadians(angle));
    }
}
