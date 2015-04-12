package com.bigcustard.blurp.core.commands;

import com.bigcustard.blurp.ui.*;

public class SetDebugModeExecutor {

    public void execute(SetDebugModeCommand request, BlurpScreen screen) {

        if(request.isDebug()) {
            screen.enableDebug(request.isDebugHidden());
        } else {
            screen.disableDebug();
        }
    }
}
