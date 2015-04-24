package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.bootstrap.*;
import com.bigcustard.blurp.model.*;

public class TextSample implements BlurpRunnable {

    private static final String MESSAGE = "The quick, brown fox jumped over the lazy dog.";

    @Override
    public void run(Blurp blurp, Screen screen, Keyboard keyboard, Utils utils, Keys keys, Colours colours) {

//        blurp.setDebugMode(true);
        blurp.textSprite("LEFT-JUSTIFIED\n" + MESSAGE, 150, 500).justifyLeft(220);
        blurp.textSprite("CENTER-JUSTIFIED\n" + MESSAGE, 400, 500).justifyCenter(220);
        blurp.textSprite("RIGHT-JUSTIFIED\n" + MESSAGE, 650, 500).justifyRight(220);

        TextSprite anchorText = blurp.textSprite("").position(250, 200);
        TextSprite rotateAndScaleText = blurp.textSprite("Rotate and Scale!").position(600, 200);
        rotateAndScaleText.colour = colours.dodgerBlue;
        int frameCount = 0;
        while(true) {
            if(frameCount == 0) {
                anchorText.anchorMiddle();
                anchorText.anchorRight();
                anchorText.text = "Anchor Right\nAnchor Right\nAnchor Right";
            } else if(frameCount == 50) {
                anchorText.anchorLeft();
                anchorText.text = "Anchor Left\nAnchor Left\nAnchor Left";
            } else if(frameCount == 100) {
                anchorText.anchorCenter();
                anchorText.text = "Anchor Center\nAnchor Center\nAnchor Center";
            } else if(frameCount == 150) {
                anchorText.anchorTop();
                anchorText.text = "Anchor Top\nAnchor Top\nAnchor Top";
            } else if(frameCount == 200) {
                anchorText.anchorMiddle();
                anchorText.text = "Anchor Middle\nAnchor Middle\nAnchor Middle";
            } else if(frameCount == 250) {
                anchorText.anchorBottom();
                anchorText.text = "Anchor Bottom\nAnchor Bottom\nAnchor Bottom";
            } else if(frameCount == 300) {
                anchorText.anchorTop();
                anchorText.anchorRight();
                anchorText.text = "Anchor Top-Right\nAnchor Top-Right\nAnchor Top-Right";
            } else if(frameCount == 350) {
                anchorText.anchorBottom();
                anchorText.anchorLeft();
                anchorText.text = "Anchor Bottom-Left\nAnchor Bottom-Left\nAnchor Bottom-Left";
            }
            rotateAndScaleText.rotateBy(2);
            rotateAndScaleText.scale(utils.wave(0.5, 2, 2500));
            frameCount = (frameCount + 1) % 400;
            blurp.blurpify();
        }
    }
}
