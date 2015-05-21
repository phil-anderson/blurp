package com.bigcustard.blurp.model.events;

import com.bigcustard.blurp.model.*;

public interface SpriteEventHandler extends SimpleEventHandler<Sprite>{

    public static SpriteEventHandler NULL = new SpriteEventHandler() {
        @Override
        public void handle(Sprite sprite) {
            // Do nothing
        }
    };

    public static SpriteEventHandler AT_END_REMOVE_SPRITE = new SpriteEventHandler() {
        @Override
        public void handle(Sprite sprite) {
            sprite.remove();
        }
    };

    public void handle(Sprite sprite);
}
