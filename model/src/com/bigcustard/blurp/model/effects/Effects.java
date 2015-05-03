package com.bigcustard.blurp.model.effects;

import com.bigcustard.blurp.model.*;

public abstract class Effects {

    public int defaultDuration = 1;

    public Effects defaultDuration(int newDefaultDuration) {

        this.defaultDuration = newDefaultDuration;
        return this;
    }

    public Effect moveTo(double targetX, double targetY) {

        return moveTo(targetX, targetY, defaultDuration);
    }

    public Effect moveBy(double targetX, double targetY) {

        return moveBy(targetX, targetY, defaultDuration);
    }

    public Effect rotateTo(double newAngle) {

        return rotateTo(newAngle, defaultDuration);
    }

    public Effect rotateBy(double changeInAngle) {

        return rotateBy(changeInAngle, defaultDuration);
    }

    public Effect scale(double newScale) {

        return scale(newScale, newScale);
    }

    public Effect scale(double newScaleX, double newScaleY) {

        return scale(newScaleX, newScaleY, defaultDuration);
    }

    public Effect alpha(double newAlpha) {

        return alpha(newAlpha, defaultDuration);
    }

    public Effect colour(Colour newColour) {

        return colour(newColour, defaultDuration);
    }


    public abstract Effect moveTo(double newX, double newY, int duration);

    public abstract Effect moveBy(double changeInX, double changeInY, int duration);

    public abstract Effect rotateTo(double newAngle, int duration);

    public abstract Effect rotateBy(double changeInAngle, int duration);

    public abstract Effect scale(double newScaleX, double newScaleY, int duration);

    public abstract Effect alpha(double newAlpha, int duration);

    public abstract Effect colour(Colour newColour, int duration);

    public abstract BaseEffect pause(int duration);

    public abstract Effect combine(BaseEffect firstEffect, BaseEffect secondEffect, BaseEffect... otherEffects);

    public abstract Effect sequence(BaseEffect firstEffect, BaseEffect secondEffect, BaseEffect... otherEffects);

}
