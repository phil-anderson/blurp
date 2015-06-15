package com.bigcustard.blurp.core.commands;

import com.bigcustard.blurp.core.*;

public class LogClearCommand implements Command {

    @Override
    public void execute(float deltaTime) {

        BlurpStore.runtimeLog.clear();
    }
}
