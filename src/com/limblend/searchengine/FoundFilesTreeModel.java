package com.limblend.searchengine;

import javax.swing.tree.*;
import java.io.File;
import java.nio.file.Path;
import java.util.*;

public class FoundFilesTreeModel extends DefaultTreeModel {
    public FoundFilesTreeModel(FileMatchesNode root){
        super(root);
    }
    public void insertNode(File file, ArrayList<Integer> matches){
        FileMatchesNode parent = (FileMatchesNode)root;
        int level = parent.getFile().toPath().getNameCount();
        Path path = file.toPath();
        FileMatchesNode child;
        boolean exists;
        int j = 0;
        for(int i = level + 1; i < path.getNameCount(); i++){
            exists = false;
            for(j = 0; j < parent.getChildCount();j++){
                child = (FileMatchesNode) parent.getChildAt(j);
                Path childSubpath = child.getFile().toPath().getRoot().resolve(child.getFile().toPath().subpath(0,i));
                Path fileSubpath = file.toPath().getRoot().resolve(file.toPath().subpath(0,i));
                if(childSubpath.equals(fileSubpath)){
                    exists = true;
                    break;
                }
            }
            if(exists){
                parent = (FileMatchesNode) parent.getChildAt(j);
            }
            else{
                parent.insert(new FileMatchesNode(parent, file.toPath().getRoot().resolve(file.toPath().subpath(0,i)).toFile()), 0);
                parent = (FileMatchesNode) parent.getChildAt(0);
            }
        }
        parent.insert(new FileMatchesNode(parent, file, matches), 0);
    }

}
