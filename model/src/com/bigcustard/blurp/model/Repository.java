package com.bigcustard.blurp.model;

import java.util.*;

class Repository {

    private static Repository instance;

    private Backdrop backdrop;
    private List<Image> images;
    private List<ImageSprite> imageSprites;

    private Repository() {

        backdrop = new Backdrop();
        images = new ArrayList<Image>();
        imageSprites = new ArrayList<ImageSprite>();
    }

    static synchronized Repository getInstance() {

        if(instance == null) {
            instance = new Repository();
        }
        return instance;
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
