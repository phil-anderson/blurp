package com.bigcustard.blurp.ui;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.*;

public class SimpleButton {

    private final TextureRegion textureRegion;
    private final Colour colour;
    private float x, y;
    private float size;

    private Vector2 mousePos = new Vector2();

    public SimpleButton(String filename, Colour colour) {

        textureRegion = new TextureRegion(new Texture(Gdx.files.classpath(filename), true));
        textureRegion.getTexture().setFilter(Texture.TextureFilter.MipMapLinearNearest, Texture.TextureFilter.Linear);
        this.colour = colour;
    }

    public void setPosition(double x, double y, double size) {

        this.x = (float) x;
        this.y = (float) y;
        this.size = (float) size;
    }

    public void render(Batch batch) {

        batch.setColor((float) colour.red, (float) colour.green, (float) colour.blue, isMouseOver() ? 1 : 0.5f);
        batch.draw(textureRegion, x, y, size, size);
        batch.setColor(1, 1, 1, 1);
    }

    public boolean wasClicked() {

        return Gdx.input.justTouched() && isMouseOver();
    }

    private boolean isMouseOver() {

        mousePos.set(Gdx.input.getX(), Gdx.input.getY());
        BlurpStore.staticViewport.unproject(mousePos);
        return mousePos.x >= x && mousePos.x <= x + size && mousePos.y >= y && mousePos.y <= y + size;
    }

    public void dispose() {

        textureRegion.getTexture().dispose();
    }
}
