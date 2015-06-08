package com.bigcustard.blurp.model;

import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.model.effects.*;
import com.bigcustard.blurp.model.events.*;

public abstract class Camera {

    public double x, y;

    public double zoom;

    public double rotation;

    public Colour colour;

    public double alpha;

    public Camera x(double newX) {

        this.x = newX;
        return this;
    }

    public Camera y(double newY) {

        this.y = newY;
        return this;
    }

    public Camera zoom(double newZoom) {

        this.zoom = newZoom;
        return this;
    }

    public Camera rotation(double newRotation) {

        this.rotation = newRotation;
        return this;
    }

    public Camera alpha(double newAlpha) {

        this.alpha = newAlpha;
        return this;
    }

    public Camera colour(Colour newColour) {

        this.colour = newColour;
        return this;
    }

    public Camera position(double x, double y) {

        this.x = x;
        this.y = y;
        return this;
    }

    public Camera rotateBy(double degrees) {

        this.rotation += degrees;
        return this;
    }

    public Camera runEffect(EffectBase effectToRun) {

        return runEffect(effectToRun, CameraEventHandler.NULL);
    }

    public Camera runEffect(EffectBase effectToRun, CameraEventHandler whatToDoAtEnd) {

        return runEffect(effectToRun, whatToDoAtEnd, ExistingEffectStrategy.CombineWithExisting);
    }

    public abstract Camera runEffect(EffectBase effectToRun, CameraEventHandler whatToDoAtEnd, ExistingEffectStrategy existingEffectStrategy);

    public abstract boolean isRunningEffect();

    public Camera stopEffect() {

        return runEffect(null);
    }

    @Override
    public String toString() {

        return "Camera {" +
                   " x=" + x +
                   ", y=" + y +
                   ", zoom=" + zoom +
                   ", rotation=" + rotation +
                   ", colour=" + colour +
                   ", alpha=" + alpha +
                   '}';
    }
}
