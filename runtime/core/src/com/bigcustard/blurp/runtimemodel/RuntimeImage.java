package com.bigcustard.blurp.runtimemodel;

import com.badlogic.gdx.*;
import com.badlogic.gdx.files.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.*;

public class RuntimeImage implements RuntimeObject<Image> {

    private TextureRegion textureRegion;

    TextureRegion getTextureRegion() {

        return textureRegion;
    }

    @Override
    public void sync(Image modelImage, BlurpObjectProvider blurpObjectProvider, boolean newInstance) {

        // Images are immutable so only need syncing when new.
        if(newInstance) {
            FileHandle internalFile = Gdx.files.internal(modelImage.filename);
            if(internalFile.exists()) {
                textureRegion = new TextureRegion(new Texture(internalFile));
            } else {
                FileHandle externalFile = Gdx.files.classpath(modelImage.filename);
                if(externalFile.exists()) {
                    textureRegion = new TextureRegion(new Texture(externalFile));
                } else {
                    System.err.println("Couldn't find image " + modelImage.filename);

                    // TODO: Have a pre-canned "not-found" texture.
                    textureRegion = new TextureRegion(new Texture(10, 10, Pixmap.Format.RGBA8888));
                }
            }
            textureRegion.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
    }

    @Override
    public void dispose() {

        textureRegion.getTexture().dispose();
    }
}
