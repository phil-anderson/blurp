package com.bigcustard.blurp.core.effects;

import aurelienribon.tweenengine.*;
import com.bigcustard.blurp.apimodel.*;
import com.bigcustard.blurp.model.effects.*;

public abstract class CompoundEffect extends EffectImpl {

    protected final Effect[] effects;

    public CompoundEffect(Effect... effects) {

        this.effects = effects;
    }

    // TODO - Loads of casting to get rid of, but not at the expense of over-complicating wth a visitor pattern.
    protected void pushEffectsToTimeline(Timeline timeline, Object target) {

        for(Effect effect : effects) {

            BaseTween baseTween = ((EffectImpl) effect).getTween(target);
            if (effect instanceof TweenEffect) {
                timeline.push((Tween) baseTween);
            } else if (effect instanceof CompoundEffect) {
                timeline.push((Timeline) baseTween);
            }
        }
    }
}