package com.bigcustard.blurp.model;

import java.util.*;
import com.bigcustard.blurp.core.*;

// TODO: This is a one-time-mutable singleton. I'm not wild about it, but it's better than anything else I could think of.
/**
 * This class acts as the root of the Blurp model, and is what will be transformed, synced and rendered by the runtime.
 * The various model objects either add themselves to this as they are instantiated, or in the case of singleton objects
 * like Screen, are instantiated directly here.
 * <p>
 * Everything in here is package-private as I don't want the users accessing it. If it should be accessible by the user
 * then expose it via {@link BlurpMain} which their script / class will extend.
 * <p>
 * I'm considering moving this to the runtime module and just having an interface in here, but still thinking it
 * through. Perhaps there'll be a tipping point that'll make me decide to do it.
 */
class Repository {

    private static Repository instance;

    private Screen screen;
    private List<Image> images;
    private List<ImageSprite> imageSprites;

    private IBlurpifier blurpifier;

    private boolean initialised;

    public static Repository getInstance() {

        if(instance == null) {
            instance = new Repository();
        }
        return instance;
    }

    private Repository() {

        images = new ArrayList<Image>();
        imageSprites = new ArrayList<ImageSprite>();
    }

    void addImage(Image image) {

        images.add(image);
    }

    void removeImage(Image image) {

        images.remove(image);
    }

    void addImageSprite(ImageSprite imageSprite) {

        imageSprites.add(imageSprite);
    }

    void removeImageSprite(ImageSprite imageSprite) {

        imageSprites.remove(imageSprite);
    }

    synchronized void requestBlurpify() {

        blurpifier.blurpify();
    }

    Screen getScreen() {

        return screen;
    }

    List<Image> getImages() {

        return images;
    }

    List<ImageSprite> getImageSprites() {

        return imageSprites;
    }


    // TODO: THIS MUST DIE!
    public synchronized void initialise(IBlurpifier blurpifier, int width, int height) {

        if(initialised) throw new IllegalStateException("Repository already initialised");

        this.blurpifier = blurpifier;
        this.screen = new Screen(width, height);
        initialised = true;
    }


    // Test purposes only
    void resetSingleton() {
        instance = null;
        initialised = false;
    }
}
