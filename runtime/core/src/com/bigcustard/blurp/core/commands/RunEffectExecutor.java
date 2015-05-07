package com.bigcustard.blurp.core.commands;

import aurelienribon.tweenengine.*;
import com.bigcustard.blurp.apimodel.*;
import com.bigcustard.blurp.core.*;

public class RunEffectExecutor {

    public void execute(RunEffectCommand command) {

        BlurpStore.tweener.killTarget(command.getSprite());

        boolean runEffect = command.getEffect() != null;
        if(runEffect) {
            BaseTween tween = command.getEffect().getTween(command.getSprite());
            tween.start(BlurpStore.tweener);
        }

        ((EffectContainer) command.getSprite()).setRunningEffect(runEffect);
    }
}
