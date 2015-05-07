package com.bigcustard.blurp.core.commands;

import com.bigcustard.blurp.core.*;

public class SetDebugModeExecutor {

    public void execute(SetDebugModeCommand request) {

        BlurpStore.blurpScreen.enableDebug(request.isDebugEnabled(), request.getDebugColour());
    }
}
