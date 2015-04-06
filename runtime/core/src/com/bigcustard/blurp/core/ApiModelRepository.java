package com.bigcustard.blurp.core;

import java.util.*;
import com.bigcustard.blurp.core.commands.*;
import com.bigcustard.blurp.model.*;

/**
 * Stores and manages api model objects that require a different runtime implementation.
 */
public class ApiModelRepository {

    private List<Image> images;
    private List<ImageSprite> imageSprites;
    private List<CommandRequestVisitable> commandRequests;

    public ApiModelRepository() {

        images = new ArrayList<Image>();
        imageSprites = new ArrayList<ImageSprite>();
        commandRequests = new ArrayList<CommandRequestVisitable>();
    }

    public void addImage(Image image) {

        images.add(image);
    }

    public void removeImage(Image image) {

        images.remove(image);
    }

    public void addImageSprite(ImageSprite imageSprite) {

        imageSprites.add(imageSprite);
    }

    public void removeImageSprite(ImageSprite imageSprite) {

        imageSprites.remove(imageSprite);
    }

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

    public Image getImage(String imageFilename) {

        for(Image image : images) {
            if(image.filename.equals(imageFilename)) return image;
        }
        return null;
    }

    public void dispose() {

        images.clear();
        imageSprites.clear();
    }
}
