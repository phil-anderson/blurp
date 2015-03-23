package com.bigcustard.blurp.ui;

import com.badlogic.gdx.graphics.g2d.*;

public interface RenderListener {

    enum EventType { PreFrame, PreRender, PostRender, PostFrame }

    void handleRenderEvent(Batch batch, float delta, EventType type);
}
