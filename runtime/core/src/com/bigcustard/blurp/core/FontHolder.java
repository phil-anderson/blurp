package com.bigcustard.blurp.core;

import com.badlogic.gdx.graphics.g2d.*;

public class FontHolder {


    private BitmapFont font;
    private final float lineHeight;
    private final float spaceWidth;
    private final float xHeight;
    private final float capHeight;
    private final float ascent;
    private final float descent;
    private final float down;
    private final float scaleX;
    private final float scaleY;

    public FontHolder(BitmapFont font) {

        this.font = font;

        BitmapFont.BitmapFontData data = font.getData();
        this.lineHeight = data.lineHeight;
        this.spaceWidth = data.spaceWidth;
        this.xHeight = data.xHeight;
        this.capHeight = data.capHeight;
        this.ascent = data.ascent;
        this.descent = data.descent;
        this.down = data.down;
        this.scaleX = data.scaleX;
        this.scaleY = data.scaleY;
    }

    public void reset() {

        BitmapFont.BitmapFontData data = font.getData();
        data.lineHeight = this.lineHeight;
        data.spaceWidth = this.spaceWidth;
        data.xHeight = this.xHeight;
        data.capHeight = this.capHeight;
        data.ascent = this.ascent;
        data.descent = this.descent;
        data.down = this.down;
        data.scaleX = this.scaleX;
        data.scaleY = this.scaleY;
    }

    public BitmapFont getFont() {

        return font;
    }
}
