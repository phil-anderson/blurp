package com.bigcustard.blurp.core.effects;

import aurelienribon.tweenengine.*;
import com.bigcustard.blurp.model.effects.*;

public class ParallelEffectGroup extends EffectGroupImpl {

    public ParallelEffectGroup(EffectBase... effects) {

        super(effects);
    }

    @Override
    public BaseTween getTween(Object target) {

        Timeline timeline = Timeline.createParallel();
        populateTween(timeline);
        pushEffectsToTimeline(timeline, target);

        return timeline;
    }

    @Override
    protected EffectGroup copy(EffectGroup effect) {

        ParallelEffectGroup copy = new ParallelEffectGroup(effects);
        copyBasePropertiesTo(copy);

        return copy;
    }
}
