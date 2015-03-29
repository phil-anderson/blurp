package com.bigcustard.blurp.testutils;

import com.badlogic.gdx.*;
import com.badlogic.gdx.backends.headless.*;
import com.badlogic.gdx.graphics.*;
import org.junit.*;
import org.mockito.*;

import static org.mockito.Mockito.*;

public class LibGdxTest {

    @BeforeClass
    public static void SetUpClass() {

        ApplicationListener mockApplication = Mockito.mock(ApplicationListener.class);

        Gdx.gl = mock(GL20.class);
        final HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        config.renderInterval = 1f / 60f;
        new HeadlessApplication(mockApplication, config);
    }
}
