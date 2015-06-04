package com.bigcustard.blurp.core.commands;

import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.*;

public class SetDebugModeCommand implements Command {

    private final boolean debugEnabled;
    private final Colour debugColour;

    public SetDebugModeCommand(boolean debugEnabled, Colour debugColour) {

        this.debugEnabled = debugEnabled;
        this.debugColour = debugColour;
    }

    @Override
    public void execute(float deltaTime) {

        BlurpStore.blurpScreen.enableDebug(debugEnabled, debugColour);
    }
}
