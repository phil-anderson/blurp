package com.bigcustard.blurp.core.effects;

import aurelienribon.tweenengine.*;
import com.bigcustard.blurp.model.*;

public class CameraAccessor implements TweenAccessor<Camera>, TweenTypes {

    @Override
    public int getValues(Camera camera, int tweenType, float[] returnValues) {

        switch(tweenType) {
            case MOVE : {
                returnValues[0] = (float) camera.x;
                returnValues[1] = (float) camera.y;
                return 2;
            }
            case SCALE : {
                returnValues[0] = (float) camera.zoom;
                returnValues[1] = (float) camera.zoom;
                return 2;
            }
            case ROTATE : {
                returnValues[0] = (float) camera.angle;
                return 1;
            }
            case ALPHA : {
                returnValues[0] = (float) camera.transparency;
                return 1;
            }
            case COLOUR : {
                Colour colour = camera.colour;
                returnValues[0] = (float) colour.red;
                returnValues[1] = (float) colour.green;
                returnValues[2] = (float) colour.blue;
                return 3;
            }
        }
        return 0;
    }

    @Override
    public void setValues(Camera camera, int tweenType, float[] newValues) {

        switch(tweenType) {
            case MOVE : {
                camera.position(newValues[0], newValues[1]);
                break;
            }
            case SCALE : {
                camera.zoom(Math.max(newValues[0], newValues[1]));
                break;
            }
            case ROTATE : {
                camera.rotation(newValues[0]);
                break;
            }
            case ALPHA : {
                camera.alpha(newValues[0]);
                break;
            }
            case COLOUR : {
                camera.colour(new Colour(newValues[0], newValues[1], newValues[2]));
                break;
            }
        }
    }
}
