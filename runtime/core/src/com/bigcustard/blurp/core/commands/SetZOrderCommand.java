package com.bigcustard.blurp.core.commands;

import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.runtimemodel.*;

public class SetZOrderCommand implements Command {

    private final Sprite target;
    private final int zOrder;

    public SetZOrderCommand(Sprite target, int zOrder) {

        this.target = target;
        this.zOrder = zOrder;
    }

    public Sprite getTarget() {

        return target;
    }

    public int getzOrder() {

        return zOrder;
    }

    @Override
    public void execute(float deltaTime) {

        RuntimeSprite runtimeSprite = BlurpStore.runtimeRepository.getSprite(target);

        if(runtimeSprite != null) {
            runtimeSprite.setZIndex(zOrder);
        } else {
            // Probably got called on a sprite that hasn't been synced yet. Try deferring until after sync.
            BlurpStore.runtimeRepository.deferCommand(this);
        }
    }
}
