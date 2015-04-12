package com.bigcustard.blurp.core.commands;

import com.bigcustard.blurp.model.*;

public class MoveTowardsExecutor {

    public void execute(MoveTowardsCommand request, float deltaTime) {

        Sprite sprite = request.getSpriteToMove();
        if(sprite.x == request.getTargetX() && sprite.y == request.getTargetY()) return; //Already there

        // Total distances to move in X and Y
        double xDistance = request.getTargetX() - sprite.x;
        double yDistance = request.getTargetY() - sprite.y;
        double distanceToTarget = Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));

        // Distance to move given delta time and speed.
        double distanceDelta = request.getSpeed() * deltaTime;

        if(distanceDelta > Math.abs(distanceToTarget)) {
            sprite.x = request.getTargetX();
            sprite.y = request.getTargetY();
            return;
        }

        // Now break the diagonal delta down into X and Y components.
        double sin = yDistance / distanceToTarget;
        double cos = xDistance / distanceToTarget;

        double xDelta = distanceDelta * cos;
        double yDelta = distanceDelta * sin;

        // TODO: Find a cleaner, more algebraic way to do this (avoid overshooting target)...
        xDelta = xDelta < 0 ? Math.max(xDelta, xDistance)
                            : Math.min(xDelta, xDistance);

        yDelta = yDelta < 0 ? Math.max(yDelta, yDistance)
                            : Math.min(yDelta, yDistance);
        sprite.x += xDelta;
        sprite.y += yDelta;
    }
}
