package com.bigcustard.blurp.core.commands;

import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.runtimemodel.*;

public class ChangeZOrderCommand implements Command {

    private final Sprite target;
    private final Sprite otherSprite;
    private int delta;

    public ChangeZOrderCommand(Sprite target, Sprite otherSprite, int delta) {

        this.target = target;

        this.otherSprite = otherSprite;
        this.delta = delta;
    }

    @Override
    public void execute(float deltaTime) {

        RuntimeSprite runtimeSprite = BlurpStore.runtimeRepository.getSprite(target);
        RuntimeSprite otherRuntimeSprite = BlurpStore.runtimeRepository.getSprite(otherSprite);

        if(runtimeSprite != null && otherRuntimeSprite != null) {
            if(runtimeSprite.getZIndex() < otherRuntimeSprite.getZIndex()) {
                // Hack to get around the remove/insert issue in libdgx's clunky setZIndex code
                delta--;
            }
            runtimeSprite.setZIndex(otherRuntimeSprite.getZIndex() + delta);

        } else {
            // Probably got called on a sprite that hasn't been synced yet. Try deferring until after sync.
            BlurpStore.runtimeRepository.deferCommand(this);
        }

    }
}
