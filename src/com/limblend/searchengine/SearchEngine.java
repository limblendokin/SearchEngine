package com.limblend.searchengine;

import java.io.File;

public class SearchEngine {
    public static void main(String[] args){
        FoundFilesTreeModel model = new FoundFilesTreeModel(new FileMatchesNode(null, new File("/home/limblend/Documents")));
        SearchEngineGUI.setupAndView(model);
        DirectoryListGenerator generator =new DirectoryListGenerator();
    }

}
