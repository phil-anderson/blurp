package com.bigcustard.blurp.model.effects;

import com.bigcustard.blurp.model.*;

public abstract class Effects {

    public double defaultDuration = 1;

    public Effects defaultDuration(double newDefaultDuration) {

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

    public Effect zoom(double newZoom) {

        return zoom(newZoom, defaultDuration);
    }

    public Effect zoom(double newZoom, double duration) {

        return scale(newZoom, newZoom, duration);
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

    public abstract Effect moveTo(double newX, double newY, double duration);

    public abstract Effect moveBy(double changeInX, double changeInY, double duration);

    public abstract Effect rotateTo(double newAngle, double duration);

    public abstract Effect rotateBy(double changeInAngle, double duration);

    public abstract Effect scale(double newScaleX, double newScaleY, double duration);

    public abstract Effect alpha(double newAlpha, double duration);

    public abstract Effect colour(Colour newColour, double duration);

    public abstract Effect combine(Effect firstEffect, Effect secondEffect, Effect... otherEffects);

    public abstract Effect sequence(Effect firstEffect, Effect secondEffect, Effect... otherEffects);
}
