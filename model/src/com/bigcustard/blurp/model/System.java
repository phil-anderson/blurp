package com.bigcustard.blurp.model;

import com.bigcustard.blurp.model.constants.*;

public abstract class System {

    public final Console console;

    protected System(Console console) {

        this.console = console;
    }

    public System debugMode(boolean enable) {

        debugMode(enable, Colours.LIME_GREEN);
        return this;
    }

    public abstract System debugMode(boolean enable, Colour debugColour);

    public abstract int getFps();

    /**
     * Makes your blurp script pause for the specified number of seconds, after which it'll carry on normally again.
     * <p>
     * WARNING - Effects won't be moving as there will be no blurpify calls, they'll effectively be on hold until the
     * end of the pause.
     *
     * @param milliseconds The number of milliseconds to pause for.
     */
    public abstract void pause(int milliseconds);

    /**
     * Waits for the specified time, whilst blurp still runs
     *
     * @param milliseconds
     */
    public abstract void wait(int milliseconds);

    public abstract void stop();

    public abstract void restart();

    public abstract void terminate();
}
