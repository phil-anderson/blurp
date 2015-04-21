package com.bigcustard.blurp.model;

import java.util.*;

public class Utils {

    // TODO: Actually... This shouldn't be here in the Model package.
    public static final Utils ENGINE_INSTANCE = new Utils();

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
    public double random(double limit) {

        return randomiser.nextDouble() * limit;
    }

    /**
     * @param low The lowest random number that could be created
     * @param high The high value that the random number is guaranteed to be less than
     * @return A random number that is greater than or equal the specified low value, but less than the specified high
     * value.
     */
    public double random(double low, double high) {

        double range = high - low;
        return random(range) + low;
    }

    /**
     * @param low The lowest value that could be returned
     * @param high The highest value that could be returned
     * @return A number between the specified low and high values. Repeatedly calling this will result in a sequence of
     * numbers that follow a sine wave.
     */
    public double wave(double low, double high, double wavelength) {

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

    /**
     * Puts your blurp script to sleep for approximately the specified number of milliseconds, after which it'll carry
     * on normally again.
     * <p>
     * Note that although it puts your script to sleep, it doesn't put blurp to sleep, so it will continue to run in
     * the background.
     *
     * @param milliseconds The number of milliseconds to sleep for.
     */
    public void sleep(long milliseconds) {

        try {
            Thread.sleep(milliseconds);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
