package com.limblend.searchengine;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class SearchComponent implements ActionListener {
    private FoundFilesTreeModel model;
    private JTextField extension;
    private JTextField stringToSearch;

    public SearchComponent(FoundFilesTreeModel model, JTextField extension, JTextField stringToSearch){
        this.model = model;
        this.extension = extension;
        this.stringToSearch = stringToSearch;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //TODO: IOEceptionHandler

        DirectoryListGenerator generator = new DirectoryListGenerator();
        FileMatchesNode node = (FileMatchesNode)model.getRoot();
        HashSet<File> directoriesList = generator.getDirectoriesList(node.getFile());
        FileExtensionFilter filter = new FileExtensionFilter();
        //TODO: splitable extension
        LinkedList<File> filteredFiles = new LinkedList<>();
        Iterator<File> it = directoriesList.iterator();
        while(it.hasNext()){
            filteredFiles.addAll(filter.filter(it.next().listFiles(File::isFile), extension.getText()));
        }
        LinkedList<FileMatchesNode> nodes = new LinkedList<>();
        LinkedList<Integer> matches = new LinkedList<>();
        FilePatternFinder finder = new BoyerMoorePatternFinder();
        it = filteredFiles.iterator();
        File f;
        int i = 0;
        while(it.hasNext()){
            f = it.next();
            try {
                matches = finder.search(f, stringToSearch.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(!matches.isEmpty()){
                model.insertNode(f, matches);
            }
        }
    }
}
