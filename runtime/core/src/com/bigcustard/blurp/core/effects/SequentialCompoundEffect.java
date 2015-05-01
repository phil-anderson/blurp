package com.bigcustard.blurp.core.effects;

import aurelienribon.tweenengine.Timeline;
import com.bigcustard.blurp.model.effects.BaseEffect;

public class SequentialCompoundEffect extends CompoundEffect implements TimelineFactory {

    public SequentialCompoundEffect(BaseEffect... effects) {

        super(effects);
    }

    @Override
    public Timeline createTimeline(Object sprite) {

        Timeline timeline = Timeline.createSequence();
        pushEffectsToTimeline(timeline, sprite);
        timeline.end();

        return timeline;
    }
}
