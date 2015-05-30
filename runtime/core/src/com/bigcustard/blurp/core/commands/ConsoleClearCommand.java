package com.bigcustard.blurp.core.commands;

import com.bigcustard.blurp.core.*;

public class ConsoleClearCommand implements Command {

    @Override
    public void execute(float deltaTime) {

        BlurpStore.runtimeConsole.clear();
    }
}
