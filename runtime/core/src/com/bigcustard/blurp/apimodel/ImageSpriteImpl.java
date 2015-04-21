package com.bigcustard.blurp.apimodel;

import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.core.commands.*;
import com.bigcustard.blurp.model.*;

public class ImageSpriteImpl extends ImageSprite {

    private final RuntimeRepository runtimeRepository;
    private final ModelRepository modelRepository;

    public ImageSpriteImpl(Image image, double x, double y, RuntimeRepository runtimeRepository, ModelRepository modelRepository) {

        this.image = image;
        this.position(x, y);
        this.scaleX = 1;
        this.scaleY = 1;
        this.alpha = 1;

        this.runtimeRepository = runtimeRepository;
        this.modelRepository = modelRepository;
    }

    @Override
    public void remove() {

        modelRepository.removeImageSprite(this);
    }

    @Override
    public ImageSprite moveTowards(double targetX, double targetY, double speed) {

        runtimeRepository.registerCommand(new MoveTowardsCommand(this, targetX, targetY, speed));
        return this;
    }

    public void dispose() {

        remove();
    }
}
