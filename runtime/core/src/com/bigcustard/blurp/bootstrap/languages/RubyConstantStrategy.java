package com.bigcustard.blurp.bootstrap.languages;

import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.utils.StringBuilder;

public class RubyConstantStrategy extends NamingStrategy {

    @Override
    protected String handleParts(Array<String> parts) {

        StringBuilder result = new StringBuilder();
        for(String part : parts) {
            result.append(part).append("_");
        }
        result.setLength(result.length() - 1);
        return result.toString();
    }
}
