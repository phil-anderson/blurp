package com.bigcustard.blurp.runtimemodel;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Align;
import com.bigcustard.blurp.apimodel.TextSpriteImpl;
import com.bigcustard.blurp.core.BlurpStore;
import com.bigcustard.blurp.model.TextSprite;
import com.bigcustard.blurp.model.constants.Handle;
import com.bigcustard.blurp.model.constants.Justification;

import java.util.HashMap;
import java.util.Map;

public class RuntimeTextSprite extends RuntimeSprite<TextSprite> {

    // todo
    private static final Map<Justification, Integer> JUSTIFICATION_TO_ALIGNMENT = new HashMap<Justification, Integer>();

    static {
        JUSTIFICATION_TO_ALIGNMENT.put(Justification.AlignLeft, Align.left);
        JUSTIFICATION_TO_ALIGNMENT.put(Justification.AlignCenter, Align.center);
        JUSTIFICATION_TO_ALIGNMENT.put(Justification.AlignRight, Align.right);
    }

    private BitmapFont font;
    private String text;
    private float wrapWidth;
    private Handle.HHandle hHandle;
    private Handle.VHandle vHandle;
    private int alignment;
    private float fontSize;
    private boolean markupEnabled;

    @Override
    public void sync(TextSprite modelSprite, boolean newInstance) {

        super.sync(modelSprite, newInstance);
        text = modelSprite.text;

        TextSpriteImpl modelSpriteImpl = (TextSpriteImpl) modelSprite;
        hHandle = modelSpriteImpl.handle.gethHandle();
        vHandle = modelSpriteImpl.handle.getvHandle();
        alignment = JUSTIFICATION_TO_ALIGNMENT.get(modelSpriteImpl.getJustification());
        wrapWidth = (float) modelSpriteImpl.getWrapWidth();
        fontSize = (float) modelSprite.fontSize;
        markupEnabled = modelSprite.colourTagsEnabled;

        // TODO: Add ability to change (and hence sync) fonts
        if (newInstance) font = BlurpStore.defaultFont.getFont();
    }

    @Override
    public void preRender() {

        font.getData().setScale(fontSize / (font.getLineHeight() / font.getScaleX()));

        //todo
        font.getData().markupEnabled = markupEnabled;
//
//        if(wrapWidth == -1) {
//            TextBounds bounds = font.getMultiLineBounds(text);
//            setSize(bounds.width, bounds.height);
//        } else {
//            TextBounds bounds = font.getWrappedBounds(text, wrapWidth);
//            setSize(wrapWidth, bounds.height);
//        }

        // Make sprite's height a multiple of the font's line height
        setHeight((float) (Math.ceil(getHeight() / fontSize) * fontSize));

        calculateOrigins();

        // This has to go here rather than in sync for TextSprites as we determine size etc. late.
        updateCollisionShapes();
    }

    @Override
    public void render(Batch batch, float parentAlpha) {
        font.setColor(getColor());
        if (wrapWidth == -1) {
            font.draw(batch, text, 0, getHeight());
        } else {
            font.draw(batch, text, 0, getHeight(), wrapWidth, alignment, true);
        }
    }

    private void calculateOrigins() {

        float originX = 0;
        float originY = 0;
        if (hHandle != Handle.HHandle.Left) {
            originX = getWidth();
            if (hHandle == Handle.HHandle.Center) originX /= 2;
        }
        if (vHandle != Handle.VHandle.Bottom) {
            originY = getHeight();
            if (vHandle == Handle.VHandle.Middle) originY /= 2;
        }
        setOrigin(originX, originY);
    }

    @Override
    public void dispose() {

        remove();
    }
}
