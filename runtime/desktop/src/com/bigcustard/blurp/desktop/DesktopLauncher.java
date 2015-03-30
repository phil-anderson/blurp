package com.bigcustard.blurp.desktop;

import com.badlogic.gdx.backends.lwjgl.*;
import com.badlogic.gdx.utils.viewport.*;
import com.bigcustard.blurp.bootstrap.*;

public class DesktopLauncher {

    private static final String USAGE_MESSAGE = "Usage = DesktopLauncher <scriptClassName> <width> <height>";

    public static void main (String[] args) {

        // TODO: Find a proper args API
        if(args.length != 3) throw new IllegalArgumentException(USAGE_MESSAGE);

        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        String scriptName = args[0];
        try {
            config.width = Integer.valueOf(args[1]);
            config.height = Integer.valueOf(args[2]);
        } catch(Exception e) {
            throw new IllegalArgumentException(USAGE_MESSAGE, e);
        }

		new LwjglApplication(new BlurpApp(scriptName,
                             new FitViewport(config.width, config.height)),
                             config);
	}
}
