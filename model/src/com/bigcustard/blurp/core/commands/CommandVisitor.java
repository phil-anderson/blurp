package com.bigcustard.blurp.core.commands;

// What a crazy load of visitor-pattern-hoops we have to jump through because of Java's type system.
public interface CommandVisitor {

    public void visit(MoveTowardsCommand moveTowardsCommand, float deltaTime);

    void visit(SetDebugModeCommand setDebugModeCommand);
}
