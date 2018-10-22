package com.limblend.searchengine;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import java.io.File;

public class SearchEngine {
    public static void main(String[] args){
        FoundFilesTree model = new FoundFilesTree(new DefaultMutableTreeNode(new File("D://")));
        CustomGUI.setupAndView(model);
    }

}
