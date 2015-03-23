package com.bigcustard.blurp.model;

import java.util.*;
import com.bigcustard.blurp.core.*;

/**
 * Slightly hacky use of package to access the package-private model repository.
 *
 * Needed as I didn't want to expose the model repository to users
 */
public class ModelRepositoryWrapper {

    public ModelRepositoryWrapper() {}

    public Backdrop getBackdrop() {

        return Repository.getInstance().getBackdrop();
    }

    public List<Image> getImages() {

        return Repository.getInstance().getImages();
    }

    public List<ImageSprite> getImageSprites() {

        return Repository.getInstance().getImageSprites();
    }

    public void setBlurpifier(Blurpifier blurpifier) {

        Repository.getInstance().initialise(blurpifier);
    }
}
