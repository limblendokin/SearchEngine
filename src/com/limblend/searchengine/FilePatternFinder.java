package com.limblend.searchengine;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public interface FilePatternFinder {
    LinkedList<Integer> search(File file, String pattern) throws IOException;
}
