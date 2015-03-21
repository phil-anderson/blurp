package com.bigcustard.blurp.model;

import java.util.*;

/**
 * This class acts as the root of the Blurp model, and is what will be transformed, synced and rendered by the runtime.
 * The various model objects either add themselves to this as they are instantiated, or in the case of singleton objects
 * like Backdrop, are instantiated directly here.
 * <p>
 * Everything in here is package-private as I don't want the users accessing it. If it should be accessible by the user
 * then expose it via {@link BlurpMasterpiece} which their script / class will extend.
 */
class Repository {

    private static Repository instance;

    private Backdrop backdrop;
    private List<Image> images;
    private List<ImageSprite> imageSprites;

    public static Repository getInstance() {

        if(instance == null) {
            instance = new Repository();
        }
        return instance;
    }

    private Repository() {

        backdrop = new Backdrop();
        images = new ArrayList<Image>();
        imageSprites = new ArrayList<ImageSprite>();
    }

    void addImage(Image image) {

        images.add(image);
    }

    void addImageSprite(ImageSprite imageSprite) {

        imageSprites.add(imageSprite);
    }

    Backdrop getBackdrop() {

        return backdrop;
    }

    List<Image> getImages() {

        return images;
    }

    List<ImageSprite> getImageSprites() {

        return imageSprites;
    }
}
