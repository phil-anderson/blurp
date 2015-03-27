package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.samples.core.*;

public class HelloWorld extends RunnableSample { static { clarse = HelloWorld.class; }

    @Override
    public void run() {

        // TODO Make Sprites center themselves by default.
        new ImageSprite("star.png", 400, 300);
    }
}
