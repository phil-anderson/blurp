package com.bigcustard.blurp.runtimemodel;

import aurelienribon.tweenengine.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.bigcustard.blurp.apimodel.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.Sprite;
import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.util.*;

public abstract class RuntimeSprite<T extends Sprite> extends Actor implements RuntimeObject<T> {

    private Matrix4 transformationStorage = new Matrix4();
    private Affine2 transform = new Affine2();
    private Matrix4 transformMatrix = new Matrix4();
    private Circle collisionCircle = new Circle();
    private Polygon collisionRectangle = new Polygon();
    private CollisionShape collisionShape;
    private EffectImpl effect;

    protected RuntimeSprite() { }

    @Override
    public void sync(T modelSprite, BlurpObjectProvider blurpObjectProvider, boolean newInstance) {

        setPosition((float) modelSprite.x, (float) modelSprite.y);
        setScale((float) modelSprite.scaleX, (float) modelSprite.scaleY);
        setRotation((float) -modelSprite.rotation);
        setColor(Convert.toGdxColour(modelSprite.colour, modelSprite.alpha));
        this.collisionShape = modelSprite.collisionShape;

        TweenManager tweener = blurpObjectProvider.getTweener();
        if(modelSprite.effect != this.effect) {
            tweener.killTarget(modelSprite);
            this.effect = (EffectImpl) modelSprite.effect;
            if(this.effect != null) {
                BaseTween tween = this.effect.getTween(modelSprite);
                System.out.println(tween);
                tween.start(tweener);
            }

        } else {
            if(modelSprite.effect != null) {
                if(!tweener.containsTarget(modelSprite)) {
                    modelSprite.effect = null;
                }
            }
        }
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

    public void preRender() { }

    public abstract void render(Batch batch, float parentAlpha);

    @Override
    protected void drawDebugBounds (ShapeRenderer shapes) {

        // TODO: Highlight on mouseover instead.
        long millis = System.currentTimeMillis();
        if(millis % 1400 < 600) {
            if(millis % 200 < 100) {
                shapes.setColor(1, 1, 1, 1);
            }
        }

        shapes.set(ShapeRenderer.ShapeType.Line);
        if(collisionShape == CollisionShape.BoundaryRectangle) {
            shapes.polygon(collisionRectangle.getTransformedVertices());
        } else {
            shapes.circle(collisionCircle.x, collisionCircle.y, collisionCircle.radius,
                          (int)(9 * Math.cbrt(collisionCircle.radius)));
        }
    }

    protected void updateCollisionShapes() {

        collisionCircle.set(getX() + getWidth() / 2 - getOriginX(), getY() + getHeight() / 2 - getOriginY(),
                            Math.min(getWidth() * getScaleX(), getHeight() * getScaleY()) / 2);

        // TODO: If this is slow, we can try checking if width / height have changed.
        collisionRectangle.setVertices(new float[] { 0, 0, getWidth(), 0, getWidth(), getHeight(), 0, getHeight() });
        collisionRectangle.setOrigin(getOriginX(), getOriginY());
        collisionRectangle.setPosition(getX() - getOriginX(), getY() - getOriginY());
        collisionRectangle.setRotation(getRotation());
        collisionRectangle.setScale(getScaleX(), getScaleY());
    }

    public Circle getCollisionCircle() {

        return collisionCircle;
    }

    public Polygon getCollisionRectangle() {

        return collisionRectangle;
    }

    @Override
    public void dispose() {

    }
}
