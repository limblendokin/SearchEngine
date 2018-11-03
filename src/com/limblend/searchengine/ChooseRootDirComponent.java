package com.limblend.searchengine;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseRootDirComponent implements ActionListener {

    private FoundFilesTreeModel model;
    private JTextField chosenDir;

    public ChooseRootDirComponent(FoundFilesTreeModel model, JTextField chosenDir){
        this.model = model;
        this.chosenDir = chosenDir;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            chosenDir.setText(fileChooser.getSelectedFile().getAbsolutePath());
            model.setRoot(new FileMatchesNode(null, fileChooser.getSelectedFile()));
        }
    }
}
