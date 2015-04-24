package com.bigcustard.blurp.runtimemodel;

import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.math.*;
import com.bigcustard.blurp.apimodel.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.*;

import static com.badlogic.gdx.graphics.g2d.BitmapFont.*;
import static com.bigcustard.blurp.apimodel.TextSpriteImpl.*;

public class RuntimeTextSprite extends RuntimeSprite<TextSprite> {

    private BitmapFont font;
    private String text;
    private float wrapWidth;
    private Handle hAnchor;
    private Handle vAnchor;
    private HAlignment justification;

    private Matrix4 transformationStorage = new Matrix4();
    private Affine2 transform = new Affine2();
    private Matrix4 transformMatrix = new Matrix4();

    @Override
    public void sync(TextSprite modelSprite, BlurpObjectProvider blurpObjectProvider, boolean newInstance) {

        super.sync(modelSprite, blurpObjectProvider, newInstance);
        text = modelSprite.text;

        TextSpriteImpl modelSpriteImpl = (TextSpriteImpl) modelSprite;
        hAnchor = modelSpriteImpl.getHAnchor();
        vAnchor = modelSpriteImpl.getVAnchor();
        justification = modelSpriteImpl.getJustification();
        wrapWidth = (float) modelSpriteImpl.getWrapWidth();

        // TODO: Add ability to change (and hence sync) fonts
        if(newInstance) font = blurpObjectProvider.getSystemFont();

        // TODO: Fix this - bt of a hack... Think I can divide by lineheight  / fontscale.
        font.setScale(1);
        font.setScale((float) (modelSpriteImpl.fontSize / font.getLineHeight()));


        if(wrapWidth == -1) {
            TextBounds bounds = font.getMultiLineBounds(text);
            setSize(bounds.width, bounds.height);
        } else {
            TextBounds bounds = font.getWrappedBounds(text, wrapWidth);
            setSize(wrapWidth, bounds.height);
        }
        // Make height round up to multiple of line height as text bounds don;t include descenders.
        if(getHeight() % font.getLineHeight() != 0) {
            setHeight((float) (Math.ceil(getHeight() / font.getLineHeight()) * font.getLineHeight()));
        }

        calculateOrigins();

        transform.setToTrnRotScl(getX(), getY(), getRotation(), getScaleX(), getScaleY());
        transform.translate(-getOriginX(), -getOriginY() + getHeight());
        transformMatrix.set(transform);

        if(newInstance) {
            blurpObjectProvider.getBlurpScreen().addActor(this);
        }
    }

//    Also, do I want to change the naming of teh anchor methods?... Theyre a bit counter-intuitive.

    @Override
    public void drawImpl(Batch batch, float parentAlpha) {
        transformationStorage.set(batch.getTransformMatrix());

        batch.setTransformMatrix(transformMatrix);
        try {
            font.setColor(getColor());
            if(wrapWidth == -1) {
                font.drawMultiLine(batch, text, 0, 0);
            } else {
                font.drawWrapped(batch, text, 0, 0, wrapWidth, justification);
            }
        } finally {
            batch.setTransformMatrix(transformationStorage);
        }
    }

    @Override
    protected void drawDebugBounds (ShapeRenderer shapes) {

            shapes.set(ShapeRenderer.ShapeType.Line);
            float value = (float) Utils.ENGINE_INSTANCE.wave(0, 1, 2000);
            shapes.setColor(value, 1 - value, 0, 1);
            shapes.rect(getX() - getOriginX() + 1, getY() - getOriginY() + 1, getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }

    private void calculateOrigins() {

        float originX = 0;
        float originY = 0;
        if(hAnchor != Handle.Left) {
            originX = getWidth();
            if(hAnchor == Handle.Center) originX /= 2;
        }
        if(vAnchor != Handle.Bottom) {
            originY = getHeight();
            if(vAnchor == Handle.Middle) originY /= 2;
        }
        setOrigin(originX, originY);
    }

    @Override
    public void dispose() {

        remove();
    }
}
