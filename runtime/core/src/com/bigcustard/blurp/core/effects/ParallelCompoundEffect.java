package com.bigcustard.blurp.core.effects;

import aurelienribon.tweenengine.Timeline;
import com.bigcustard.blurp.model.effects.BaseEffect;

public class ParallelCompoundEffect extends CompoundEffect implements TimelineFactory {

    public ParallelCompoundEffect(BaseEffect... effects) {

        super(effects);
    }

    @Override
    public Timeline createTimeline(Object sprite) {

        Timeline timeline = Timeline.createParallel();
        pushEffectsToTimeline(timeline, sprite);
        populateTween(timeline);

        return timeline;
    }
}
