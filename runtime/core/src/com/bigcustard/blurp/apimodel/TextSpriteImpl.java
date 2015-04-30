package com.bigcustard.blurp.apimodel;

import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.core.common.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.constants.*;

public class TextSpriteImpl extends TextSprite {

    private final RuntimeRepository runtimeRepository;
    private final ModelRepository modelRepository;

    public TextSpriteImpl(String text, double x, double y, RuntimeRepository runtimeRepository, ModelRepository modelRepository) {

        this.text = text;
        position(x, y);
        scaleX = 1;
        scaleY = 1;
        alpha = 1;
        colour = Colours.WHITE;
        fontSize = 30;
        wrapWidth = -1;
        justification = Justification.AlignLeft;
        handle = Handle.Center;

        this.runtimeRepository = runtimeRepository;
        this.modelRepository = modelRepository;
    }

    @Override
    public void remove() {

        modelRepository.removeTextSprite(this);
    }

    public Justification getJustification() {

        return justification;
    }

    public double getWrapWidth() {

        return wrapWidth;
    }

    @Override
    public TextSprite moveTowards(double targetX, double targetY, double distanceToMove) {

        MoveTowardsHandler.moveTowards(this, targetX, targetY, distanceToMove);
        return this;
    }

    @Override
    public boolean collidedWith(Sprite other) {

        return CollisionDetector.detectCollision(this, other, runtimeRepository);
    }
}
