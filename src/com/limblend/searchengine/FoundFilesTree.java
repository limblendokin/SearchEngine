package com.limblend.searchengine;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.*;
import java.awt.*;
import java.io.File;

public class FoundFilesTree extends DefaultTreeModel {
    public FoundFilesTree(MutableTreeNode treeNode) {
        super(treeNode);
        //super.insertNodeInto(new DefaultMutableTreeNode(new File("Documents")), treeNode, 1 );
    }
    public FoundFilesTree(MutableTreeNode treeNode, boolean b) {
        super(treeNode, b);
    }
}
