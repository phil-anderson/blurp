package com.bigcustard.blurp.core.effects;

import aurelienribon.tweenengine.*;
import com.bigcustard.blurp.apimodel.*;
import com.bigcustard.blurp.model.effects.*;

public abstract class EffectGroupImpl extends EffectImpl<EffectGroup> implements EffectGroup {

    protected final EffectBase[] effects;

    public EffectGroupImpl(EffectBase... effects) {

        this.effects = effects;
    }

    // TODO - Loads of casting to get rid of, but not at the expense of over-complicating wth a visitor pattern.
    protected void pushEffectsToTimeline(Timeline timeline, Object target) {

        for(EffectBase effect : effects) {

            BaseTween baseTween = ((EffectImpl) effect).getTween(target);
            if (effect instanceof TweenEffect) {
                timeline.push((Tween) baseTween);
            } else if (effect instanceof EffectGroupImpl) {
                timeline.push((Timeline) baseTween);
            }
        }
    }
}