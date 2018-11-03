package com.limblend.searchengine;

import java.io.File;
import java.util.*;

public class DirectoryListGenerator implements DirectoryListGeneratorInterface {
    public DirectoryListGenerator(){

    }
    @Override
    public HashSet<File> getDirectoriesList(File rootDirectory){
        HashSet<File> dirHashSet = new HashSet<>();
        dirHashSet.add(rootDirectory);
        PriorityQueue<File> dirQueue = new PriorityQueue<>();
        dirQueue.add(rootDirectory);
        File curDir;
        File[] subdirectories;
        while(!dirQueue.isEmpty()){
            curDir = dirQueue.remove();
            subdirectories = curDir.listFiles();
            if(subdirectories!=null) {
                for (File f :
                        subdirectories) {
                    dirQueue.add(f);
                }
                if (!dirHashSet.contains(curDir.hashCode())) {
                    dirHashSet.add(curDir);
                }
            }
        }
        return dirHashSet;
    }
}