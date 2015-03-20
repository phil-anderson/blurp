package com.bigcustard.blurp.model;

import java.util.*;

class Repository {

    private static Repository instance;

    private List<Image> images = new ArrayList<Image>();
    private List<ImageSprite> imageSprites = new ArrayList<ImageSprite>();

    private Repository() {}

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

    List<Image> getImages() {

        return images;
    }

    List<ImageSprite> getImageSprites() {

        return imageSprites;
    }
}
