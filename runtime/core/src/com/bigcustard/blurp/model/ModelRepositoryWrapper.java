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

        ModelRepository.getInstance().initialise(blurpifier, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public Canvas getScreen() {

        return ModelRepository.getInstance().getCanvas();
    }

    public List<Image> getImages() {

        return ModelRepository.getInstance().getImages();
    }

    public List<ImageSprite> getImageSprites() {

        return ModelRepository.getInstance().getImageSprites();
    }

    public void dispose() {

        ModelRepository.getInstance().dispose();
    }
}
