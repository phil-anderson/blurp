package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.model.java.*;

public class KeyboardEventsExample extends BlurpJavaProgram{

    @Override
    public void run() {

        keyboard.onKeyPressed(Key.Enter_Key, new Runnable() {
            @Override
            public void run() {
                console.println("Enter Down");
            }
        });

        keyboard.onKeyReleased(Key.Enter_Key, new Runnable() {
            @Override
            public void run() {
                console.println("Enter Up");
            }
        });

        while(screen.update()) {
            if(keyboard.wasKeyJustPressed(Key.Space_Key)) console.println("Space Down");
            if(keyboard.wasKeyJustReleased(Key.Space_Key)) console.println("Space Up");
        }
    }
}
