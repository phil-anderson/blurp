package com.bigcustard.blurp.runtimemodel;

import java.io.*;
import com.badlogic.gdx.files.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.util.*;

public class RuntimeImage {

    private final String name;
    private final TextureRegion textureRegion;

    private int referenceCount = 0;

    TextureRegion getTextureRegion() {

        return textureRegion;
    }

    public RuntimeImage(String name) {

        this.name = name;
        String filename = BlurpStore.configuration.getContentRoot() + name;
        try {
            FileHandle file = Files.getFile(filename);
            Pixmap pixmap = new Pixmap(file);
            boolean useMipMaps = pixmap.getWidth() >= 4 && pixmap.getWidth() <= 1024 && pixmap.getHeight() >= 4 && pixmap.getHeight() <= 1024;
            Texture texture = new Texture(pixmap, useMipMaps);
            if(useMipMaps) {
                texture.setFilter(Texture.TextureFilter.MipMapLinearNearest, Texture.TextureFilter.Linear);
            } else {
                texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            }
            textureRegion = new TextureRegion(texture);
        } catch(FileNotFoundException e) {
            throw new BlurpException("Couldn't load image with name: " + name);
        }
    }

    public void registerUsage() {

        referenceCount++;
    }

    public void deregisterUsage() {

        referenceCount--;
    }

    public boolean isInUse() {

        return referenceCount > 0;
    }

    public void dispose() {

        if(textureRegion != null) {
            textureRegion.getTexture().dispose();
        }
    }

    public String getName() {

        return name;
    }
}
