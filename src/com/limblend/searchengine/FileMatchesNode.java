package com.limblend.searchengine;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import java.io.File;
import java.util.*;

public class FileMatchesNode extends DefaultMutableTreeNode {
    private FileMatchesNode parent;
    private boolean isLeaf;

    public File getFile() {
        return file;
    }

    public LinkedList<Integer> getMatches() {
        return matches;
    }

    private File file;
    private LinkedList<Integer> matches;

    public FileMatchesNode(FileMatchesNode parent, File file){
        if(parent != null) {
            this.parent = parent;
            parent.setChild(this);
        }
        this.file = file;
        this.isLeaf = false;
        children = new Vector<>();
    }

    public FileMatchesNode(FileMatchesNode parent, File file, LinkedList<Integer> matches){
        this(parent,file);
        this.matches = matches;
        this.isLeaf = true;
    }

    public FileMatchesNode insertChild(File file){
        boolean exists = false;
        int index = -1;
        for (int i = 0; i < children.size(); i++ ) {
            if (children.elementAt(i).getFile().hashCode() == file.hashCode()) {
                exists = true;
                index = i;
            }
        }
        if(exists)
            return children.elementAt(index);
        else{
            this.setChild(new FileMatchesNode(this, file));
            return children.lastElement();
        }
    }

    public FileMatchesNode insertChild(File file, LinkedList<Integer> matches){
        boolean exists = false;
        int index = -1;
        for (int i = 0; i < children.size(); i++ ) {
            if (children.elementAt(i).getFile().hashCode() == file.hashCode()) {
                exists = true;
                index = i;
            }
        }
        if(exists)
            return children.elementAt(index);
        else{
            this.setChild(new FileMatchesNode(this, file, matches));
            return children.lastElement();
        }
    }

    public void setChild(FileMatchesNode child){
        this.children.add(child);
    }



    public String toString(){
        return file.getName();
    }
}
