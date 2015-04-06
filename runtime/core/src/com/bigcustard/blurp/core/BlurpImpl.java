package com.bigcustard.blurp.core;

import com.bigcustard.blurp.apimodel.*;
import com.bigcustard.blurp.model.*;

/**
 * Allows the user to call methods that specific to the Blurp engine.
 */
public class BlurpImpl extends Blurp {

    private final ApiModelRepository apiModelRepository;
    private final Screen screen;
    private final Blurpifier blurpifier;

    BlurpImpl(ApiModelRepository apiModelRepository, Screen screen, Blurpifier blurpifier) {

        this.apiModelRepository = apiModelRepository;
        this.screen = screen;
        this.blurpifier = blurpifier;
    }

    public void blurpify() {

        blurpifier.blurpify();
    }

    @Override
    public Image image(String filename) {

        if(filename == null) throw new RuntimeException("Image file name can't be null");

        // See if we already have one
        Image image = apiModelRepository.getImage(filename);
        if(image == null) {
            image = new ImageImpl(filename, apiModelRepository);
            apiModelRepository.addImage(image);
        }
        return image;
    }

    @Override
    public ImageSprite imageSprite(String imageFilename) {

        if(imageFilename == null) throw new RuntimeException("Image file name can't be null");

        return imageSprite(image(imageFilename));
    }

    @Override
    public ImageSprite imageSprite(String imageFilename, double x, double y) {

        if(imageFilename == null) throw new RuntimeException("Image file name can't be null");

        return imageSprite(image(imageFilename), x, y);
    }

    @Override
    public ImageSprite imageSprite(Image image) {

        return imageSprite(image, screen.width / 2.0, screen.height / 2.0);
    }

    @Override
    public ImageSprite imageSprite(Image image, double x, double y) {

        if(image == null) throw new RuntimeException("Image can't be null");

        ImageSprite imageSprite = new ImageSpriteImpl(image, x, y, apiModelRepository);
        apiModelRepository.addImageSprite(imageSprite);
        return imageSprite;
    }

    @Override
    public Colour colour(double red, double green, double blue) {

        return new Colour(red, green, blue);
    }
}
