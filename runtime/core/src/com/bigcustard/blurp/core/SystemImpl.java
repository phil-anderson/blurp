package com.bigcustard.blurp.core;

import com.bigcustard.blurp.apimodel.*;
import com.bigcustard.blurp.core.commands.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.System;

import static com.bigcustard.blurp.core.BlurpTerminatedException.CompletionAction.*;

/**
 * Allows the user to call methods that specific to the Blurp engine.
 */
public class SystemImpl extends System {

    @Override
    public System debugMode(boolean enable, Colour debugColour) {

        BlurpStore.runtimeRepository.registerCommand(new SetDebugModeCommand(enable, debugColour));
        return this;
    }

    @Override
    public int getFps() {

        return BlurpStore.blurpScreen.getFps();
    }

    @Override
    public void stop() {

        throw new BlurpTerminatedException(Normal);
    }

    @Override
    public void restart() {

        throw new BlurpTerminatedException(Restart);
    }

    @Override
    public void terminate() {

        throw new BlurpTerminatedException(Terminate);
    }
}
