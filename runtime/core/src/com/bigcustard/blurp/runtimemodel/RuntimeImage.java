package com.bigcustard.blurp.runtimemodel;

import java.io.*;
import com.badlogic.gdx.files.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.util.*;

public class RuntimeImage implements RuntimeObject<Image> {

    private TextureRegion textureRegion;

    TextureRegion getTextureRegion() {

        return textureRegion;
    }

    @Override
    public void sync(Image modelImage, boolean newInstance) {


        String filename = BlurpStore.configuration.getContentRoot() + modelImage.name;
        // Images are immutable so only need syncing when new.
        if(newInstance) {
            try {
                FileHandle file = Files.getFile(filename);
                textureRegion = new TextureRegion(new Texture(file, true));
            } catch(FileNotFoundException e) {
                textureRegion.getTexture().setFilter(Texture.TextureFilter.MipMapLinearNearest, Texture.TextureFilter.Linear);
            }
        }
    }

    @Override
    public void dispose() {

        textureRegion.getTexture().dispose();
    }
}
