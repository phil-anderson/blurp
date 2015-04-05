package com.bigcustard.blurp.core;

import java.util.*;
import com.bigcustard.blurp.core.commands.*;
import com.bigcustard.blurp.model.*;

/**
 * This is an implementation of the model module's ModelRepository. It gets instantiated by the runtime, and then used
 * by the various model objects to register their presence.
 */
public class ModelRepositoryImpl implements ModelRepository {

    private List<Image> images;
    private List<ImageSprite> imageSprites;
    private List<CommandRequestVisitable> commandRequests;

    public ModelRepositoryImpl() {

        images = new ArrayList<Image>();
        imageSprites = new ArrayList<ImageSprite>();
        commandRequests = new ArrayList<CommandRequestVisitable>();
    }

    @Override
    public void addImage(Image image) {

        images.add(image);
    }

    @Override
    public void removeImage(Image image) {

        images.remove(image);
    }

    @Override
    public void addImageSprite(ImageSprite imageSprite) {

        imageSprites.add(imageSprite);
    }

    @Override
    public void removeImageSprite(ImageSprite imageSprite) {

        imageSprites.remove(imageSprite);
    }

    @Override
    public void registerRequest(CommandRequestVisitable request) {

        commandRequests.add(request);
    }

    List<Image> getImages() {

        return images;
    }

    List<ImageSprite> getImageSprites() {

        return imageSprites;
    }

    List<CommandRequestVisitable> getCommandRequests() {

        return commandRequests;
    }

    public void commandRequestsComplete() {

        commandRequests.clear();
    }

    public void dispose() {

        images.clear();
        imageSprites.clear();
    }
}
