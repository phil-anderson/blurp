package com.bigcustard.blurp.core;

import java.util.*;
import com.bigcustard.blurp.model.*;

/**
 * Stores and manages api model objects that require a different runtime implementation.
 */
public class ModelRepository {

    private List<Image> images;
    private List<ImageSprite> imageSprites;
    private List<TextSprite> textSprites;

    public ModelRepository() {

        images = new ArrayList<Image>();
        imageSprites = new ArrayList<ImageSprite>();
        textSprites = new ArrayList<TextSprite>();
    }

    public void addImage(Image image) {

        images.add(image);
    }

    public void removeImage(Image image) {

        images.remove(image);
    }

    public void addImageSprite(ImageSprite imageSprite) {

        imageSprites.add(imageSprite);
    }

    public void removeImageSprite(ImageSprite imageSprite) {

        imageSprites.remove(imageSprite);
    }

    public void addTextSprite(TextSprite textSprite) {

        textSprites.add(textSprite);
    }

    public void removeTextSprite(TextSprite textSprite) {

        textSprites.remove(textSprite);
    }

    List<Image> getImages() {

        return images;
    }

    List<ImageSprite> getImageSprites() {

        return imageSprites;
    }

    public List<TextSprite> getTextSprites() {

        return textSprites;
    }

    public Image getImage(String imageFilename) {

        for(Image image : images) {
            if(image.filename.equals(imageFilename)) return image;
        }
        return null;
    }

    public void dispose() {

        images.clear();
        imageSprites.clear();
    }
}
