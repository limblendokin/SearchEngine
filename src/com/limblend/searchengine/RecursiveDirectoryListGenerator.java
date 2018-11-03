package com.limblend.searchengine;

import java.io.File;
import java.util.HashSet;

public class RecursiveDirectoryListGenerator implements DirectoryListGeneratorInterface {
    private HashSet<File> fileHashSet;
    public RecursiveDirectoryListGenerator(){
        fileHashSet = new HashSet<>();
    }
    @Override
    public HashSet<File> getDirectoriesList(File rootDirectory) {
        fillHashSet(rootDirectory);
        return fileHashSet;
    }
    private void fillHashSet(File root){
        fileHashSet.add(root);
        File[] list = root.listFiles(File::isDirectory);
        if (list != null) {
            for (File f :
                    root.listFiles(File::isDirectory)) {
                fillHashSet(f);
            }
        }
    }
}
