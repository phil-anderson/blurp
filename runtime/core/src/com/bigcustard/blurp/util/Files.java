package com.bigcustard.blurp.util;

import java.io.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.files.*;

public class Files {

    public static FileHandle getFile(String filename) throws FileNotFoundException {

        FileHandle localFile = Gdx.files.local(filename);
        if(localFile.exists()) {
            return localFile;
        }

        FileHandle absoluteFile = Gdx.files.absolute(filename);
        if(absoluteFile.exists()) {
            return absoluteFile;
        }

        FileHandle classpathFile = Gdx.files.classpath(filename);
        if(classpathFile.exists()) {
            return classpathFile;
        }

        throw new FileNotFoundException("Couldn't find file: " + filename);
    }
}
