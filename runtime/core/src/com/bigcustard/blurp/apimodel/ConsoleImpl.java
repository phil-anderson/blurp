package com.bigcustard.blurp.apimodel;

import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.core.commands.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.constants.*;

public class ConsoleImpl extends Console {

    @Override
    public Console clear() {

        BlurpStore.runtimeRepository.registerCommand(new LogClearCommand());
        return this;
    }

    @Override
    public Console print(Object textToPrint) {

        BlurpStore.runtimeRepository.registerCommand(new LogPrintCommand("" + textToPrint, colour, transparency));
        return null;
    }

    public void reset() {

        colour = Colours.LightGrey;
        transparency = 1;
    }
}
