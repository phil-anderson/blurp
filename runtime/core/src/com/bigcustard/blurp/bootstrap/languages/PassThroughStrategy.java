package com.bigcustard.blurp.bootstrap.languages;

import com.badlogic.gdx.utils.*;

public class PassThroughStrategy extends NamingStrategy {


    @Override
    public String transform(String name) {

        return name;
    }

    @Override
    protected String handleParts(Array<String> parts) {

        return null;
    }
}
