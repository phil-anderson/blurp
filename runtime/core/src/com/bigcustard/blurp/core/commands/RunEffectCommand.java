package com.bigcustard.blurp.core.commands;

import com.bigcustard.blurp.apimodel.*;
import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.model.effects.*;
import com.bigcustard.blurp.model.events.*;

public class RunEffectCommand<T> implements CommandVisitable {

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

    public Object getTarget() {

        return target;
    }

    public EffectImpl getEffect() {

        return effect;
    }

    public SimpleEventHandler<T> getCompletionHandler() {

        return completionHandler;
    }

    public ExistingEffectStrategy getExistingEffectStrategy() {

        return existingEffectStrategy;
    }

    public boolean isRemoveOnComplete() {

        return removeOnComplete;
    }

    @Override
    public void accept(CommandVisitor visitor, float deltaTime) {

        visitor.visit(this);
    }
}
