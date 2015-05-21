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

        return scaleTo(newZoom, newZoom);
    }

    public Effect scaleTo(double newScale) {

        return scaleTo(newScale, newScale);
    }

    public Effect scaleBy(double newScale) {

        return scaleBy(newScale, newScale);
    }

    public abstract Effect moveTo(double newX, double newY);

    public abstract Effect moveBy(double changeInX, double changeInY);

    public abstract Effect rotateTo(double newAngle);

    public abstract Effect rotateBy(double changeInAngle);

    public abstract Effect scaleTo(double newScaleX, double newScaleY);

    public abstract Effect scaleBy(double newScaleX, double newScaleY);

    public abstract Effect alpha(double newAlpha);

    public abstract Effect colour(Colour newColour);

    public abstract EffectGroup combine(EffectBase... effectsToCombine);

    public abstract EffectGroup sequence(EffectBase... effectsToDoInSequence);
}
