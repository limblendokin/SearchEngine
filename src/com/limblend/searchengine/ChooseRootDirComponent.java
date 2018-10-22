package com.limblend.searchengine;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ChooseRootDirComponent implements ActionListener {

    private FoundFilesTree model;

    public ChooseRootDirComponent(FoundFilesTree model){
        this.model = model;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        File selectedFile;
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            model.setRoot(new DefaultMutableTreeNode(fileChooser.getSelectedFile()));
        } else {
            System.out.println("No Selection ");
        }
    }
}
