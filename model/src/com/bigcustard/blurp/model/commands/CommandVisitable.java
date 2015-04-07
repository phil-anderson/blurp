package com.bigcustard.blurp.model.commands;

public interface CommandVisitable {

    public void accept(CommandVisitor visitor, float deltaTime);
}
