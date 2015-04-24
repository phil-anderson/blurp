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
    public Image loadImage(String filename) {

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
    public ImageSprite createImageSprite(String imageFilename) {

        if(imageFilename == null) throw new RuntimeException("Image file name can't be null");

        return createImageSprite(loadImage(imageFilename));
    }

    public ImageSprite createImageSprite(Image image) {

        if(image == null) throw new RuntimeException("Image can't be null");

        ImageSprite imageSprite = new ImageSpriteImpl(image, screen.width / 2.0, screen.height / 2.0, runtimeRepository, modelRepository);
        modelRepository.addImageSprite(imageSprite);
        return imageSprite;
    }

    @Override
    public TextSprite createTextSprite(String text) {

        if(text == null) text = "";

        TextSprite textSprite = new TextSpriteImpl(text, screen.width / 2.0, screen.height / 2.0, runtimeRepository, modelRepository);
        modelRepository.addTextSprite(textSprite);
        return textSprite;
    }

    @Override
    public Colour createColour(double red, double green, double blue) {

        return new Colour(red, green, blue);
    }

    @Override
    public Blurp setDebugMode(boolean enable, Colour debugColour) {

        runtimeRepository.registerCommand(new SetDebugModeCommand(enable, debugColour));
        return this;
    }
}
