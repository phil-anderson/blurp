package com.bigcustard.blurp.apimodel;

import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.core.commands.*;
import com.bigcustard.blurp.model.*;

public class ConsoleImpl extends Console {

    @Override
    public Console clear() {

        BlurpStore.runtimeRepository.registerCommand(new ConsoleClearCommand());
        return this;
    }

    @Override
    public Console print(String textToPrint) {

        BlurpStore.runtimeRepository.registerCommand(new ConsolePrintCommand(textToPrint, colour, alpha));
        return null;
    }
}
