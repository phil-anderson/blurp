package com.bigcustard.blurp.core.commands;

import com.bigcustard.blurp.runtimemodel.*;

public class HandleLayerCommand implements CommandVisitable {

    private RuntimeSprite sprite;

    public HandleLayerCommand(RuntimeSprite sprite) {

        this.sprite = sprite;
    }

    public RuntimeSprite getSprite() {

        return sprite;
    }

    @Override
    public void accept(CommandVisitor visitor, float deltaTime) {

        visitor.visit(this);
    }
}
