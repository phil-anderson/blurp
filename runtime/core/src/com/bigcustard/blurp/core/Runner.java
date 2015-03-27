package com.bigcustard.blurp.core;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.ui.*;
import com.bigcustard.blurp.utils.*;

public class Runner {

    private BlurpMain script;

    private BlurpScreen screen;

    public Runner(BlurpMain script, int width, int height) {

        this.script = script;
        screen = new BlurpScreen(width, height);


        // TODO: Refactor singletons to have instance manager classes that can initialise.
        RuntimeRepository.getInstance(); // This makes me want to cry.
    }

    public BlurpScreen getScreen() {

        return screen;
    }

    public void start() {

        Thread scriptThread = new Thread(new ScriptRunnable());
        scriptThread.start();
    }

    // TODO: Need a way to pause / resume / restart / stop the script. Communicate via flags in Repository? Blurpifier states?
    //       Note - When paused, the Blurpifier will still have to allow renders to continue so that input gestures
    //              (e.g. to resume) get picked up.
    private class ScriptRunnable implements Runnable {

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
