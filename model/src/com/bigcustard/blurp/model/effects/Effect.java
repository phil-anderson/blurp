package com.bigcustard.blurp.model.effects;

import com.bigcustard.blurp.model.constants.*;

public interface Effect extends EffectBase<Effect> {

    public Effect duration(int durationInMilliseconds);

    public Effect style(EffectStyle style);
}
