package com.bigcustard.blurp.model.java.bootstrap;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.System;
import com.bigcustard.blurp.model.effects.*;

public interface JavaBootstrap {

    public System getSystem();
    public Screen getScreen();
    public Camera getCamera();
    public Resources getResources();
    public Effects getEffects();
    public Keyboard getKeyboard();
    public Mouse getMouse();
    public Utils getUtils();
    public Timer getTimer();
}
