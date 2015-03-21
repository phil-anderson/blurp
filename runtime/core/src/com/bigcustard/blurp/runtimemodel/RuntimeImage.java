package com.bigcustard.blurp.runtimemodel;

import com.badlogic.gdx.graphics.*;
import com.bigcustard.blurp.model.*;

public class RuntimeImage implements RuntimeObject<Image> {

    private Texture texture;

    public RuntimeImage(Image modelImage) {

        texture = new Texture(modelImage.filename);
    }

    Texture getTexture() {

        return texture;
    }

    @Override
    public void sync(Image modelImage) { } // Images are immutable so never need syncing.

    @Override
    public void dispose() {

        texture.dispose();
    }
}
