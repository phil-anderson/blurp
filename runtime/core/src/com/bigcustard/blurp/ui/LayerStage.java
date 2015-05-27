package com.bigcustard.blurp.ui;

import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.utils.viewport.*;

public class LayerStage extends Stage {

    // Flywweight
    private Vector2 coords = new Vector2();

    private boolean mouseHandled;

    public LayerStage(Viewport viewport, Batch batch) {

        super(viewport, batch);
    }

    @Override // Stop any mouse movement that is over a sprite propagating to other layers.
    public boolean mouseMoved(int screenX, int screenY) {

        super.mouseMoved(screenX, screenY);

        screenToStageCoordinates(coords.set(screenX, screenY));
        mouseHandled = hit(coords.x, coords.y, true) != null;
        return mouseHandled;
    }

    public boolean mouseHandled() {

        return mouseHandled;
    }
}
