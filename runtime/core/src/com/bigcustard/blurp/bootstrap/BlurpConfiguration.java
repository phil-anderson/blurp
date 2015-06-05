package com.bigcustard.blurp.bootstrap;

import com.badlogic.gdx.utils.viewport.*;

public class BlurpConfiguration {

    private ScalingViewport viewport;
    private String contentRoot = "";
    private boolean debugEnabled;
    private ScriptCompletionHandler scriptCompletionHandler;

    public BlurpConfiguration(ScalingViewport viewport) {

        this.viewport = viewport;
        scriptCompletionHandler = new ScriptCompletionHandler();
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

    public void setScriptCompletionHandler(ScriptCompletionHandler scriptCompletionHandler) {

        this.scriptCompletionHandler = scriptCompletionHandler;
    }

    public boolean isDebugEnabled() {

        return debugEnabled;
    }

    public ScalingViewport getViewport() {

        return viewport;
    }

    public String getContentRoot() {

        return contentRoot;
    }

    public ScriptCompletionHandler getScriptCompletionHandler() {

        return scriptCompletionHandler;
    }
}
