package com.bigcustard.blurp.model;

import java.util.*;
import com.badlogic.gdx.*;
import com.bigcustard.blurp.core.*;

/**
 * Hacky use of package to access the package-private model repository.
 *
 * Needed as I didn't want to expose the model repository to users. Any other option? Could inject but not convinced
 * it's be an improvement.
 */
public class ModelRepositoryWrapper {

    public ModelRepositoryWrapper(Blurpifier blurpifier) {

        Repository.getInstance().initialise(blurpifier, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public Screen getBackdrop() {

        return Repository.getInstance().getScreen();
    }

    public List<Image> getImages() {

        return Repository.getInstance().getImages();
    }

    public List<ImageSprite> getImageSprites() {

        return Repository.getInstance().getImageSprites();
    }
}
