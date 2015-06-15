package com.bigcustard.blurp.apimodel;

import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.core.commands.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.constants.*;

public class LogImpl extends Log {

    @Override
    public Log clear() {

        BlurpStore.runtimeRepository.registerCommand(new LogClearCommand());
        return this;
    }

    @Override
    public Log print(Object textToPrint) {

        BlurpStore.runtimeRepository.registerCommand(new LogPrintCommand("" + textToPrint, colour, transparency));
        return null;
    }

    public void reset() {

        colour = Colours.LIGHT_GREY;
        transparency = 1;
    }
}
