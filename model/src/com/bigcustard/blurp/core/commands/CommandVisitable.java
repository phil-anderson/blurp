package com.bigcustard.blurp.core.commands;

public interface CommandVisitable {

    public void accept(CommandVisitor visitor, float deltaTime);
}
