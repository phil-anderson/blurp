package com.bigcustard.blurp.model.effects;

import com.bigcustard.blurp.model.*;

public class EffectSpriteEventHandler implements SpriteEventHandler {

    private Effect effectToRun;

    public EffectSpriteEventHandler(Effect effectToRun) {

        this.effectToRun = effectToRun;
    }

    @Override
    public void handle(Sprite sprite) {

        sprite.runEffect(effectToRun);
    }
}
