package com.bigcustard.blurp.core;

import com.badlogic.gdx.*;
import com.badlogic.gdx.utils.viewport.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.utils.*;

public class Runner {


    private Blurpifier blurpifier;
    private RuntimeRepository runtimeRepository;

    private final Thread scriptThread;

    public Runner(BlurpMain script, Viewport viewport) {

        SF.instantiateSingletons(viewport);
        scriptThread = new Thread(new ScriptRunnable(script));
    }

    public Screen getScreen() {

        return SF.getBlurpScreen();
    }

    public void start() {

        scriptThread.start();
    }

    // TODO: Implement this properly
    public void stop() {

        SF.dispose();
    }

    // TODO: Need a way to pause / resume / restart / stop the script. Communicate via flags in Repository? Blurpifier states?
    //       Note - When paused, the Blurpifier will still have to allow renders to continue so that input gestures
    //              (e.g. to resume) get picked up.
    private class ScriptRunnable implements Runnable {

        private BlurpMain script;

        private ScriptRunnable(BlurpMain script) {

            this.script = script;
        }

        @Override
        public void run() {

            script.run();
            while(true) {
                script.blurpify();
                Sleep.forOneMs();
            }
        }
    }
}
