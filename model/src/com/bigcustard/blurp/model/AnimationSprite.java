package com.bigcustard.blurp.model;

import java.util.*;
import com.bigcustard.blurp.model.constants.*;

public abstract class AnimationSprite extends Sprite<AnimationSprite> {

    public String[] images;
    public boolean reverse;
    public boolean loop;
    public int currentFrame;

    protected AnimationSprite() {

        targetStyle = TargetStyle.Circle;
        loop = true;
    }

    public AnimationSprite setImages(String[] newImages) {

        images = newImages;
        currentFrame = 0;
        return this;
    }

    public AnimationSprite setFrame(int frameNumber) {

        currentFrame = frameNumber;
        return this;
    }

    public AnimationSprite step() {

        currentFrame += reverse ? -1 : 1;
        if(loop) {
            currentFrame %= images.length;
            if(currentFrame < 0) {
                currentFrame += images.length;
            }
        } else {
            currentFrame = reverse ? Math.max(currentFrame, 0) : Math.min(currentFrame, images.length - 1);
        }
        return this;
    }

    public abstract AnimationSprite play(int frameDuration);

    public abstract AnimationSprite stop();

    public abstract boolean isPlaying();

    public int numFrames() {

        return images.length;
    }

    @Override
    public String toString() {

        return String.format("ImageSprite: images=%s ", Arrays.toString(images)) + super.toString();
    }
}
