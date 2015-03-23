package com.bigcustard.blurp.ui;

import com.badlogic.gdx.graphics.g2d.*;

public class RenderListenerAdapter implements RenderListener {

    @Override
    public void handleRenderEvent(Batch batch, float delta, EventType type) {

        switch(type) {
            case PreFrame : onPreFrame(batch, delta); break;
            case PreRender : onPreRender(batch, delta); break;
            case PostRender : onPostRender(batch, delta); break;
            case PostFrame : onPostFrame(batch, delta); break;
        }
    }

    protected void onPreFrame(Batch batch, float delta) { }

    private void onPreRender(Batch batch, float delta) { }

    private void onPostRender(Batch batch, float delta) { }

    private void onPostFrame(Batch batch, float delta) { }
}
