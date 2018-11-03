package com.limblend.searchengine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class SearchComponent implements ActionListener {
    private JTextField chosenDirectory;
    private JTextField extension;
    private JTextField stringToSearch;
    private SearchThread searchThread;

    public SearchComponent(JTextField chosenDirectory, JTextField extension, JTextField stringToSearch){
        this.chosenDirectory = chosenDirectory;
        this.extension = extension;
        this.stringToSearch = stringToSearch;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        FoundFilesTreeModel model = null;
        if(searchThread == null){
            File file = new File(chosenDirectory.getText());
            if(file.exists() && file.isDirectory()) {
                if(!stringToSearch.getText().equals("")) {
                    String extensionValue = extension.getText();
                    if(extensionValue.equals("")) {
                        extensionValue = "log";
                    }
                    model = new FoundFilesTreeModel(new FileMatchesNode(null, file));
                    SearchEngineOutputGUI.setupAndView(model, stringToSearch.getText(), extensionValue);
                    searchThread = new SearchThread(model, stringToSearch.getText(), extensionValue);
                }
                else {
                    JOptionPane.showMessageDialog(new Frame(), "String to search should not be empty!", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            else{
                JOptionPane.showMessageDialog(new Frame(), "Selected directory does not exists!", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        if(searchThread.isAlive()){
            searchThread.interrupt();
            try {
                searchThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        searchThread = new SearchThread(model, stringToSearch.getText(), extension.getText());
        searchThread.start();
    }
}
