package com.bigcustard.blurp.model;

import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.model.effects.*;
import com.bigcustard.blurp.model.events.*;

public abstract class Camera {

    public double x, y;

    public double zoom;

    public double angle;

    public Colour colour;

    public double transparency;

    public Camera setX(double newX) {

        this.x = newX;
        return this;
    }

    public Camera setY(double newY) {

        this.y = newY;
        return this;
    }

    public Camera setZoom(double newZoom) {

        this.zoom = newZoom;
        return this;
    }

    public Camera setAngle(double newAngle) {

        this.angle = newAngle;
        return this;
    }

    public Camera setTransparency(double newTransparency) {

        this.transparency = newTransparency;
        return this;
    }

    public Camera setColour(Colour newColour) {

        this.colour = newColour;
        return this;
    }

    public Camera setPosition(double x, double y) {

        this.x = x;
        this.y = y;
        return this;
    }

    public Camera rotateBy(double degrees) {

        this.angle += degrees;
        return this;
    }

    public Camera runEffect(EffectBase effectToRun) {

        return runEffect(effectToRun, CameraEventHandler.NULL);
    }

    public Camera runEffect(EffectBase effectToRun, CameraEventHandler whatToDoAtEnd) {

        return runEffect(effectToRun, whatToDoAtEnd, ExistingEffectStrategy.CombineWithExistingEffect);
    }

    public abstract Camera runEffect(EffectBase effectToRun, CameraEventHandler whatToDoAtEnd, ExistingEffectStrategy existingEffectStrategy);

    public abstract boolean isRunningAnEffect();

    public Camera stopEffects() {

        return runEffect(null);
    }

    @Override
    public String toString() {

        return String.format("Camera: x=%.1f y=%.1f zoom=%.2f angle=%.1f colour=%s transparency=%.2f", x, y, zoom, angle, colour, transparency);
    }
}
