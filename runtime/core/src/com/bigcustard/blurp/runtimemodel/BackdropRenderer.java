package com.bigcustard.blurp.runtimemodel;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.*;

public class BackdropRenderer {

    // TODO pre and post render hooks in renderer
    // TODO Script runner
    // TODO Think about ways to hide the Blurpifier from the users (or email Spence)

    public void render() {

        Backdrop backdrop = RuntimeRepository.getInstance().getBackdrop();

        Gdx.gl.glClearColor((float) backdrop.baseColour.red,
                            (float) backdrop.baseColour.green,
                            (float) backdrop.baseColour.blue, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}
