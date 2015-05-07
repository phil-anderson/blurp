package com.bigcustard.blurp.core.effects;

import aurelienribon.tweenengine.*;
import com.bigcustard.blurp.model.effects.*;

public class ParallelCompoundEffect extends CompoundEffect {

    public ParallelCompoundEffect(Effect... effects) {

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
    protected Effect copy(Effect effect) {

        ParallelCompoundEffect copy = new ParallelCompoundEffect(effects);
        copyBasePropertiesTo(copy);

        return copy;
    }
}
