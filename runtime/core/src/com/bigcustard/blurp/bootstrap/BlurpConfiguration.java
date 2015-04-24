package com.bigcustard.blurp.bootstrap;

import com.badlogic.gdx.utils.viewport.*;

public class BlurpConfiguration {

    private Viewport viewport;
    private String contentRoot = "";
    private boolean debugEnabled;

    public BlurpConfiguration(Viewport viewport) {

        this.viewport = viewport;
    }

    public BlurpConfiguration setContentRoot(String contentRoot) {

        if(!contentRoot.endsWith("/")) contentRoot += "/";
        this.contentRoot = contentRoot;
        return this;
    }

    public BlurpConfiguration setDebugEnabled(boolean enabled) {

        this.debugEnabled = enabled;
        return this;
    }

    public boolean isDebugEnabled() {

        return debugEnabled;
    }

    public Viewport getViewport() {

        return viewport;
    }

    public String getContentRoot() {

        return contentRoot;
    }
}
