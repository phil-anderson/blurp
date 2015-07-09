package com.bigcustard.blurp.core;

import java.io.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.*;
import com.badlogic.gdx.files.*;
import com.bigcustard.blurp.core.commands.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.util.Files;

public class SoundEffectImpl extends SoundEffect {

    private Sound gdxSound;

    public SoundEffectImpl(final String soundEffectFilename) {

        BlurpStore.runtimeRepository.registerCommand(new Command() {
            @Override
            public void execute(float deltaTime) {

                try {
                    FileHandle soundEffectFile = Files.getFile(BlurpStore.configuration.getContentRoot() + soundEffectFilename);
                    if(soundEffectFile.length() > 1024 * 1024) {
                        throw new BlurpException("File " + soundEffectFilename + "is too large. Sound effect files must be < 1MB");
                    }
                    gdxSound = Gdx.audio.newSound(soundEffectFile);
                    BlurpStore.runtimeRepository.registerSoundEffect(SoundEffectImpl.this);
                } catch(FileNotFoundException e) {
                    throw new BlurpException("Error loading sound effect " + soundEffectFilename);
                }
            }
        });
    }

    @Override
    public SoundEffect play(final double volume, final double speed) {

        BlurpStore.runtimeRepository.registerCommand(new Command() {

            @Override
            public void execute(float deltaTime) {
                gdxSound.play((float) volume, (float) speed, 0);
            }
        });
        return this;
    }

    public void dispose() {

        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
                if(gdxSound != null) {
                    gdxSound.stop();
                    gdxSound.dispose();
                }
            }
        });
    }
}
