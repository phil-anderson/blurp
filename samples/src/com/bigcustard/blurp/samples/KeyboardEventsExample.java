package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.java.*;

public class KeyboardEventsExample extends BlurpJavaProgram{

    @Override
    public void run() {

        keyboard.Enter.onPressed(new Runnable() {
            @Override
            public void run() {
                console.println("Enter Down");
            }
        });

        keyboard.Enter.onReleased(new Runnable() {
            @Override
            public void run() {
                console.println("Enter Up");
            }
        });

        while(screen.update()) {
            if(keyboard.Space.wasJustPressed()) {
                console.println("Space Down");
            }
            if(keyboard.Space.wasJustReleased()) {
                console.println("Space Up");
            }
        }
    }
}
