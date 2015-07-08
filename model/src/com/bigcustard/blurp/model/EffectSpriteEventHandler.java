package com.bigcustard.blurp.model;

import com.bigcustard.blurp.model.effects.*;
import com.bigcustard.blurp.model.events.*;

public class EffectSpriteEventHandler implements SpriteEventHandler {

    private final EffectBase effectToRun;

    public EffectSpriteEventHandler(EffectBase effectToRun) {

        this.effectToRun = effectToRun;
    }

    @Override
    public void handle(Sprite sprite) {

        sprite.runEffect(effectToRun);
    }
}
