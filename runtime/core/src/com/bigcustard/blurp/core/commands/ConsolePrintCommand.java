package com.bigcustard.blurp.core.commands;

import com.bigcustard.blurp.model.*;

public class ConsolePrintCommand implements CommandVisitable {

    private final String textToPrint;
    private final Colour colour;
    private final double alpha;

    public ConsolePrintCommand(String text, Colour colour, double alpha) {

        this.textToPrint = text;
        this.colour = colour;
        this.alpha = alpha;
    }

    @Override
    public void accept(CommandVisitor visitor, float deltaTime) {

        visitor.visit(this);
    }

    public String getTextToPrint() {

        return textToPrint;
    }

    public Colour getColour() {

        return colour;
    }

    public double getAlpha() {
        return alpha;
    }
}
