package com.bigcustard.blurp.core.common;

import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.*;

public class MoveHandler {

    public static void move(Sprite sprite, double angle, double distance) {

        sprite.x += BlurpStore.utils.sin(angle) * distance;
        sprite.y += BlurpStore.utils.cos(angle) * distance;
    }
}
