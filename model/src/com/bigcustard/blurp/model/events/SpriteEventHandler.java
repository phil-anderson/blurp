package com.bigcustard.blurp.model.events;

import com.bigcustard.blurp.model.*;

public interface SpriteEventHandler extends SimpleEventHandler<Sprite>{

    public void handle(Sprite sprite);
}
