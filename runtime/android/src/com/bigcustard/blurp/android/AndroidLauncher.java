package com.bigcustard.blurp.android;

import android.os.*;
import com.badlogic.gdx.backends.android.*;
import com.badlogic.gdx.utils.viewport.*;
import com.bigcustard.blurp.bootstrap.*;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new BlurpApp(null, new FitViewport(800, 480)), config);
	}
}
