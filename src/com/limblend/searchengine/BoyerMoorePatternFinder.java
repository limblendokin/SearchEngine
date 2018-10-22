package com.limblend.searchengine;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.File;

public class BoyerMoorePatternFinder implements FilePatternFinder {
    private File file;
    public BoyerMoorePatternFinder(File file){
        this.file = file;
    }
    public FileMatchesNode search(){
        //TODO: Search
        throw new NotImplementedException();
    }
}
