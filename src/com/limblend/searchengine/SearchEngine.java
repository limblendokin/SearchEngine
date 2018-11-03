package com.limblend.searchengine;

import java.io.File;

public class SearchEngine {
    public static void main(String[] args){
//        FoundFilesTreeModel model = new FoundFilesTreeModel(new FileMatchesNode(null, new File(System.getProperty("user.home"))));
        FoundFilesTreeModel model = new FoundFilesTreeModel(new FileMatchesNode(null, new File("D://Documents/GitHub/SearchEngine")));
        SearchEngineInputGUI.setupAndView(model);
        DirectoryListGenerator generator =new DirectoryListGenerator();
    }

}
