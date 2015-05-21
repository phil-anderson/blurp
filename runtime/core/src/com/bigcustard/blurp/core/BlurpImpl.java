package com.bigcustard.blurp.core;

import com.bigcustard.blurp.apimodel.*;
import com.bigcustard.blurp.core.commands.*;
import com.bigcustard.blurp.core.events.*;
import com.bigcustard.blurp.model.*;

/**
 * Allows the user to call methods that specific to the Blurp engine.
 */
public class BlurpImpl extends Blurp {

    public Blurp blurpify() {

        BlurpStore.blurpifier.blurpify();
        EventDispatcher.dispatchEvents();
        return this;
    }

    @Override
    public Image loadImage(String filename) {

        if(filename == null) throw new RuntimeException("Image file name can't be null");

        // See if we already have one
        Image image = BlurpStore.modelRepository.getImage(filename);
        if(image == null) {
            image = new ImageImpl(filename);
            BlurpStore.modelRepository.addImage(image);
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

        ImageSprite imageSprite = new ImageSpriteImpl(image, BlurpStore.screenCenterX(), BlurpStore.screenCenterY());
        BlurpStore.modelRepository.addImageSprite(imageSprite);
        return imageSprite;
    }

    @Override
    public TextSprite createTextSprite(String text) {

        if(text == null) text = "";

        TextSprite textSprite = new TextSpriteImpl(text, BlurpStore.screenCenterX(), BlurpStore.screenCenterY());
        BlurpStore.modelRepository.addTextSprite(textSprite);
        return textSprite;
    }

    @Override
    public Colour createColour(double red, double green, double blue) {

        return new Colour(red, green, blue);
    }

    @Override
    public Blurp debugMode(boolean enable, Colour debugColour) {

        BlurpStore.runtimeRepository.registerCommand(new SetDebugModeCommand(enable, debugColour));
        return this;
    }
}
