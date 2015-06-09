package com.bigcustard.blurp.apimodel;

import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.core.commands.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.model.effects.*;
import com.bigcustard.blurp.model.events.*;

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
    public Camera runEffect(EffectBase effectToRun, CameraEventHandler completionHandler, ExistingEffectStrategy existingEffectStrategy) {

        BlurpStore.runtimeRepository.registerCommand(new RunEffectCommand<Camera>(this, effectToRun, completionHandler, existingEffectStrategy, false));
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

    @Override
    public void remove() {

        throw new UnsupportedOperationException("You can't remove the Camera");
    }

    public void reset() {

        position(BlurpStore.configuration.getInitialViewportWidth() / 2, (float) BlurpStore.configuration.getInitialViewportHeight() / 2);
        this.zoom = 1;
        this.rotation = 0;
        this.colour = Colours.WHITE;
        this.alpha = 1;
        this.runningEffect = false;
    }
}
