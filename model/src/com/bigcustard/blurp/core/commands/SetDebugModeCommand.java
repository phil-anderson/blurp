package com.bigcustard.blurp.core.commands;

public class SetDebugModeCommand implements CommandVisitable {

    private boolean debug;
    private boolean debugHidden;

    public SetDebugModeCommand(boolean debug, boolean debugHidden) {

        this.debug = debug;
        this.debugHidden = debugHidden;
    }

    @Override
    public void accept(CommandVisitor visitor, float deltaTime) {

        visitor.visit(this);
    }

    public boolean isDebug() {

        return debug;
    }

    public boolean isDebugHidden() {

        return debugHidden;
    }
}
