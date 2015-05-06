package com.bigcustard.blurp.core.effects;

import aurelienribon.tweenengine.*;
import com.bigcustard.blurp.model.*;

public class SpriteAccessor implements TweenAccessor<Sprite>, TweenTypes {

    @Override
    public int getValues(Sprite sprite, int tweenType, float[] returnValues) {

        switch(tweenType) {
            case MOVE : {
                returnValues[0] = (float) sprite.x;
                returnValues[1] = (float) sprite.y;
                return 2;
            }
            case SCALE : {
                returnValues[0] = (float) sprite.scaleX;
                returnValues[1] = (float) sprite.scaleY;
                return 2;
            }
            case ROTATE : {
                returnValues[0] = (float) sprite.rotation;
                return 1;
            }
            case ALPHA : {
                returnValues[0] = (float) sprite.alpha;
                return 1;
            }
            case COLOUR : {
                Colour colour = sprite.colour;
                returnValues[0] = (float) colour.red;
                returnValues[1] = (float) colour.green;
                returnValues[2] = (float) colour.blue;
                return 3;
            }
        }
        return 0;
    }

    @Override
    public void setValues(Sprite sprite, int tweenType, float[] newValues) {

        switch(tweenType) {
            case MOVE : {
                sprite.position(newValues[0], newValues[1]);
                break;
            }
            case SCALE : {
                sprite.scale(newValues[0], newValues[1]);
                break;
            }
            case ROTATE : {
                sprite.rotation(newValues[0]);
                break;
            }
            case ALPHA : {
                sprite.alpha(newValues[0]);
                break;
            }
            case COLOUR : {
                sprite.colour(new Colour(newValues[0], newValues[1], newValues[2]));
                break;
            }
        }
    }
}
