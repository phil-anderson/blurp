package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.java.*;

public class KeyTypedExample extends BlurpJavaProgram{


    @Override
    public void run() {

        while(screen.update()) {

            if(keyboard.wasKeyTyped()) system.log.print(keyboard.typedKey);
        }
    }
}
