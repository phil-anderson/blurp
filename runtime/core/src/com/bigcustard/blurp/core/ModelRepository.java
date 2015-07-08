package com.bigcustard.blurp.core;

import java.util.*;
import com.bigcustard.blurp.model.*;

/**
 * Stores and manages api model objects that require a different runtime implementation.
 */
public class ModelRepository {

    private List<ImageSprite> imageSprites;
    private List<AnimationSprite> animationSprites;
    private List<TextSprite> textSprites;
    private List<Sprite> createdSprites;

    public ModelRepository() {

        imageSprites = new ArrayList<ImageSprite>();
        animationSprites = new ArrayList<AnimationSprite>();
        textSprites = new ArrayList<TextSprite>();
        createdSprites = new ArrayList<Sprite>();
    }

    public void addImageSprite(ImageSprite imageSprite) {

        imageSprites.add(imageSprite);
        createdSprites.add(imageSprite);
    }

    public void removeImageSprite(ImageSprite imageSprite) {

        imageSprites.remove(imageSprite);
    }

    public void addAnimationSprite(AnimationSprite animationSprite) {

        animationSprites.add(animationSprite);
        createdSprites.add(animationSprite);
    }

    public void removeAnimationSprite(AnimationSprite animationSprite) {

        animationSprites.remove(animationSprite);
    }

    public void addTextSprite(TextSprite textSprite) {

        textSprites.add(textSprite);
        createdSprites.add(textSprite);
    }

    public void removeTextSprite(TextSprite textSprite) {

        textSprites.remove(textSprite);
    }

    List<ImageSprite> getImageSprites() {

        return imageSprites;
    }

    public List<AnimationSprite> getAnimationSprites() {

        return animationSprites;
    }

    public List<TextSprite> getTextSprites() {

        return textSprites;
    }

    public List<Sprite> getSprites() {

        ArrayList<Sprite> sprites = new ArrayList<Sprite>();
        sprites.addAll(imageSprites);
        sprites.addAll(animationSprites);
        sprites.addAll(textSprites);
        return sprites;
    }

    public void dispose() {

        imageSprites.clear();
        textSprites.clear();
        createdSprites.clear();
    }

    public List<Sprite> pullCreatedSprites() {

        ArrayList<Sprite> result = new ArrayList<Sprite>(createdSprites);
        createdSprites.clear();
        return result;
    }
}
