package com.bigcustard.blurp.core;

import com.bigcustard.blurp.model.*;

/**
 * This is the root of the Blurp model. It holds all of the objects what will be transformed, synced and rendered by the
 * Blurp runtime.
 * <p>
 * The various model objects either add themselves to this as they are instantiated, or in the case of singleton objects
 * like Canvas, are instantiated by the runtime implementation of this.
 */
public interface IModelRepository {

    public void addImage(Image image);

    public void removeImage(Image image);

    public void addImageSprite(ImageSprite imageSprite);

    public void removeImageSprite(ImageSprite imageSprite);

    public void requestBlurpify();

    public Canvas getCanvas();
}
