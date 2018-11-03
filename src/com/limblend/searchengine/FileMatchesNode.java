package com.limblend.searchengine;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import java.io.File;
import java.util.*;

public class FileMatchesNode extends DefaultMutableTreeNode {

    private File file;
    private ArrayList<Integer> matches;

    public File getFile() {
        return file;
    }

    public ArrayList<Integer> getMatches() {
        return matches;
    }

    public FileMatchesNode(FileMatchesNode parent, File file){
        super(parent, true);
        this.file = file;
    }

    public FileMatchesNode(FileMatchesNode parent, File file, ArrayList<Integer> matches){
        super(parent, false);
        this.file = file;
        this.matches = matches;
    }

    public String toString(){
        return file.getName();
    }
}
