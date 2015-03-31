package com.bigcustard.blurp.testutils;

import com.badlogic.gdx.utils.viewport.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.ui.*;
import static org.mockito.Mockito.*;

public class SingletonFactoryForTests extends SingletonFactory {

    public SingletonFactoryForTests() {

        super(new ScreenViewport());
    }

    @Override
    protected BlurpScreen makeBlurpScreen(Viewport viewport) {

        return mock(BlurpScreen.class);
    }
}
