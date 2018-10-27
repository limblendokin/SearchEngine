package com.limblend.searchengine;

import javax.swing.event.EventListenerList;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.*;
import java.awt.*;
import java.io.File;
import java.nio.file.Path;
import java.util.EventListener;
import java.util.LinkedList;
import java.util.Vector;

public class FoundFilesTreeModel implements TreeModel {
    private FileMatchesNode root;
    private EventListenerList listenerList;
    public FoundFilesTreeModel(FileMatchesNode root){
        listenerList = new EventListenerList();
        this.root = root;
    }
    public void insertNode(File file, LinkedList<Integer> matches){
        int level = root.getLevel();
        FileMatchesNode tmp = root;
        Path path = file.toPath();
        for(int i = level + 1; i < path.getNameCount(); i++){
            tmp = tmp.insertChild(file.toPath().subpath(0,i).toFile());
        }
        tmp.insertChild(file, matches);
    }
    @Override
    public Object getRoot() {
        return root;
    }
    public void setRoot(FileMatchesNode root){
        this.root = root;
    }

    @Override
    public Object getChild(Object o, int i) {
        return ((FileMatchesNode)o).getChildAt(i);
    }

    @Override
    public int getChildCount(Object o) {
        return ((FileMatchesNode)o).getChildCount();
    }

    @Override
    public boolean isLeaf(Object o) {
        return ((FileMatchesNode)o).isLeaf();
    }

    @Override
    public void valueForPathChanged(TreePath treePath, Object o) {

    }

    @Override
    public int getIndexOfChild(Object o, Object o1) {
        return ((FileMatchesNode)o).getIndex((FileMatchesNode)o1);
    }

    @Override
    public void addTreeModelListener(TreeModelListener treeModelListener) {
        listenerList.add(TreeModelListener.class, treeModelListener);
    }

    @Override
    public void removeTreeModelListener(TreeModelListener treeModelListener) {
        listenerList.remove(TreeModelListener.class, treeModelListener);
    }

}
