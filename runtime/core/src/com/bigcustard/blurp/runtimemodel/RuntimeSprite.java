package com.bigcustard.blurp.runtimemodel;

import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.Sprite;
import com.bigcustard.blurp.util.*;

public abstract class RuntimeSprite<T extends Sprite> extends Actor implements RuntimeObject<T> {

    private Matrix4 transformationStorage = new Matrix4();
    private Affine2 transform = new Affine2();
    private Matrix4 transformMatrix = new Matrix4();

    protected RuntimeSprite() { }

    @Override
    public void sync(T modelSprite, BlurpObjectProvider blurpObjectProvider, boolean newInstance) {

        setPosition((float) modelSprite.x, (float) modelSprite.y);
        setScale((float) modelSprite.scaleX, (float) modelSprite.scaleY);
        setRotation((float) -modelSprite.rotation);
        setColor(Convert.toGdxColour(modelSprite.colour, modelSprite.alpha));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        preRender();
        batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a * parentAlpha);

        transform.setToTrnRotScl(getX(), getY(), getRotation(), getScaleX(), getScaleY());
        transform.translate(-getOriginX(), -getOriginY());
        transformMatrix.set(transform);

        transformationStorage.set(batch.getTransformMatrix());
        batch.setTransformMatrix(transformMatrix);
        try {
            render(batch, parentAlpha);
        } finally {
            batch.setTransformMatrix(transformationStorage);
            batch.setColor(1, 1, 1, 1);
        }
    }

    public void preRender() { };

    public abstract void render(Batch batch, float parentAlpha);

    @Override
    protected void drawDebugBounds (ShapeRenderer shapes) {

        shapes.set(ShapeRenderer.ShapeType.Line);
        shapes.rect(getX() - getOriginX(), getY() - getOriginY(),
                    getOriginX(), getOriginY(),
                    getWidth(), getHeight(),
                    getScaleX(), getScaleY(),
                    getRotation());
    }



    @Override
    public void dispose() {

    }
}
