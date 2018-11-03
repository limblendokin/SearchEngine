package com.limblend.searchengine;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public interface FilePatternFinder {
    ArrayList<Integer> search(File file, String pattern);
}
