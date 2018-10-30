package com.limblend.searchengine;

import java.io.File;
import java.net.URI;
import java.nio.file.Path;

public class SearchEngine {
    public static void main(String[] args){
        FoundFilesTreeModel model = new FoundFilesTreeModel(new FileMatchesNode(null, new File(System.getProperty("user.home"))));
        SearchEngineGUI.setupAndView(model);
        DirectoryListGenerator generator =new DirectoryListGenerator();
    }

}
