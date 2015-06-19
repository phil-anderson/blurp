package com.bigcustard.blurp.desktop;

import java.util.prefs.*;
import javax.swing.*;

public class FileSelector {

    private static final String LAST_FOLDER_KEY = "last_folder_selected";

    public static String selectFile() {

        Preferences preferences = Preferences.userNodeForPackage(FileSelector.class);
        String folder = preferences.get(LAST_FOLDER_KEY, "");
        JFileChooser chooser = new JFileChooser(folder);

        chooser.setDialogTitle("Blurp: Select program to run");
        chooser.setApproveButtonText("Run");
        for(SupportedLanguage language : SupportedLanguage.values()) {
            chooser.addChoosableFileFilter(language.getFileFilter());
        }

        JFrame frame = new JFrame();
        int option = chooser.showOpenDialog(frame);
        frame.dispose();

        if(option == JFileChooser.APPROVE_OPTION) {
            String result = chooser.getSelectedFile().getPath();
            preferences.put(LAST_FOLDER_KEY, result);
            return result;
        }
        return null;
    }
}
