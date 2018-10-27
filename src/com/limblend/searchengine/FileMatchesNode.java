package com.limblend.searchengine;

import javax.swing.tree.TreeNode;
import java.io.File;
import java.util.*;

public class FileMatchesNode implements TreeNode {
    private FileMatchesNode parent;
    private Vector<FileMatchesNode> children;
    private int level;
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
        this.parent = parent;
        level = parent.getLevel() + 1;
        parent.setChild(this);
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

    public int getLevel(){
        return level;
    }

    @Override
    public TreeNode getChildAt(int i) {
        return children.elementAt(i);
    }

    @Override
    public int getChildCount() {
        return children.size();
    }

    @Override
    public TreeNode getParent() {
        return parent;
    }

    @Override
    public int getIndex(TreeNode treeNode) {
        return children.indexOf(treeNode);
    }

    @Override
    public boolean getAllowsChildren() {
        return !isLeaf;
    }

    @Override
    public boolean isLeaf() {
        return isLeaf;
    }

    @Override
    public Enumeration children() {
        return null;
    }

    public String toString(){
        return file.getName();
    }
}
