package com.bigcustard.blurp.runtimemodel;

import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.bigcustard.blurp.apimodel.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.*;

import static com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import static com.bigcustard.blurp.apimodel.TextSpriteImpl.*;

public class RuntimeTextSprite extends RuntimeSprite<TextSprite> {

    private BitmapFont font;
    private String text;
    private float wrapWidth;
    private Handle hAnchor;
    private Handle vAnchor;
    private HAlignment justification;

    private BitmapFont.TextBounds bounds;

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
        if(wrapWidth == -1) {
            bounds = font.getBounds(text);
            setSize(bounds.width, bounds.height);
        } else {
            bounds = font.getWrappedBounds(text, wrapWidth);
            setSize(wrapWidth, bounds.height);
        }

        if(newInstance) {
            blurpObjectProvider.getBlurpScreen().addActor(this);
        }
    }

    @Override
    public void drawImpl(Batch batch, float parentAlpha) {

//  DOESN'T SCALE PROPERLY, PROBABLY OTHER STUFF TOO - ALPHA, ROTATE.
        float x = calcDrawX();
        float y = calcDrawY();
        if(wrapWidth == -1) {
            font.draw(batch, text, x, y);
        } else {
            font.drawWrapped(batch, text, x, y, wrapWidth, justification);
        }
    }

    @Override
    protected void drawDebugBounds (ShapeRenderer shapes) {

        shapes.set(ShapeRenderer.ShapeType.Line);
        float value = (float) Utils.ENGINE_INSTANCE.wave(0, 1, 2000);
        shapes.setColor(value, 1 - value, 0, 1);
        float x = calcDrawX();
        float y = calcDrawY() - getHeight(); // Text is anchored at top and draws down for some reason.
        shapes.rect(x, y, getOriginX(), getOriginY(), getWidth() - 1, getHeight() - 1, getScaleX(), getScaleY(), getRotation());
    }

    private float calcDrawX() {

        float x = getX();
        if(hAnchor == Handle.Center) x -= getWidth() / 2;
        if(hAnchor == Handle.Right) x -= getWidth();
        return x;
    }

    private float calcDrawY() {

        float y = getY();
        if(vAnchor == Handle.Middle) y += getHeight() / 2;
        if(vAnchor == Handle.Top) y += getHeight();

        return y;
    }

    @Override
    public void dispose() {

        remove();
    }
}
