package com.bigcustard.blurp.apimodel;

import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.core.commands.*;
import com.bigcustard.blurp.model.*;

import static com.badlogic.gdx.graphics.g2d.BitmapFont.*;

public class TextSpriteImpl extends TextSprite {

    private final RuntimeRepository runtimeRepository;
    private final ModelRepository modelRepository;

    public enum Handle { Left, Center, Right, Top, Middle, Bottom }

    private HAlignment justification = HAlignment.CENTER;
    private Handle hAnchor = Handle.Center;
    private Handle vAnchor = Handle.Middle;
    private double wrapWidth = -1;

    public TextSpriteImpl(String text, double x, double y, RuntimeRepository runtimeRepository, ModelRepository modelRepository) {

        this.text = text;
        this.position(x, y);
        this.scaleX = 1;
        this.scaleY = 1;
        this.alpha = 1;

        this.runtimeRepository = runtimeRepository;
        this.modelRepository = modelRepository;
    }

    @Override
    public TextSprite anchorLeft() {

        hAnchor = Handle.Left;
        return this;
    }

    @Override
    public TextSprite anchorCenter() {

        hAnchor = Handle.Center;
        return this;
    }

    @Override
    public TextSprite anchorRight() {

        hAnchor = Handle.Right;
        return this;
    }

    @Override
    public TextSprite anchorTop() {

        vAnchor = Handle.Top;
        return this;
    }

    @Override
    public TextSprite anchorMiddle() {

        vAnchor = Handle.Middle;
        return this;
    }

    @Override
    public TextSprite anchorBottom() {

        vAnchor = Handle.Bottom;
        return this;
    }

    @Override
    public TextSprite justifyLeft(double width) {

        justification = HAlignment.LEFT;
        this.wrapWidth = width;
        return this;
    }

    @Override
    public TextSprite justifyCenter(double width) {

        justification = HAlignment.CENTER;
        this.wrapWidth = width;
        return this;
    }

    @Override
    public TextSprite justifyRight(double width) {

        justification = HAlignment.RIGHT;
        this.wrapWidth = width;
        return this;
    }

    @Override
    public void remove() {

        modelRepository.removeTextSprite(this);
    }

    public Handle getHAnchor() {

        return hAnchor;
    }

    public Handle getVAnchor() {

        return vAnchor;
    }

    public HAlignment getJustification() {

        return justification;
    }

    public double getWrapWidth() {

        return wrapWidth;
    }

    @Override
    public TextSprite moveTowards(double targetX, double targetY, double speed) {

        runtimeRepository.registerCommand(new MoveTowardsCommand(this, targetX, targetY, speed));
        return this;
    }
}
