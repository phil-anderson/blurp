package com.bigcustard.blurp.core.commands;

import com.bigcustard.blurp.model.*;

public class ConsolePrintCommand implements CommandVisitable {

    private String textToPrint;
    private Colour colour;

    public ConsolePrintCommand(String text, Colour colour) {

        this.textToPrint = text;
        this.colour = colour;
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
}
