package com.bigcustard.blurp.core.commands;

import com.bigcustard.blurp.model.*;

public class ChangeZOrderCommand implements CommandVisitable {

    private final Sprite target;
    private final Sprite otherSprite;
    private final int delta;

    public ChangeZOrderCommand(Sprite target, Sprite otherSprite, int delta) {

        this.target = target;

        this.otherSprite = otherSprite;
        this.delta = delta;
    }

    public Sprite getTarget() {

        return target;
    }

    public Sprite getOtherSprite() {

        return otherSprite;
    }

    public int getDelta() {

        return delta;
    }

    @Override
    public void accept(CommandVisitor visitor, float deltaTime) {

        visitor.visit(this);
    }
}
