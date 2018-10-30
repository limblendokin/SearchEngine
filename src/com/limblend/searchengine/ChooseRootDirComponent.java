package com.limblend.searchengine;

import javax.swing.*;
import javax.swing.event.TreeModelEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ChooseRootDirComponent implements ActionListener {

    private FoundFilesTreeModel model;
    private JTree tree;

    public ChooseRootDirComponent(FoundFilesTreeModel model){
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        File selectedFile;
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            model.setRoot(new FileMatchesNode(null, fileChooser.getSelectedFile()));
        }
    }
}
