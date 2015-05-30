package com.bigcustard.blurp.core.commands;

import aurelienribon.tweenengine.*;
import com.bigcustard.blurp.apimodel.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.model.effects.*;
import com.bigcustard.blurp.model.events.*;

public class RunEffectCommand<T> implements Command {

    private final Object target;
    private final EffectImpl effect;
    private final SimpleEventHandler<T> completionHandler;
    private final ExistingEffectStrategy existingEffectStrategy;
    private final boolean removeOnComplete;

    public RunEffectCommand(Object target, EffectBase effect, SimpleEventHandler<T> completionHandler, ExistingEffectStrategy existingEffectStrategy, boolean removeOnComplete) {

        this.target = target;
        this.completionHandler = completionHandler;
        this.existingEffectStrategy = existingEffectStrategy;
        this.effect = (EffectImpl) effect;
        this.removeOnComplete = removeOnComplete;
    }

    @Override
    public void execute(float deltaTime) {

        if(existingEffectStrategy == ExistingEffectStrategy.StopExisting) {
            BlurpStore.tweener.killTarget(target);
        }

        if(effect != null) {
            BaseTween tween = effect.getTween(target);
            tween.setCallback(new FlagComplete(target, completionHandler));
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
