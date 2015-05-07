package com.bigcustard.blurp.core.commands;

import aurelienribon.tweenengine.*;
import com.bigcustard.blurp.apimodel.*;
import com.bigcustard.blurp.core.*;

public class RunEffectExecutor {

    public void execute(RunEffectCommand command) {

        BlurpStore.tweener.killTarget(command.getTarget());

        boolean runEffect = command.getEffect() != null;
        if(runEffect) {
            BaseTween tween = command.getEffect().getTween(command.getTarget());
            tween.setCallback(new FlagComplete(command.getTarget()));
            tween.setCallbackTriggers(TweenCallback.COMPLETE);
            tween.start(BlurpStore.tweener);
        }
    }

    private static class FlagComplete implements TweenCallback {

        private EffectContainer target;

        private FlagComplete(Object target) {

            this.target = (EffectContainer) target;
        }

        @Override
        public void onEvent(int type, BaseTween<?> source) {

            target.setRunningEffect(false);
        }
    }
}
