package com.bigcustard.blurp.runtimemodel;

import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.*;

public class RuntimeImageSprite extends RuntimeSprite<ImageSprite> {

    private static final Utils UTILS = new Utils();

    private RuntimeImage image;

    @Override
    public void drawImpl(Batch batch, float parentAlpha) {

        // Draw scaled texture centered at (and rotated around) origin
        batch.draw(image.getTextureRegion(),
                   getX() - getOriginX(), getY() - getOriginY(),
                   getOriginX(), getOriginY(),
                   getWidth(), getHeight(),
                   getScaleX(), getScaleY(),
                   getRotation());
    }

    @Override
    public void sync(ImageSprite modelImageSprite, BlurpObjectProvider blurpObjectProvider, boolean newInstance) {

        super.sync(modelImageSprite, blurpObjectProvider, newInstance);

        image = blurpObjectProvider.getRuntimeRepository().getImage(modelImageSprite.image);

        int width = image.getTextureRegion().getRegionWidth();
        int height = image.getTextureRegion().getRegionHeight();
        setSize(width, height);

        setOrigin(width / 2, height / 2);

        if(newInstance) {
            blurpObjectProvider.getBlurpScreen().addActor(this);
        }
    }

    @Override
    protected void drawDebugBounds (ShapeRenderer shapes) {

        shapes.set(ShapeRenderer.ShapeType.Line);
        float value = (float) UTILS.wave(0, 1, 2000);
        shapes.setColor(value, 1 - value, 0, 1);
        shapes.rect(getX() - getOriginX(), getY() - getOriginY(),
                    getOriginX(), getOriginY(),
                    getWidth() - 1, getHeight() - 1,
                    getScaleX(), getScaleY(),
                    getRotation());
    }

    @Override
    public void dispose() {

        super.remove();
    }
}
