package com.bigcustard.blurp.core.effects;

public class MoveEffect extends TweenEffect {

    public MoveEffect(double targetX, double targetY, int duration, boolean relative) {

        super(TweenTypes.POSITION, duration, relative, (float) targetX, (float) targetY);
    }
}

