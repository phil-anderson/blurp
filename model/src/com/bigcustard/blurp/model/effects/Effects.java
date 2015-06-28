package com.bigcustard.blurp.model.effects;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.constants.*;

public abstract class Effects {

    public int defaultDuration;
    public EffectStyle defaultStyle;

    public Effects setDefaultDuration(int newDefaultDurationInMilliseconds) {

        this.defaultDuration = newDefaultDurationInMilliseconds;
        return this;
    }

    public Effects setDefaultStyle(EffectStyle newDefaultStyle) {

        this.defaultStyle = newDefaultStyle;
        return this;
    }

    public Effect zoomTo(double newZoom) {

        return scaleTo(newZoom, newZoom);
    }

    public Effect zoomBy(double newZoom) {

        return scaleBy(newZoom, newZoom);
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

    public abstract Effect transparency(double newTransparency);

    public abstract Effect colour(Colour newColour);

    public abstract EffectGroup combine(EffectBase... effectsToCombine);

    public abstract EffectGroup sequence(EffectBase... effectsToDoInSequence);
}
