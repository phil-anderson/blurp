package com.bigcustard.blurp.runtimemodel;

import java.util.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.util.*;

// A quick, hacky log
public class RuntimeLog {

    private final int lines, columns; // In characters

    private final float pixelHeight;
    private final float lineSpacing;
    private final String[] contents;

    private Colour lastColour;
    private double lastAlpha;
    private int currentLine;
    private int currentColumn;

    public RuntimeLog() {

        // TODO: This is probably going to be wrong when we start mixing Camera zoom etc in.
        this.pixelHeight = BlurpStore.mainCamera.viewportHeight;

        columns = (int) (BlurpStore.staticViewport.getWorldWidth() / BlurpStore.systemFont.getSpaceWidth());
        lines = (int) (pixelHeight / BlurpStore.systemFont.getLineHeight());
        lineSpacing = pixelHeight / lines;

        contents = new String[lines];
        clear();
    }

    public void clear() {

        Arrays.fill(contents, "");
        currentLine = 0;
        currentColumn = 0;
    }

    public void print(String text, Colour colour, double alpha) {

        text = text.replaceAll("\\[", "[[");

        if(text.contains("\n")) {
            int newLinePos = text.indexOf('\n');
            printWrapped(text.substring(0, newLinePos), colour, alpha);
            newLine();
            print(text.substring(newLinePos + 1), colour, alpha);
        } else {
            printWrapped(text, colour, alpha);
        }
    }

    private void printWrapped(String text, Colour colour, double alpha) {

        int charsRemaining = columns - currentColumn;

        if(text.length() > charsRemaining) {
            String textToAdd = text.substring(0, charsRemaining);
            printChunk(textToAdd, colour, alpha);
            newLine();
            printWrapped(text.substring(charsRemaining), colour, alpha);
        } else {
            printChunk(text, colour, alpha);
        }
    }

    private void printChunk(String text, Colour colour, double alpha) {

        if(colour != lastColour || alpha != lastAlpha) {
            contents[currentLine] += "[#" + Convert.toGdxColour(colour, alpha).toString() + "]";
            lastColour = colour;
            lastAlpha = alpha;
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
                BlurpStore.systemFont.draw(batch, contents[i], 0, y);
            }
        }
    }
}
