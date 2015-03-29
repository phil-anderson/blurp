package com.bigcustard.blurp.core;

import java.util.*;
import com.bigcustard.blurp.model.*;

/**
 * This is an implementation of the model module's IModelRepository. It gets instantiated by the runtime, and then used
 * by the various model objects to register their presence.
 */
public class ModelRepository implements IModelRepository {

    private Canvas canvas;
    private List<Image> images;
    private List<ImageSprite> imageSprites;

    public ModelRepository(Canvas canvas) {

        this.canvas = canvas;
        images = new ArrayList<Image>();
        imageSprites = new ArrayList<ImageSprite>();
    }

    public void removeImage(Image image) {

        images.remove(image);
    }

    public void addImage(Image image) {

        images.add(image);
    }

    public List<Image> getImages() {

        return images;
    }

    public void addImageSprite(ImageSprite imageSprite) {

        imageSprites.add(imageSprite);
    }

    public List<ImageSprite> getImageSprites() {

        return imageSprites;
    }

    public void removeImageSprite(ImageSprite imageSprite) {

        imageSprites.remove(imageSprite);
    }

    public void requestBlurpify() {

        SF.getBlurpifier().blurpify();
    }


    public Canvas getCanvas() {

        return canvas;
    }


    public void dispose() {

        images.clear();
        imageSprites.clear();
    }
}
