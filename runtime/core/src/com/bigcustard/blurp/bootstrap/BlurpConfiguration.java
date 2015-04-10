package com.bigcustard.blurp.bootstrap;

import com.badlogic.gdx.utils.viewport.*;

public class BlurpConfiguration {

    public static enum DebugMode { OFF, VISIBLE_SPRITES, ALL_SPRITES };

    private Viewport viewport;
    private String contentRoot;
    private DebugMode debugMode;

    public BlurpConfiguration(Viewport viewport) {

        this.viewport = viewport;
    }

    public BlurpConfiguration contentRoot(String contentRoot) {

        this.contentRoot = contentRoot;
        return this;
    }

    public BlurpConfiguration debugMode(DebugMode debugMode) {

        this.debugMode = debugMode;
        return this;
    }

    public boolean isDebugEnabled() {

        return debugMode != DebugMode.OFF;
    }

    public boolean isDebugAll() {

        return debugMode == DebugMode.ALL_SPRITES;
    }

    public Viewport getViewport() {

        return viewport;
    }

    public String getContentRoot() {

        return contentRoot;
    }

    public DebugMode getDebugMode() {

        return debugMode;
    }
}
