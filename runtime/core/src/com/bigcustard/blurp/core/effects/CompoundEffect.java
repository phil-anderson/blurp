package com.bigcustard.blurp.core.effects;

import aurelienribon.tweenengine.*;
import com.bigcustard.blurp.apimodel.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.effects.*;

public abstract class CompoundEffect extends EffectImpl {

    protected final BaseEffect[] effects;

    public CompoundEffect(BaseEffect... effects) {

        this.effects = effects;
    }

    // TODO - Loads of casting to get rid of, but not at the expense of over-complicating wth a visitor pattern.
    protected void pushEffectsToTimeline(Timeline timeline, Sprite sprite) {

        for(BaseEffect effect : effects) {

            if (effect instanceof PauseEffect) {
                timeline.pushPause(((PauseEffect) effect).getDuration());
            } else {
                BaseTween baseTween = ((EffectImpl) effect).getTween(sprite);
                if (effect instanceof TweenEffect) {
                    timeline.push((Tween) baseTween);
                } else if (effect instanceof CompoundEffect) {
                    timeline.push((Timeline) baseTween);
                }
            }
        }
    }
}