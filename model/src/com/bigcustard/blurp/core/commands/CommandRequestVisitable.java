package com.bigcustard.blurp.core.commands;

public interface CommandRequestVisitable {

    public void accept(CommandRequestVisitor visitor, float deltaTime);
}
