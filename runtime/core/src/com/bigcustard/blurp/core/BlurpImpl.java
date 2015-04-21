package com.bigcustard.blurp.core;

import com.bigcustard.blurp.apimodel.*;
import com.bigcustard.blurp.core.commands.*;
import com.bigcustard.blurp.model.*;

/**
 * Allows the user to call methods that specific to the Blurp engine.
 */
public class BlurpImpl extends Blurp {

    private final RuntimeRepository runtimeRepository;
    private final ModelRepository modelRepository;
    private final Screen screen;
    private final Blurpifier blurpifier;

    BlurpImpl(RuntimeRepository runtimeRepository, ModelRepository modelRepository, Screen screen, Blurpifier blurpifier) {

        this.runtimeRepository = runtimeRepository;
        this.modelRepository = modelRepository;
        this.screen = screen;
        this.blurpifier = blurpifier;
    }

    public Blurp blurpify() {

        blurpifier.blurpify();
        return this;
    }

    @Override
    public Image image(String filename) {

        if(filename == null) throw new RuntimeException("Image file name can't be null");

        // See if we already have one
        Image image = modelRepository.getImage(filename);
        if(image == null) {
            image = new ImageImpl(filename, modelRepository);
            modelRepository.addImage(image);
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

        ImageSprite imageSprite = new ImageSpriteImpl(image, x, y, runtimeRepository, modelRepository);
        modelRepository.addImageSprite(imageSprite);
        return imageSprite;
    }

    @Override
    public TextSprite textSprite(String text) {

        return textSprite(text, screen.width / 2.0, screen.height / 2.0);
    }


    @Override
    public TextSprite textSprite(String text, double x, double y) {

        if(text == null) text = "";

        TextSprite textSprite = new TextSpriteImpl(text, x, y, runtimeRepository, modelRepository);
        modelRepository.addTextSprite(textSprite);
        return textSprite;
    }

    @Override
    public Colour colour(double red, double green, double blue) {

        return new Colour(red, green, blue);
    }

    @Override
    public Blurp setDebugMode(boolean enable, boolean includeHiddenSprites) {

        runtimeRepository.registerCommand(new SetDebugModeCommand(enable, includeHiddenSprites));
        return this;
    }
}
