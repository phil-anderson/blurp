package com.bigcustard.blurp.model;

import java.util.*;

/**
 * Slightly hacky use of package to access the package-private model repository.
 *
 * Needed as I didn't want to expose the model repository to users
 */
public class ModelRepositoryWrapper {

    public ModelRepositoryWrapper() {}

    public List<Image> getImages() {

        return Repository.getInstance().getImages();
    }

    public List<ImageSprite> getImageSprites() {

        return Repository.getInstance().getImageSprites();
    }
}
