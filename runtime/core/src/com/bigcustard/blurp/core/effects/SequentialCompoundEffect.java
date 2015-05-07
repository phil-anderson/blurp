package com.bigcustard.blurp.core.effects;

import aurelienribon.tweenengine.*;
import com.bigcustard.blurp.model.effects.*;

public class SequentialCompoundEffect extends CompoundEffect {

    public SequentialCompoundEffect(Effect... effects) {

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
    protected Effect copy(Effect effect) {

        SequentialCompoundEffect copy = new SequentialCompoundEffect(effects);
        copyBasePropertiesTo(copy);
        return copy;
    }
}
