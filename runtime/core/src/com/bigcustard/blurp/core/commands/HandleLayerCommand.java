package com.bigcustard.blurp.core.commands;

import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.runtimemodel.*;

public class HandleLayerCommand implements Command {

    private RuntimeSprite sprite;

    public HandleLayerCommand(RuntimeSprite sprite) {

        this.sprite = sprite;
    }

    @Override
    public void execute(float deltaTime) {

        BlurpStore.blurpScreen.handleSpriteLayer(sprite);
    }
}
