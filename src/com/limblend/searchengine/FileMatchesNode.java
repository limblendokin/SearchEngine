package com.limblend.searchengine;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;
import java.util.LinkedList;

public class FileMatchesNode extends DefaultMutableTreeNode {
    private File file;
    private LinkedList<Integer> matches;
    public FileMatchesNode(File file, LinkedList<Integer> matches){
        this.file = file;
        this.matches = matches;
    }

}
