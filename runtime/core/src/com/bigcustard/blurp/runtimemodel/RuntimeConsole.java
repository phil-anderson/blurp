package com.bigcustard.blurp.runtimemodel;

import java.util.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.util.*;

// A quick, hacky console
public class RuntimeConsole {

    private int lines, columns; // In characters

    private float pixelHeight;
    private float lineSpacing;
    private String[] contents;
    private StringBuilder stringContents;
    private OrthographicCamera pixelCamera;

    private Colour lastColour;
    private double lastAlpha;
    private int currentLine;

    private int currentColumn;

    public RuntimeConsole() {

        initialise();
    }

    public void initialise() {

        pixelCamera = new OrthographicCamera();
        pixelCamera.setToOrtho(false, BlurpStore.staticViewport.getScreenWidth(), BlurpStore.staticViewport.getScreenHeight());

        this.pixelHeight = BlurpStore.staticViewport.getScreenHeight();

        columns = (int) (BlurpStore.staticViewport.getScreenWidth() / BlurpStore.systemFont.getSpaceWidth());
        lines = (int) (pixelHeight / BlurpStore.systemFont.getLineHeight());
        lineSpacing = pixelHeight / lines;

        contents = new String[lines];
        stringContents = new StringBuilder();
        clear();
    }

    public void clear() {

        stringContents.setLength(0);
        Arrays.fill(contents, "");
        currentLine = 0;
        currentColumn = 0;
    }

    public void print(String text, Colour colour, double alpha) {

        text = text.replaceAll("\r\n", "\n").replaceAll("\r", "\n");
        stringContents.append(text);
        if(stringContents.length() > 32768) {
            stringContents.delete(0, stringContents.length() - 32768); // Max 32K characters
        }

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

        // Lines should always start with a colour tag for when they scroll to top.
        if (currentColumn == 0 || colour != lastColour || alpha != lastAlpha) {
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

        batch.setProjectionMatrix(pixelCamera.combined);
        for(int i = 0; i < lines; i++) {
            float y = pixelHeight - i * lineSpacing;
            if(!contents[i].isEmpty()) {
                BlurpStore.systemFont.draw(batch, contents[i], 0, y);
            }
        }
    }

    @Override
    public String toString() {

        return stringContents.toString();
    }
}
