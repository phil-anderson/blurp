package com.bigcustard.blurp.apimodel;

import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.core.events.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.constants.*;

public class ScreenImpl extends Screen {

    private final EventDispatcher eventDispatcher;

    public ScreenImpl() {

        super(BlurpStore.modelViewport);
        this.eventDispatcher = new EventDispatcher();
    }

    public void reset(double viewportWidth, double viewportHeight) {

        viewport.setSize(viewportWidth, viewportHeight).setStretchToFit(false);
        backgroundColour = Colours.Black;
    }

    @Override
    public boolean update() {

        BlurpStore.blurpifier.blurpify();
        eventDispatcher.dispatchEvents();
        return true;
    }

    @Override
    public Screen setTitle(String title) {

        BlurpState.setWindowTitle(title);
        return this;
    }

    @Override
    public String toString() {

        return String.format("Screen: viewport=%s backgroundColour=%s", viewport, backgroundColour);
    }
}
