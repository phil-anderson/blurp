package com.bigcustard.blurp.core.effects;

import aurelienribon.tweenengine.*;
import com.bigcustard.blurp.apimodel.*;
import com.bigcustard.blurp.model.effects.*;

public abstract class CompoundEffect extends EffectImpl {

    private final BaseEffect[] effects;

    public CompoundEffect(BaseEffect... effects) {

        this.effects = effects;
    }

    protected void pushEffectsToTimeline(Timeline timeline, Object sprite) {

        for(BaseEffect effect : effects) {

            if (effect instanceof TweenFactory) {
                timeline.push(((TweenFactory) effect).createTween(sprite));
            } else if (effect instanceof TimelineFactory) {
                timeline.push(((TimelineFactory) effect).createTimeline(sprite));
            } else if (effect instanceof PauseEffect) {
                timeline.pushPause(((PauseEffect) effect).getDuration());
            }
        }
    }
}
