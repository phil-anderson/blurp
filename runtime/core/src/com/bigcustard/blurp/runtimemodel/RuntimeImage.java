package com.bigcustard.blurp.runtimemodel;

import com.badlogic.gdx.graphics.*;
import com.bigcustard.blurp.model.*;

public class RuntimeImage implements RuntimeObject<Image> {

    private String filename;
    private Texture texture;

    public RuntimeImage(Image modelImage) {

        loadImage(modelImage.filename);
    }

    @Override
    public void sync(Image modelImage) {

        if(!modelImage.filename.equals(filename)) {
            texture.dispose();
            loadImage(modelImage.filename);
        }
    }

    Texture getTexture() {

        return texture;
    }

    private void loadImage(String imageFilename) {

        this.filename = imageFilename;
        texture = new Texture(imageFilename);
    }
}
