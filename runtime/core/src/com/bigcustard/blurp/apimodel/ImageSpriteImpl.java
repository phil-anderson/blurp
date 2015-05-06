package com.bigcustard.blurp.apimodel;

import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.core.common.*;
import com.bigcustard.blurp.model.*;

public class ImageSpriteImpl extends ImageSprite {

    public ImageSpriteImpl(Image image, double x, double y) {

        this.image = image;
        this.position(x, y);
        this.scaleX = 1;
        this.scaleY = 1;
        this.alpha = 1;
    }

    @Override
    public void remove() {

        BlurpStore.modelRepository.removeImageSprite(this);
    }

    @Override
    public ImageSprite moveTowards(double targetX, double targetY, double distanceToMove) {

        MoveTowardsHandler.moveTowards(this, targetX, targetY, distanceToMove);
        return this;
    }

    @Override
    public boolean collidedWith(Sprite other) {

        return CollisionDetector.detectCollision(this, other);
    }

    public void dispose() {

        remove();
    }
}
