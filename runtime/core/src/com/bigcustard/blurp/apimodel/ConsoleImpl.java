package com.bigcustard.blurp.apimodel;

import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.core.commands.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.constants.*;

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

    public void reset() {

        colour = Colours.LIGHT_GREY;
        alpha = 1;
    }
}
