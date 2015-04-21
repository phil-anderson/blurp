package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.bootstrap.*;
import com.bigcustard.blurp.model.*;

public class HelloTextWorld implements BlurpRunnable {

    private static final String PALINDROME = "A man, a plan, a canal - Panama!";

    @Override
    public void run(Blurp blurp, Screen screen, Keyboard keyboard, Utils utils, Keys keys) {

        blurp.setDebugMode(true);
        blurp.textSprite(PALINDROME, 400, 550).justifyLeft(300);
        blurp.textSprite(PALINDROME, 400, 500).justifyCenter(300);
        blurp.textSprite(PALINDROME, 400, 450).justifyRight(300);

        TextSprite textSprite = blurp.textSprite("");
        while(true) {
            textSprite.anchorRight();
            textSprite.text = "Anchor Right";
            blurp.blurpify();
            utils.sleep(500);

            textSprite.anchorLeft();
            textSprite.text = "Anchor Left";
            blurp.blurpify();
            utils.sleep(500);

            textSprite.anchorCenter();
            textSprite.text = "Anchor Center";
            blurp.blurpify();
            utils.sleep(500);

            textSprite.anchorTop();
            textSprite.text = "Anchor Top";
            blurp.blurpify();
            utils.sleep(500);

            textSprite.anchorMiddle();
            textSprite.text = "Anchor Middle";
            blurp.blurpify();
            utils.sleep(500);

            textSprite.anchorBottom();
            textSprite.text = "Anchor Bottom";
            blurp.blurpify();
            utils.sleep(500);
        }
    }
}
