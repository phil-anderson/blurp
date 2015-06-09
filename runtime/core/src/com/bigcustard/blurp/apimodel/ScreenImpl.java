package com.bigcustard.blurp.apimodel;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.constants.*;

public class ScreenImpl extends Screen {

    public ScreenImpl(double viewportWidth, double viewportHeight) {

        this.viewportWidth = viewportWidth;
        this.viewportHeight = viewportHeight;
    }

    public void reset(double viewportWidth, double viewportHeight) {

        viewport(viewportWidth, viewportHeight, false);
        backgroundColour = Colours.BLACK;
    }
}
