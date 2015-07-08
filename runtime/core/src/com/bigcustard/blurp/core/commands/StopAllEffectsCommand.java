package com.bigcustard.blurp.core.commands;

import com.bigcustard.blurp.apimodel.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.*;

public class StopAllEffectsCommand implements Command {

    @Override
    public void execute(float deltaTime) {

        BlurpStore.tweener.killAll();
        for(Sprite sprite : BlurpStore.modelRepository.getSprites()) {
            ((EffectContainer) sprite).setRunningEffect(false);
        }
    }
}
