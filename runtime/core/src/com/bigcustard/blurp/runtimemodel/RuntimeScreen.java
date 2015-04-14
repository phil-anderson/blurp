package com.bigcustard.blurp.runtimemodel;

import com.bigcustard.blurp.model.*;

public class RuntimeScreen {

    private Screen modelScreen;
    private Colour backgroundColour;

    public RuntimeScreen(Screen modelScreen) {

        this.modelScreen = modelScreen;
        sync();
    }

    public void sync() {

        this.backgroundColour = modelScreen.backgroundColour;
    }

    public Colour getBackgroundColour() {

        return backgroundColour;
    }
}
