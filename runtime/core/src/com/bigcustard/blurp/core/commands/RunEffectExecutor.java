package com.bigcustard.blurp.core.commands;

import aurelienribon.tweenengine.*;
import com.bigcustard.blurp.apimodel.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.model.events.*;

public class RunEffectExecutor {

    public void execute(RunEffectCommand command) {

        if(command.getExistingEffectStrategy() == ExistingEffectStrategy.StopExisting) {
            BlurpStore.tweener.killTarget(command.getTarget());
        }

        if(command.getEffect() != null) {
            BaseTween tween = command.getEffect().getTween(command.getTarget());
            tween.setCallback(new FlagComplete(command.getTarget(), command.getCompletionHandler()));
            tween.start(BlurpStore.tweener);
        }
    }

    private static class FlagComplete implements TweenCallback {

        private final EffectContainer target;
        private final SimpleEventHandler onCompletion;

        private FlagComplete(Object target, SimpleEventHandler onCompletion) {

            this.target = (EffectContainer) target;
            this.onCompletion = onCompletion;
        }

        @Override
        public void onEvent(int type, BaseTween<?> source) {

            if(!otherTweensRunning(source)) {
                target.setRunningEffect(false);
                if(onCompletion != null) {
                    onCompletion.handle(target);
                }
            }
        }

        private boolean otherTweensRunning(BaseTween tweenToCheck) {

            // More bulletproof than a reference-counting-style approach
            for(BaseTween tween : BlurpStore.tweener.getObjects()) {
                if(tween != tweenToCheck && !tween.isFinished() && TweenHack.containsTarget(tween, target)) return true;
            }
            return false;
        }
    }
}
