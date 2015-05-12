package com.bigcustard.blurp.core.commands;

import com.bigcustard.blurp.apimodel.*;
import com.bigcustard.blurp.model.effects.*;

public class RunEffectCommand implements CommandVisitable {

    private final Object target;
    private final EffectImpl effect;
    private final boolean removeOnComplete;

    public RunEffectCommand(Object target, EffectBase effect, boolean removeOnComplete) {

        this.target = target;
        this.effect = (EffectImpl) effect;
        this.removeOnComplete = removeOnComplete;
    }

    public Object getTarget() {

        return target;
    }

    public EffectImpl getEffect() {

        return effect;
    }

    public boolean isRemoveOnComplete() {

        return removeOnComplete;
    }

    @Override
    public void accept(CommandVisitor visitor, float deltaTime) {

        visitor.visit(this);
    }
}
