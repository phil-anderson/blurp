package com.bigcustard.blurp.core;

import java.io.*;
import com.badlogic.gdx.*;
import com.bigcustard.blurp.core.commands.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.util.Files;

public class MusicImpl implements Music {

    private final String filename;
    private com.badlogic.gdx.audio.Music gdxMusic;
    private boolean initialised;
    private float volume = 1;

    public MusicImpl(final String filename) {

        this.filename = filename;
    }

    @Override
    public Music play() {

        ensureLoaded();
        BlurpStore.runtimeRepository.registerCommand(new Command() {
            @Override
            public void execute(float deltaTime) {
                gdxMusic.play();
            }
        });
        return this;
    }

    @Override
    public Music play(double volume) {

        setVolume(volume);
        play();
        return this;
    }

    @Override
    public Music pause() {

        if(gdxMusic != null) {
            BlurpStore.runtimeRepository.registerCommand(new Command() {
                @Override
                public void execute(float deltaTime) {
                    gdxMusic.pause();
                }
            });
        }
        return this;
    }

    @Override
    public Music stop() {

        if(gdxMusic != null) {
            BlurpStore.runtimeRepository.registerCommand(new Command() {
                @Override
                public void execute(float deltaTime) {
                    gdxMusic.stop();
                    gdxMusic.dispose();
                    gdxMusic = null;
                    BlurpStore.runtimeRepository.deregisterMusic(MusicImpl.this);
                }
            });
        }
        return this;
    }

    @Override
    public Music setPosition(final int milliseconds) {

        ensureLoaded();
        BlurpStore.runtimeRepository.registerCommand(new Command() {
            @Override
            public void execute(float deltaTime) {
                gdxMusic.setPosition(milliseconds / 1000f);
            }
        });
        return this;
    }

    @Override
    public Music setVolume(final double volume) {

        ensureLoaded();
        this.volume = (float) volume;
        BlurpStore.runtimeRepository.registerCommand(new Command() {
            @Override
            public void execute(float deltaTime) {
                gdxMusic.setVolume((float) volume);
            }
        });
        return this;
    }

    private void ensureLoaded() {

        if(!initialised) {
            BlurpStore.runtimeRepository.registerCommand(new Command() {
                @Override
                public void execute(float deltaTime) {

                    try {
                        gdxMusic = Gdx.audio.newMusic(Files.getFile(BlurpStore.configuration.getContentRoot() + filename));
                        gdxMusic.setLooping(true);
                        gdxMusic.setVolume(volume);
                        BlurpStore.runtimeRepository.registerMusic(MusicImpl.this);
                    } catch(FileNotFoundException e) {
                        throw new BlurpException("Couldn't load music file " + filename, e);
                    }
                }
            });
            initialised = true;
        }
    }

    public com.badlogic.gdx.audio.Music getGdxMusic() {

        return gdxMusic;
    }

    public void dispose() {

        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
                if(gdxMusic != null) {
                    gdxMusic.stop();
                    gdxMusic.dispose();
                }
            }
        });
    }
}
