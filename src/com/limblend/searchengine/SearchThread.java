package com.limblend.searchengine;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public class SearchThread extends Thread {

    private FoundFilesTreeModel model;
    private String pattern;
    private String extension;

    public SearchThread(FoundFilesTreeModel model, String pattern, String extension){
        this.model = model;
        this.pattern = pattern;
        this.extension = extension;
    }

    @Override
    public void run() {
        DirectoryListGeneratorInterface generator = new RecursiveDirectoryListGenerator();
        FileMatchesNode node = (FileMatchesNode) model.getRoot();
        ((FileMatchesNode) model.getRoot()).removeAllChildren();
        HashSet<File> directoriesList = generator.getDirectoriesList(node.getFile());
        if(this.isInterrupted())
            return;
        FileExtensionFilter filter = new FileExtensionFilter();
        //TODO: splitable extension
        LinkedList<File> filteredFiles = new LinkedList<>();
        Iterator<File> it = directoriesList.iterator();
        while (it.hasNext()) {
            filteredFiles.addAll(filter.filter(it.next().listFiles(File::isFile), extension));
        }
        if(this.isInterrupted())
            return;
        LinkedList<FileMatchesNode> nodes = new LinkedList<>();
        ArrayList<Integer> matches = new ArrayList<>();
        FilePatternFinder finder = new BoyerMoorePatternFinder();
        it = filteredFiles.iterator();
        File f;
        int i = 0;
        while (it.hasNext()) {
            f = it.next();
            matches = finder.search(f, pattern);
            if (!matches.isEmpty()) {
                model.insertNode(f, matches);
                if(this.isInterrupted())
                    return;
            }
        }
        System.out.println("");
    }
}
