package com.bigcustard.blurp.core.commands;

import aurelienribon.tweenengine.*;
import com.bigcustard.blurp.apimodel.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.constants.*;

public class RunEffectExecutor {

    public void execute(RunEffectCommand command) {

        if(command.getExistingEffectStrategy() == ExistingEffectStrategy.StopExisting) {
            BlurpStore.tweener.killTarget(command.getTarget());
        }

        if(command.getEffect() != null) {
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

            if(!otherTweensRunning(source)) {
                target.setRunningEffect(false);
                if(removeOnComplete) {
                    target.remove();
                }
            }
        }

        private boolean otherTweensRunning(BaseTween tweenToCheck) {

            // More bulletproof than a reference-counting-style approach
            for(BaseTween tween : BlurpStore.tweener.getObjects()) {
                if(tween != tweenToCheck && !tween.isFinished()) return true;
            }
            return false;
        }
    }
}
