package com.bigcustard.blurp.core.commands;

import com.bigcustard.blurp.apimodel.*;
import com.bigcustard.blurp.model.effects.*;

public class RunEffectCommand implements CommandVisitable {

    private final Object target;
    private final EffectImpl effect;

    public RunEffectCommand(Object target, Effect effect) {

        this.target = target;
        this.effect = (EffectImpl) effect;
    }

    public Object getTarget() {

        return target;
    }

    public EffectImpl getEffect() {

        return effect;
    }

    @Override
    public void accept(CommandVisitor visitor, float deltaTime) {

        visitor.visit(this);
    }
}
