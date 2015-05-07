package com.bigcustard.blurp.core.commands;

import com.bigcustard.blurp.model.*;

public class SetDebugModeCommand implements CommandVisitable {

    private final boolean debugEnabled;
    private final Colour debugColour;

    public SetDebugModeCommand(boolean debugEnabled, Colour debugColour) {

        this.debugEnabled = debugEnabled;
        this.debugColour = debugColour;
    }

    @Override
    public void accept(CommandVisitor visitor, float deltaTime) {

        visitor.visit(this);
    }

    public boolean isDebugEnabled() {

        return debugEnabled;
    }

    public Colour getDebugColour() {

        return debugColour;
    }
}
