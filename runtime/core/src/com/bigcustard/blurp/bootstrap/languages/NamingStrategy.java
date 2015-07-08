package com.bigcustard.blurp.bootstrap.languages;

import java.lang.*;
import java.lang.StringBuilder;
import com.badlogic.gdx.utils.*;

public abstract class NamingStrategy {

    public static final NamingStrategy PASS_THROUGH = new PassThroughStrategy();
    public static final NamingStrategy RUBY_CONSTANT = new RubyConstantStrategy();
    public static final NamingStrategy RUBY_GLOBAL = new RubyGlobalStrategy();

    public String transform(String name) {

        return handleParts(splitCamel(name));
    }

    protected abstract String handleParts(Array<String> parts);

    private Array<String> splitCamel(String name) {

        Array<String> parts = new Array<String>(4);
        StringBuilder currentPart = new StringBuilder("");
        for(int i = 0; i < name.length(); i++) {
            char ch = name.charAt(i);
            if(Character.isUpperCase(ch) && currentPart.length() != 0) {
                parts.add(currentPart.toString());
                currentPart.setLength(0);
            }
            currentPart.append(ch);
        }
        parts.add(currentPart.toString());
        return parts;
    }
}
