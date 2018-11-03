package com.limblend.searchengine;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedList;

public class FileSelectionComponent implements TreeSelectionListener {
    private JTree tree;
    private JTabbedPane tabbedPane;
    public FileSelectionComponent(JTree tree, JTabbedPane tabbedPane){
        this.tree = tree;
        this.tabbedPane = tabbedPane;
    }
    @Override
    public void valueChanged(TreeSelectionEvent treeSelectionEvent) {
        FileMatchesNode node = (FileMatchesNode) tree.getLastSelectedPathComponent();
        if(node==null)
            return;
        if(!node.isLeaf())
            return;
        boolean alreadyContains = false;
        int index = tabbedPane.getTabCount()-1;
        for(int i = 0; i<tabbedPane.getTabCount(); i++){
            if(tabbedPane.getTitleAt(i).equals(node.getFile().getName())){
                alreadyContains = true;
                index = i;
                break;
            }
        }
        if(!alreadyContains) {
            tabbedPane.addTab(node.getFile().getName(), new TextFileViewPanel(node.getFile(), node.getMatches()));
            index++;
        }
        tabbedPane.setSelectedIndex(index);
    }
}
