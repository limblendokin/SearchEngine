package com.limblend.searchengine;

import java.io.File;
import java.util.HashSet;

public interface DirectoryListGeneratorInterface {
    HashSet<File> getDirectoriesList(File rootDirectory);
}
