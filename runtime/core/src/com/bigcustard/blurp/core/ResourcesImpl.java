package com.bigcustard.blurp.core;

import com.bigcustard.blurp.apimodel.*;
import com.bigcustard.blurp.model.*;

public class ResourcesImpl extends Resources {

    @Override
    public ImageSprite createImageSprite(String imageFilename) {

        if(imageFilename == null) throw new RuntimeException("Image file name can't be null");

        ImageSprite imageSprite = new ImageSpriteImpl(imageFilename, BlurpStore.mainCamera.position.x, BlurpStore.mainCamera.position.y);
        BlurpStore.modelRepository.addImageSprite(imageSprite);
        return imageSprite;
    }

    @Override
    public AnimationSprite createAnimationSprite(String... imageFilenames) {

        if(imageFilenames == null) throw new RuntimeException("Image file names can't be null");

        AnimationSprite animationSprite = new AnimationSpriteImpl(BlurpStore.mainCamera.position.x, BlurpStore.mainCamera.position.y, imageFilenames);
        BlurpStore.modelRepository.addAnimationSprite(animationSprite);
        return animationSprite;
    }

    @Override
    public TextSprite createTextSprite(String text) {

        if(text == null) text = "";

        TextSprite textSprite = new TextSpriteImpl(text, BlurpStore.mainCamera.position.x, BlurpStore.mainCamera.position.y);
        BlurpStore.modelRepository.addTextSprite(textSprite);
        return textSprite;
    }

    @Override
    public Colour createColour(double red, double green, double blue) {

        return new Colour(red, green, blue);
    }

    @Override
    public String toString() {

        return String.format("Resources: numImageSprites=%d numTextSprites=%d",
                             BlurpStore.modelRepository.getImageSprites().size(),
                             BlurpStore.modelRepository.getTextSprites().size());
    }
}
