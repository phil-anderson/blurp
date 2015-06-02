package com.bigcustard.blurp.desktop;

import com.bigcustard.blurp.ui.*;
import org.lwjgl.input.*;

public class LwjglMouseWindowChecker implements MouseWindowChecker {

    @Override
    public boolean isInsideWindow() {

        return Mouse.isInsideWindow();
    }
}
