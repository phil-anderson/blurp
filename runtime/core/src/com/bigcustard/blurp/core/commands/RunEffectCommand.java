package com.bigcustard.blurp.core.commands;

import com.bigcustard.blurp.apimodel.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.effects.*;

public class RunEffectCommand implements CommandVisitable {

    private final Sprite sprite;
    private final EffectImpl effect;

    public RunEffectCommand(Sprite sprite, Effect effect) {

        this.sprite = sprite;
        this.effect = (EffectImpl) effect;
    }

    public Sprite getSprite() {

        return sprite;
    }

    public EffectImpl getEffect() {

        return effect;
    }

    @Override
    public void accept(CommandVisitor visitor, float deltaTime) {

        visitor.visit(this);
    }
}
