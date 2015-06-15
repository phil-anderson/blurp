package com.bigcustard.blurp.model;

import java.lang.System;
import java.util.*;

public class Utils {

    private Random randomiser = new Random();

    /**
     * Sets the random seed, which is a special value that is used as the basis of random numbers. The sequence of
     * random numbers that is generated will always be the same for a given random seed, so this is a good way of
     * getting a repeatable sequence. If you just want genuinely random numbers though, then you'll never need to set
     * this, and the numbers will be different every time you run your program.
     *
     * @param seed The random seed to set
     * @return The Utils object
     */
    public Utils setRandomSeed(long seed) {

        randomiser.setSeed(seed);
        return this;
    }

    /**
     * @param limit The limit that the returned random numbers must be smaller than.
     * @return A random number that is greater than or equal zero, but less than limit.
     */
    public double randomUpTo(double limit) {

        return randomiser.nextDouble() * limit;
    }

    /**
     * @param low The lowest random number that could be created
     * @param high The high value that the random number is guaranteed to be less than
     * @return A random number that is greater than or equal the specified low value, but less than the specified high
     * value.
     */
    public double randomInRange(double low, double high) {

        double range = high - low;
        return randomUpTo(range) + low;
    }

    public Colour randomColour() {

        return new Colour(randomUpTo(1), randomUpTo(1), randomUpTo(1));
    }

//    /**
//     * Returns a random colour based on the baseColour. The greater the variation, the more different the colours could
//     * be.
//     * @param baseColour The colour to use as the basis for the random colours
//     * @param variation A value between 0 and 1, where 1 is no variation (i.e. the base colour will always be returned),
//     *                  and 1 is completely random colours.
//     * @return The new random colour.
//     */
//    public Colour randomColour(Colour baseColour, double variation) {
//
//
//        return new Colour(baseColour.red * random(1 - variation, 1 + variation))
//    }

    /**
     * Returns a number between the specified low and high values. Repeatedly calling this will result in a sequence of
     * numbers that follow a sine wave between the two values.
     *
     * @param low The lowest value that could be returned
     * @param high The highest value that could be returned
     * @return A number between the specified low and high values.
     */
    public double waveValue(double low, double high, int wavelength) {

        double angle = (System.currentTimeMillis() % wavelength) * 360 / (wavelength - 1);

        double range = high - low;
        double positiveSine = (sin(angle) + 1) / 2;
        return positiveSine * range + low;
    }

    /**
     * @param angle The angle expressed in degrees
     * @return The sine of the specified angle.
     */
    public double sin(double angle) {

        return Math.sin(Math.toRadians(angle));
    }

    /**
     * @param angle The angle expressed in degrees
     * @return The cosine of the specified angle.
     */
    public double cos(double angle) {

        return Math.cos(Math.toRadians(angle));
    }

    /**
     * @param angle The angle expressed in degrees
     * @return The tangent of the specified angle.
     */
    public double tan(double angle) {

        return Math.tan(Math.toRadians(angle));
    }
}
