package com.bigcustard.blurp.core.commands;

import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.util.*;

public class SetDebugModeExecutor {

    public void execute(SetDebugModeCommand request) {

        BlurpStore.blurpScreen.enableDebug(request.isDebugEnabled(), request.getDebugColour());
        BlurpStore.debugMode = request.isDebugEnabled();
        BlurpStore.debugColour = Convert.toGdxColour(request.getDebugColour());
    }
}
