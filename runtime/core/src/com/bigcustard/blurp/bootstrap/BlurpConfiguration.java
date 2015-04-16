package com.bigcustard.blurp.bootstrap;

import com.badlogic.gdx.utils.viewport.*;

public class BlurpConfiguration {

    private Viewport viewport;
    private String contentRoot = "";
    private boolean debug;
    private boolean debugHidden;

    public BlurpConfiguration(Viewport viewport) {

        this.viewport = viewport;
    }

    public BlurpConfiguration setContentRoot(String contentRoot) {

        if(!contentRoot.endsWith("/")) contentRoot += "/";
        this.contentRoot = contentRoot;
        return this;
    }

    public BlurpConfiguration setDebug(boolean enabled) {

        this.debug = enabled;
        return this;
    }

    public BlurpConfiguration setDebugHidden(boolean enabled) {

        this.debugHidden = enabled;
        return this;
    }

    public boolean isDebug() {

        return debug;
    }

    public boolean isDebugHidden() {

        return debugHidden;
    }

    public Viewport getViewport() {

        return viewport;
    }

    public String getContentRoot() {

        return contentRoot;
    }
}
