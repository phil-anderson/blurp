package com.bigcustard.blurp.model.java.bootstrap;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.effects.*;

// This over-complicated class is for Blurp internal use only. It allows BlurpJavaProgram instances to be created with
// a no-args constructor, without having a dependency on the Blurp runtime. It's not good practise by normal standards,
// but is expedient for this specific case. It's base on the initialise-on-demand holder idiom.
public class BlurpBootstrapHolder implements BlurpBootstrap {

    private static class SingletonHolder {

        private static final BlurpBootstrapHolder INSTANCE = new BlurpBootstrapHolder();
    }

    public static BlurpBootstrapHolder initialise(BlurpBootstrap blurpBootstrap) {

        if(SingletonHolder.INSTANCE.delegate != null) {
            throw new IllegalStateException("BlurpBootstrapHolder already initialised");
        }
        SingletonHolder.INSTANCE.delegate = blurpBootstrap;
        return SingletonHolder.INSTANCE;
    }

    public static BlurpBootstrapHolder getInstance() {

        if(SingletonHolder.INSTANCE.delegate == null) {
            throw new IllegalStateException("BlurpBootstrapHolder must be initialised before use");
        }
        return SingletonHolder.INSTANCE;
    }

    private BlurpBootstrap delegate;

    private BlurpBootstrapHolder() { }

    @Override
    public Blurp getBlurp() {

        return delegate.getBlurp();
    }

    @Override
    public Screen getScreen() {

        return delegate.getScreen();
    }

    @Override
    public Console getConsole() {

        return delegate.getConsole();
    }

    @Override
    public Camera getCamera() {

        return delegate.getCamera();
    }

    @Override
    public Effects getEffects() {

        return delegate.getEffects();
    }

    @Override
    public Keyboard getKeyboard() {

        return delegate.getKeyboard();
    }

    @Override
    public Mouse getMouse() {

        return delegate.getMouse();
    }

    @Override
    public Utils getUtils() {

        return delegate.getUtils();
    }
}
