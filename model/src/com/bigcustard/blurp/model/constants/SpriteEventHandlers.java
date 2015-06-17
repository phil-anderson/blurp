package com.bigcustard.blurp.model.constants;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.events.*;

public interface SpriteEventHandlers {

    public static SpriteEventHandler DoNothing = new SpriteEventHandler() {

        @Override
        public void handle(Sprite sprite) {
            // Do nothing
        }

        @Override
        public String toString() {

            return "SpriteEventHandler: DoNothing";
        }
    };

    public static SpriteEventHandler AtEndRemoveSprite = new SpriteEventHandler() {

        @Override
        public void handle(Sprite sprite) {
            sprite.remove();
        }

        @Override
        public String toString() {

            return "SpriteEventHandler: AtEndRemoveSprite";
        }
    };

    public static SpriteEventHandler AtEndNormaliseRotation = new SpriteEventHandler() {

        @Override
        public void handle(Sprite sprite) {
            sprite.normaliseAngle();
        }

        @Override
        public String toString() {

            return "SpriteEventHandler: AtEndNormaliseRotation";
        }
    };
}
