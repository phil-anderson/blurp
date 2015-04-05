package com.bigcustard.blurp.core.commands;

// What a crazy load of visitor-pattern-hoops we have to jump through because of Java's type system.
public interface CommandRequestVisitor {

    public void visit(MoveTowardsRequest moveTowardsRequest, float deltaTime);
}
