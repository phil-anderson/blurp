package com.bigcustard.blurp.core.commands;

import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.runtimemodel.*;

public class ZOrderExecutor {

    public void execute(SetZOrderCommand setZOrderCommand) {

        RuntimeSprite runtimeSprite = BlurpStore.runtimeRepository.getSprite(setZOrderCommand.getTarget());

        if(runtimeSprite != null) {
            runtimeSprite.setZIndex(setZOrderCommand.getzOrder());
        } else {
            // Probably got called on a sprite that hasn't been synced yet. Try deferring until after sync.
            BlurpStore.runtimeRepository.deferCommand(setZOrderCommand);
        }
    }

    public void execute(ChangeZOrderCommand changeZOrderCommand) {

        RuntimeSprite runtimeSprite = BlurpStore.runtimeRepository.getSprite(changeZOrderCommand.getTarget());
        RuntimeSprite otherRuntimeSprite = BlurpStore.runtimeRepository.getSprite(changeZOrderCommand.getOtherSprite());

        if(runtimeSprite != null && otherRuntimeSprite != null) {
            int delta = changeZOrderCommand.getDelta();
            if(runtimeSprite.getZIndex() < otherRuntimeSprite.getZIndex()) {
                // Hack to get around the remove/insert issue in libdgx's clunky setZIndex code
                delta--;
            }
            runtimeSprite.setZIndex(otherRuntimeSprite.getZIndex() + delta);

        } else {
            // Probably got called on a sprite that hasn't been synced yet. Try deferring until after sync.
            BlurpStore.runtimeRepository.deferCommand(changeZOrderCommand);
        }
    }
}
