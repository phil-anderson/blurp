package com.bigcustard.blurp.core;

import com.badlogic.gdx.math.*;

public class Utils {

    public double random(double limit) {

        return Math.random() * limit;
    }

    public double wave(double low, double high, double wavelength) {

        double angle = (System.currentTimeMillis() % wavelength) * 360 / (wavelength - 1);

        double range = high - low;
        double positiveSine = (sin(angle) + 1) / 2;
        return positiveSine * range + low;
    }

    public double random(double from, double to) {

        double range = to - from;
        return random(range) + from;
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
