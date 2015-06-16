package com.bigcustard.blurp.core.commands;

import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.*;

public class LogPrintCommand implements Command {

    private final String textToPrint;
    private final Colour colour;
    private final double alpha;

    public LogPrintCommand(String text, Colour colour, double alpha) {

        this.textToPrint = text;
        this.colour = colour;
        this.alpha = alpha;
    }

    @Override
    public void execute(float deltaTime) {

        BlurpStore.runtimeConsole.print(textToPrint, colour, alpha);
    }
}
