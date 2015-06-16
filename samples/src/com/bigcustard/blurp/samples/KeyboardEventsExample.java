package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.model.java.*;

public class KeyboardEventsExample extends BlurpJavaProgram{

    @Override
    public void run() {

        keyboard.onKeyPressed(Key.Key_Enter, new Runnable() {
            @Override
            public void run() {
                console.println("Enter Down");
            }
        });

        keyboard.onKeyReleased(Key.Key_Enter, new Runnable() {
            @Override
            public void run() {
                console.println("Enter Up");
            }
        });

        while(screen.update()) {
            if(keyboard.wasKeyJustPressed(Key.Key_Space)) console.println("Space Down");
            if(keyboard.wasKeyJustReleased(Key.Key_Space)) console.println("Space Up");
        }
    }
}
