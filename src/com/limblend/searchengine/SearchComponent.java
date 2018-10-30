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
    private JTree tree;

    public SearchComponent(FoundFilesTreeModel model, JTextField extension, JTextField stringToSearch, JTree tree){
        this.model = model;
        this.extension = extension;
        this.stringToSearch = stringToSearch;
        this.tree = tree;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        DirectoryListGenerator generator = new DirectoryListGenerator();
        FileMatchesNode node = (FileMatchesNode)model.getRoot();
        ((FileMatchesNode) model.getRoot()).removeAllChildren();
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
        expandRows(tree, 0, tree.getRowCount());
        System.out.print("");
    }
    private void expandRows(JTree tree, int startIndex, int rowCount){
        for(int i = startIndex; i<rowCount; i++){
            tree.expandRow(i);
        }
        if(rowCount !=tree.getRowCount()){
            expandRows(tree, rowCount, tree.getRowCount());
        }
    }
}
