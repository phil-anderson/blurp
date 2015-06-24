package com.bigcustard.blurp.core;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.util.*;

public class BlurpState {

    public static boolean debugMode;
    public static Colour debugColour = Colours.LimeGreen;
    public static boolean scriptComplete;
    public static RuntimeException exception;
    public static long frameStartTime;
    public static boolean error;
    public static volatile boolean paused;
    public static String windowTitle;

    public static int numFrames = -1;
    public static long startTime;

    public static Color debugGdxColour() {

        return Convert.toGdxColour(debugColour);
    }

    public static void togglePause() {

        if(paused) {
            resume();
            setActualWindowTitle(BlurpState.windowTitle);
        } else {
            pause();
            setActualWindowTitle(BlurpState.windowTitle + " [PAUSED]");
        }
    }

    public static void pause() {

        paused = true;
        BlurpStore.timer.hold();
    }

    public static void resume() {

        paused = false;
        BlurpStore.timer.unhold();
    }

    public static void reset() {

        debugMode = false;
        debugColour = Colours.LimeGreen;
        scriptComplete = false;
        exception = null;
        error = false;
        paused = false;
        numFrames = -1;
    }

    public static void setWindowTitle(final String newTitle) {

        windowTitle = newTitle;
        setActualWindowTitle(newTitle);
    }

    private static void setActualWindowTitle(final String newTitle) {

        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
                Gdx.graphics.setTitle(newTitle);
            }
        });
    }
}
