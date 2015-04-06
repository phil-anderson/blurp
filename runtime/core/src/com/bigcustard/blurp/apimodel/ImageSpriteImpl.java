package com.bigcustard.blurp.apimodel;

import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.core.commands.*;
import com.bigcustard.blurp.model.*;

public class ImageSpriteImpl extends ImageSprite {

    private final ApiModelRepository apiModelRepository;

    public ImageSpriteImpl(Image image, double x, double y, ApiModelRepository apiModelRepository) {

        this.image = image;
        this.setPosition(x, y);
        this.apiModelRepository = apiModelRepository;

        this.scaleX = 1;
        this.scaleY = 1;
        this.alpha = 1;

        apiModelRepository.addImageSprite(this);
    }

    @Override
    public void remove() {

        apiModelRepository.removeImageSprite(this);
    }

    @Override
    public ImageSprite moveTowards(double targetX, double targetY, double speed) {

        apiModelRepository.registerRequest(new MoveTowardsRequest(this, targetX, targetY, speed));
        return this;
    }

    public void dispose() {

        remove();
    }
}
