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
            tween.setCallback(new FlagComplete(command.getTarget(), command.isRemoveOnComplete()));
            tween.setCallbackTriggers(TweenCallback.COMPLETE);
            tween.start(BlurpStore.tweener);
        }
    }

    private static class FlagComplete implements TweenCallback {

        private final EffectContainer target;
        private final boolean removeOnComplete;

        private FlagComplete(Object target, boolean removeOnComplete) {

            this.target = (EffectContainer) target;
            this.removeOnComplete = removeOnComplete;
        }

        @Override
        public void onEvent(int type, BaseTween<?> source) {

            target.setRunningEffect(false);
            if(removeOnComplete) {
                target.remove();
            }
        }
    }
}
