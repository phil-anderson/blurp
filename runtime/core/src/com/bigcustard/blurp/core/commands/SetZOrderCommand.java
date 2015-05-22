package com.bigcustard.blurp.core.commands;

import com.bigcustard.blurp.model.*;

public class SetZOrderCommand implements CommandVisitable {

    private final Sprite target;
    private final int zOrder;

    public SetZOrderCommand(Sprite target, int zOrder) {

        this.target = target;
        this.zOrder = zOrder;
    }

    public Sprite getTarget() {

        return target;
    }

    public int getzOrder() {

        return zOrder;
    }

    @Override
    public void accept(CommandVisitor visitor, float deltaTime) {

        visitor.visit(this);
    }
}
