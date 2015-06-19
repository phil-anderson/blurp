package com.bigcustard.blurp.desktop;

import java.io.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.*;

public enum SupportedLanguage {

    Java("java", "Java classes", "class"),
    JavaScript("javascript", "JavaScript scripts", "js"),
    Python("python", "Python scripts", "py"),
    Groovy("groovy", "Groovy scripts", "groovy", "gry", "gr", "gsh"),
    Ruby("ruby", "Ruby scripts [WIP]", "rb");

    private String scriptEngineName;
    private FileFilter fileFilter;

    private SupportedLanguage(String scriptEngineName, String description, String... extensions) {

        this.scriptEngineName = scriptEngineName;
        this.fileFilter = new FileNameExtensionFilter(description, extensions);
    }

    public FileFilter getFileFilter() {

        return fileFilter;
    }

    public String getScriptEngineName() {

        return scriptEngineName;
    }

    public static SupportedLanguage forFile(File file) {

        for(SupportedLanguage language : values()) {
            if(language.fileFilter.accept(file)) return language;
        }
        return null;
    }
}
