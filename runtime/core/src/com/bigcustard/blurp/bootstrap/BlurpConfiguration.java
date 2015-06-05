package com.bigcustard.blurp.bootstrap;

public class BlurpConfiguration {

    private double initialViewportWidth, initialViewportHeight;
    private String contentRoot = "";
    private boolean debugEnabled;
    private ScriptCompletionHandler scriptCompletionHandler;

    public BlurpConfiguration(double initialViewportWidth, double initialViewportHeight) {

        this.initialViewportWidth = initialViewportWidth;
        this.initialViewportHeight = initialViewportHeight;
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

    public String getContentRoot() {

        return contentRoot;
    }

    public ScriptCompletionHandler getScriptCompletionHandler() {

        return scriptCompletionHandler;
    }

    public double getInitialViewportWidth() {

        return initialViewportWidth;
    }

    public double getInitialViewportHeight() {

        return initialViewportHeight;
    }
}
