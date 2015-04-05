package com.bigcustard.blurp.core.commands;

import com.bigcustard.blurp.model.*;

public class MoveTowardsRequest implements CommandRequestVisitable {

    private final Sprite spriteToMove;

    private final double targetX, targetY;

    private final double speed;

    public MoveTowardsRequest(Sprite spriteToMove, double targetX, double targetY, double speed) {

        this.spriteToMove = spriteToMove;
        this.targetX = targetX;
        this.targetY = targetY;
        this.speed = speed;
    }

    @Override
    public void accept(CommandRequestVisitor visitor, float deltaTime) {
        visitor.visit(this, deltaTime);
    }

    Sprite getSpriteToMove() {

        return spriteToMove;
    }

    double getTargetX() {

        return targetX;
    }

    double getTargetY() {

        return targetY;
    }

    double getSpeed() {

        return speed;
    }
}
