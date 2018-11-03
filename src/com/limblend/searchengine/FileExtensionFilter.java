package com.limblend.searchengine;

import java.io.File;
import java.io.FilenameFilter;
import java.util.LinkedList;
import java.util.ListIterator;

public class FileExtensionFilter {
    public LinkedList<File> filter(File[] files, String extension){
        LinkedList<File> filteredFiles = new LinkedList<>();
        if(files!=null) {
            for (File f : files) {
                if (getFileExtension(f.getName()).equals(extension)) {
                    filteredFiles.add(f);
                }
            }
        }
        return filteredFiles;
    }
    private String getFileExtension(String fileName){
        int extensionIndex = fileName.lastIndexOf(".");
        if(extensionIndex > 0){
            return fileName.substring(extensionIndex + 1);
        }
        else {
            //TODO: this branch will execute if there is no extension or if it is a hidden dir. This should't happen
            return "";
        }
    }

}
