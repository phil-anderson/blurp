package com.bigcustard.blurp.core.effects;

import aurelienribon.tweenengine.*;
import com.bigcustard.blurp.model.effects.*;

public class SequentialEffectGroup extends EffectGroupImpl {

    public SequentialEffectGroup(EffectBase... effects) {

        super(effects);
    }

    @Override
    public BaseTween getTween(Object target) {

        Timeline timeline = Timeline.createSequence();
        populateTween(timeline);
        pushEffectsToTimeline(timeline, target);

        return timeline;
    }

    @Override
    protected EffectGroup copy(EffectGroup effect) {

        SequentialEffectGroup copy = new SequentialEffectGroup(effects);
        copyBasePropertiesTo(copy);
        return copy;
    }
}
