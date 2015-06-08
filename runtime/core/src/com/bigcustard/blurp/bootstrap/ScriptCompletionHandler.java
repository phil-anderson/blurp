package com.bigcustard.blurp.bootstrap;

import com.badlogic.gdx.*;
import com.bigcustard.blurp.core.*;

public class ScriptCompletionHandler {

    public void onTerminate() {

        BlurpStore.dispose();
        Gdx.app.exit();
    }

    public void onRestart() {

        BlurpStore.reset();
        BlurpStore.runtime.startThread();
    }
}
