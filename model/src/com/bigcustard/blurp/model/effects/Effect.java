package com.bigcustard.blurp.model.effects;

import com.bigcustard.blurp.model.constants.*;

public interface Effect extends EffectBase<Effect> {

    public Effect withDuration(int durationInMilliseconds);

    public Effect withStyle(EffectStyle style);
}
