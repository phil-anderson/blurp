package com.bigcustard.blurp.model.testutils;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.effects.*;

public class SpriteForTests extends Sprite {

    @Override
    public Object moveTowards(double targetX, double targetY, double speed) {

        return null;
    }

    @Override
    public boolean collidedWith(Sprite other) {

        return false;
    }

    @Override
    public Object runEffect(Effect effectToRun) {

        return null;
    }

    @Override
    public boolean isRunningEffect() {

        return false;
    }

    @Override
    public Object stopEffect() {

        return null;
    }
}
