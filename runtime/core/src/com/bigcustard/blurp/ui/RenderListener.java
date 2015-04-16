package com.bigcustard.blurp.ui;

import com.badlogic.gdx.graphics.g2d.*;

public interface RenderListener {

    public static final RenderListener NULL_IMPLEMENTATION = new RenderListener() {
        @Override
        public void handlePreRenderEvent(float delta) { }

        @Override
        public void handlePostRenderEvent(Batch batch, float delta) { }
    };

    void handlePreRenderEvent(float delta);

    void handlePostRenderEvent(Batch batch, float delta);
}
