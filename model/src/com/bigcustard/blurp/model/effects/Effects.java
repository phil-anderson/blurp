package com.bigcustard.blurp.model.effects;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.constants.*;

public abstract class Effects {

    public double defaultDuration;
    public EffectStyle defaultEffectStyle;

    public Effects defaultDuration(double newDefaultDuration) {

        this.defaultDuration = newDefaultDuration;
        return this;
    }

    public Effects defaultEffectStyle(EffectStyle newDefaultEffectStyle) {

        this.defaultEffectStyle = newDefaultEffectStyle;
        return this;
    }

    public Effect zoom(double newZoom) {

        return scale(newZoom, newZoom);
    }

    public Effect scale(double newScale) {

        return scale(newScale, newScale);
    }

    public abstract Effect moveTo(double newX, double newY);

    public abstract Effect moveBy(double changeInX, double changeInY);

    public abstract Effect rotateTo(double newAngle);

    public abstract Effect rotateBy(double changeInAngle);

    public abstract Effect scale(double newScaleX, double newScaleY);

    public abstract Effect alpha(double newAlpha);

    public abstract Effect colour(Colour newColour);

    public abstract EffectGroup combine(EffectBase... effectsToCombine);

    public abstract EffectGroup sequence(EffectBase... effectsToDoInSequence);
}
