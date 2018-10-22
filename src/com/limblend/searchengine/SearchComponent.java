package com.limblend.searchengine;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchComponent implements ActionListener {
    private FoundFilesTree model;
    private JTextField extension;
    private JTextField stringToSearch;

    public SearchComponent(FoundFilesTree model, JTextField extension, JTextField stringToSearch){
        this.model = model;
        this.extension = extension;
        this.stringToSearch = stringToSearch;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //TODO: model.search
        System.out.println("searching string \""+stringToSearch.getText()+"\" in files with "+extension.getText()+" extension");
    }
}
