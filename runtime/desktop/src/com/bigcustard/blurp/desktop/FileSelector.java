package com.bigcustard.blurp.desktop;

import java.util.prefs.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import com.bigcustard.blurp.bootstrap.languages.*;

public class FileSelector {

    private static final String LAST_FOLDER_KEY = "last_folder_selected";

    public static String selectFile() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e) {
            // No big deal.
        }

        Preferences preferences = Preferences.userNodeForPackage(FileSelector.class);
        String folder = preferences.get(LAST_FOLDER_KEY, "");
        JFileChooser chooser = new JFileChooser(folder);

        chooser.setDialogTitle("Blurp: Select program to run");
        chooser.setApproveButtonText("Run");
        for(SupportedLanguage language : SupportedLanguages.getAll()) {
            chooser.addChoosableFileFilter(new FileNameExtensionFilter(language.getDescription(), language.getFileExtensions()));
        }

        int option = chooser.showOpenDialog(null);
        if(option == JFileChooser.APPROVE_OPTION) {
            String result = chooser.getSelectedFile().getPath();
            preferences.put(LAST_FOLDER_KEY, result);
            return result;
        }
        return null;
    }
}
