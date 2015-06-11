package com.bigcustard.blurp.core.common;

import com.bigcustard.blurp.model.*;

public class MoveTowardsHandler {

    public static boolean moveTowards(Sprite sprite, double targetX, double targetY, double distanceToMove) {

        // Total distances to move in X and Y
        double xDistance = targetX - sprite.x;
        double yDistance = targetY - sprite.y;
        double distanceToTarget = Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));

        if(distanceToMove > Math.abs(distanceToTarget)) {
            sprite.x = targetX;
            sprite.y = targetY;
            return true;
        }

        double ratio = distanceToMove / distanceToTarget;
        sprite.x += xDistance * ratio;
        sprite.y += yDistance * ratio;

        return false;
    }
}
