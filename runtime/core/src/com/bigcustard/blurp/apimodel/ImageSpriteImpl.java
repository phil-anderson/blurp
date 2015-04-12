package com.bigcustard.blurp.apimodel;

import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.core.commands.*;
import com.bigcustard.blurp.model.*;

public class ImageSpriteImpl extends ImageSprite {

    private final ModelRepository modelRepository;

    public ImageSpriteImpl(Image image, double x, double y, ModelRepository modelRepository) {

        this.image = image;
        this.setPosition(x, y);
        this.modelRepository = modelRepository;

        this.scaleX = 1;
        this.scaleY = 1;
        this.alpha = 1;
    }

    @Override
    public void remove() {

        modelRepository.removeImageSprite(this);
    }

    @Override
    public ImageSprite moveTowards(double targetX, double targetY, double speed) {

        modelRepository.registerCommand(new MoveTowardsCommand(this, targetX, targetY, speed));
        return this;
    }

    public void dispose() {

        remove();
    }
}
