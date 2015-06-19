package com.bigcustard.blurp.desktop;

import java.io.*;
import javax.swing.*;

public class FileSelector {

    public static File selectFile() {

        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Blurp: Select program to run");
        chooser.setApproveButtonText("Run");
        for(SupportedLanguage language : SupportedLanguage.values()) {
            chooser.addChoosableFileFilter(language.getFileFilter());
        }

        JFrame frame = new JFrame();
        int option = chooser.showOpenDialog(frame);
        frame.dispose();

        return option == JFileChooser.APPROVE_OPTION ? chooser.getSelectedFile() : null;
    }
}
