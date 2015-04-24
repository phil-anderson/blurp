package com.bigcustard.blurp.core.commands;

import com.bigcustard.blurp.ui.*;

public class SetDebugModeExecutor {

    public void execute(SetDebugModeCommand request, BlurpScreen screen) {

        screen.enableDebug(request.isDebugEnabled(), request.getDebugColour());
    }
}
