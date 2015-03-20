package com.bigcustard.blurp.testutils;

import com.badlogic.gdx.*;
import com.badlogic.gdx.backends.headless.*;
import com.badlogic.gdx.graphics.*;
import com.bigcustard.blurp.ui.*;
import org.junit.*;

import static org.mockito.Mockito.mock;

public class LibGdxTest {

    @BeforeClass
    public static void SetUpClass() {

        Gdx.gl = mock(GL20.class);
        final HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        config.renderInterval = 1f / 60f;
        new HeadlessApplication(new Blurp(), config);
    }
}
