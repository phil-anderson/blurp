package com.bigcustard.blurp.runtimemodel;

import java.util.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.util.*;

// A quick, hacky console
public class RuntimeConsole {

    private final int lines, columns; // In characters

    private final float pixelHeight;
    private final float lineSpacing;
    private final BitmapFont font;
    private final String[] contents;

    private Colour lastColour;
    private int currentLine;
    private int currentColumn;

    public RuntimeConsole() {

        // This is probably going to be wrong when we start mixing Camera zoom etc in.
        this.pixelHeight = BlurpStore.configuration.getViewport().getWorldHeight();

        // Font must be non-proportional
        font = new BitmapFont(Gdx.files.internal("small-rabbit.fnt"));
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.setMarkupEnabled(true);

        columns = (int) (BlurpStore.configuration.getViewport().getWorldWidth() / font.getSpaceWidth());
        lines = (int) (pixelHeight / font.getLineHeight());
        lineSpacing = pixelHeight / lines;

        contents = new String[lines];
        clear();
    }

    public void clear() {

        Arrays.fill(contents, "");
        currentLine = 0;
        currentColumn = 0;
    }

    public void print(String text, Colour colour) {

        text = text.replaceAll("\\[", "[[");

        if(text.contains("\n")) {
            int newLinePos = text.indexOf('\n');
            printWrapped(text.substring(0, newLinePos), colour);
            newLine();
            print(text.substring(newLinePos + 1), colour);
        } else {
            printWrapped(text, colour);
        }
    }

    private void printWrapped(String text, Colour colour) {

        int charsRemaining = columns - currentColumn;

        if(text.length() > charsRemaining) {
            String textToAdd = text.substring(0, charsRemaining);
            printChunk(textToAdd, colour);
            newLine();
            printWrapped(text.substring(charsRemaining), colour);
        } else {
            printChunk(text, colour);
        }
    }

    private void printChunk(String text, Colour colour) {

        if(colour != lastColour) {
            contents[currentLine] += "[#" + Convert.toGdxColour(colour).toString() + "]";
            lastColour = colour;
        }
        contents[currentLine] += text;
        currentColumn += text.length();
    }

    private void newLine() {

        if(currentLine < lines - 1) {
            currentLine++;
        } else {
            for (int i = 1; i < lines; i++) {
                contents[i - 1] = contents[i];
            }
            contents[lines - 1] = "";
        }
        currentColumn = 0;
    }

    public void render(Batch batch) {

        for(int i = 0; i < lines; i++) {
            float y = pixelHeight - i * lineSpacing;
            if(!contents[i].isEmpty()) {
                font.draw(batch, contents[i], 0, y);
//                System.out.println(contents[i]);
            }
        }
    }
}
