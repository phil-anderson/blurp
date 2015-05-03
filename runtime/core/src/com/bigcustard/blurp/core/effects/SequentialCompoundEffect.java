package com.bigcustard.blurp.core.effects;

import aurelienribon.tweenengine.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.effects.*;

public class SequentialCompoundEffect extends CompoundEffect {

    public SequentialCompoundEffect(BaseEffect... effects) {

        super(effects);
    }


    @Override
    public BaseTween getTween(Sprite sprite) {

        Timeline timeline = Timeline.createSequence();
        pushEffectsToTimeline(timeline, sprite);
        populateTween(timeline);

        return timeline;
    }

    @Override
    protected Effect copy(Effect effect) {

        ParallelCompoundEffect copy = new ParallelCompoundEffect(effects);
        copyBasePropertiesTo(copy);
        return copy;
    }
}
