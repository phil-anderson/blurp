package com.bigcustard.blurp.core.effects;

import aurelienribon.tweenengine.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.effects.*;

public class ParallelCompoundEffect extends CompoundEffect {

    public ParallelCompoundEffect(BaseEffect... effects) {

        super(effects);
    }

    @Override
    public BaseTween getTween(Sprite sprite) {

        Timeline timeline = Timeline.createParallel();
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
