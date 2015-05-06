package com.bigcustard.blurp.runtimemodel;

import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.*;

public class RuntimeScreen {

    private Colour backgroundColour;

    public RuntimeScreen() {

        sync();
    }

    public void sync() {

        this.backgroundColour = BlurpStore.modelScreen.backgroundColour;
    }

    public Colour getBackgroundColour() {

        return backgroundColour;
    }
}
