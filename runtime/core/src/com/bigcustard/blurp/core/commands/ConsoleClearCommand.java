package com.bigcustard.blurp.core.commands;

public class ConsoleClearCommand implements CommandVisitable {

    @Override
    public void accept(CommandVisitor visitor, float deltaTime) {

        visitor.visit(this);
    }
}
