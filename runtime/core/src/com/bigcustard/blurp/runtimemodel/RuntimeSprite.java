package com.bigcustard.blurp.runtimemodel;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.core.commands.*;
import com.bigcustard.blurp.model.Sprite;
import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.util.*;

public abstract class RuntimeSprite<T extends Sprite> extends Actor implements RuntimeObject<T> {

    private final SpriteClickListener mouseListener;

    private Matrix4 transformationStorage = new Matrix4();
    private Affine2 transform = new Affine2();
    private Matrix4 transformMatrix = new Matrix4();
    private Circle collisionCircle = new Circle();
    private Polygon collisionRectangle = new Polygon();
    private CollisionShape collisionShape;
    private SpriteLayer layer;

    protected RuntimeSprite() {

        mouseListener = new SpriteClickListener(this);
        addListener(mouseListener);
    }

    @Override
    public void sync(T modelSprite, boolean newInstance) {

        setPosition((float) modelSprite.x, (float) modelSprite.y);
        setScale((float) modelSprite.scaleX, (float) modelSprite.scaleY);
        setRotation((float) -modelSprite.rotation);
        setColor(Convert.toGdxColour(modelSprite.colour, modelSprite.alpha));
        setVisible(!modelSprite.hidden);
        this.collisionShape = modelSprite.collisionShape;

        if(modelSprite.layer != this.layer) {
            this.layer = modelSprite.layer;
            BlurpStore.runtimeRepository.deferCommand(new HandleLayerCommand(this));
        }

        // Sync mouse state from runtime to model
        modelSprite.mouseState(mouseListener.buildState());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        preRender();

        Color originalColour = batch.getColor();
        Color newColour = originalColour.cpy().mul(getColor());
        batch.setColor(newColour.r, newColour.g, newColour.b, newColour.a * parentAlpha);

        transform.setToTrnRotScl(getX(), getY(), getRotation(), getScaleX(), getScaleY());
        transform.translate(-getOriginX(), -getOriginY());
        transformMatrix.set(transform);

        transformationStorage.set(batch.getTransformMatrix());
        batch.setTransformMatrix(transformMatrix);
        try {
            render(batch, parentAlpha);
        } finally {
            batch.setTransformMatrix(transformationStorage);
            batch.setColor(originalColour);
        }
    }

    // TODO: This is hacky at the moment as it ignores the transformed X and Y passed in. Blurp uses X and Y to mean
    // center, not bottom-left, and I couldn't get it transforming correctly when rotated.
    @Override
    public Actor hit(float x, float y, boolean touchable) {

        if (touchable && this.getTouchable() != Touchable.enabled) return null;

        if (!MouseState.isInsideWindow()) return null;

        Vector3 mouseXY = MouseState.getPosition(layer);
        boolean hit = collisionShape == CollisionShape.CenterCircle ? collisionCircle.contains(mouseXY.x, mouseXY.y)
                                                                    : collisionRectangle.contains(mouseXY.x, mouseXY.y);
        return hit ? this : null;
    }

    public void preRender() { }

    public abstract void render(Batch batch, float parentAlpha);

    @Override
    protected void drawDebugBounds (ShapeRenderer shapes) {

        if(mouseListener.isOver()) {
            shapes.setColor(1, 1, 1, 1);
        } else {
            shapes.setColor(BlurpState.debugColour);
        }

        shapes.set(ShapeRenderer.ShapeType.Line);

        shapes.getColor().a = collisionShape == CollisionShape.BoundaryRectangle ? 1 : 0.5f;
        shapes.polygon(collisionRectangle.getTransformedVertices());

        shapes.getColor().a = collisionShape == CollisionShape.CenterCircle ? 1 : 0.5f;
        shapes.circle(collisionCircle.x, collisionCircle.y, collisionCircle.radius,
                      (int) (9 * Math.cbrt(collisionCircle.radius)));
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

    public SpriteClickListener getMouseListener() {

        return mouseListener;
    }

    public SpriteLayer getLayer() {

        return layer;
    }

    @Override
    public void dispose() { }
}
