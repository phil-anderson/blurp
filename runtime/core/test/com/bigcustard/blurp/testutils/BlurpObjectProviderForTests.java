package com.bigcustard.blurp.testutils;

import com.badlogic.gdx.utils.viewport.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.ui.*;
import org.mockito.*;

public class BlurpObjectProviderForTests extends BlurpObjectProvider {

    @Mock private BlurpScreen mockBlurpScreen;

    public BlurpObjectProviderForTests() {

        super(new ScreenViewport());
        MockitoAnnotations.initMocks(this);
    }

    @Override
    public BlurpScreen getBlurpScreen() {

        return mockBlurpScreen;
    }
}
