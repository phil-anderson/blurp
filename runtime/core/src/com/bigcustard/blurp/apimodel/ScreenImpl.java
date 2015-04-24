package com.bigcustard.blurp.apimodel;

import com.bigcustard.blurp.model.*;

public class ScreenImpl extends Screen {

    public ScreenImpl(double width, double height) {

        super(width, height);
        this.backgroundColour =  Colours.RUNTIME_INSTANCE.black;
    }
}
