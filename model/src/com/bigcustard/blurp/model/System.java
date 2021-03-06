package com.bigcustard.blurp.model;

import com.bigcustard.blurp.model.constants.*;

public abstract class System {

    public System debugMode(boolean enable) {

        debugMode(enable, Colours.LimeGreen);
        return this;
    }

    public abstract System debugMode(boolean enable, Colour debugColour);

    public abstract int getFps();

    public abstract int getFrameCount();

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
    public abstract void sleep(int milliseconds);

    public abstract void stop();

    public abstract void restart();

    public abstract void terminate();
}
