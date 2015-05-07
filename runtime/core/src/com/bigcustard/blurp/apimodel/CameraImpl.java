package com.bigcustard.blurp.apimodel;

import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.core.commands.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.model.effects.*;

public class CameraImpl extends Camera implements EffectContainer {

    private boolean runningEffect;

    public CameraImpl(double x, double y) {

        this.x = x;
        this.y = y;
        this.zoom = 1;
        this.rotation = 0;
        this.colour = Colours.WHITE;
        this.alpha = 1;
    }

    @Override
    public Camera runEffect(Effect effectToRun) {

        BlurpStore.runtimeRepository.registerCommand(new RunEffectCommand(this, effectToRun));
        runningEffect = effectToRun != null;
        return this;
    }

    @Override
    public void setRunningEffect(boolean running) {
         this.runningEffect = running;
    }

    @Override
    public boolean isRunningEffect() {

        return runningEffect;
    }
}
